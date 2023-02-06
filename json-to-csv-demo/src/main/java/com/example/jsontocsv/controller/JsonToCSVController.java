package com.example.jsontocsv.controller;

import com.example.jsontocsv.model.EVStationInfo;
import com.example.jsontocsv.service.EVStationCSVGeneratorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversion")
public class JsonToCSVController {


    @Autowired
    private EVStationCSVGeneratorService evStationCSVGeneratorService;


    @PostMapping("/jsonToCsv")
    public void jsonToCsv(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(file.getInputStream());
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(response.getWriter(), jsonTree);
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName() + "_converted.csv";
        response.setHeader(headerKey, headerValue);
    }

    @PostMapping("/generateEVStationCSVs")
    public void generateEVStationCSVs(@RequestParam MultipartFile file, HttpServletResponse response) throws IOException {
        //EVStation evStation = new ObjectMapper().readValue(file.getInputStream(), EVStation.class);

        File evZipFile = new File("evFiles.zip");
        Path path = Paths.get(evZipFile.getAbsolutePath());


        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=evFiles.zip");
        response.setStatus(HttpServletResponse.SC_OK);
        EVStationInfo evStationInfo = new ObjectMapper().readValue(file.getInputStream(), EVStationInfo.class);
        System.out.println("evStation size = " + evStationInfo.getEvStations().size());
        List<File> files = evStationCSVGeneratorService.generateEVStationCSVs(evStationInfo);


        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            for (File evFile : files) {
                FileSystemResource resource = new FileSystemResource(evFile);

                ZipEntry e = new ZipEntry(resource.getFilename());
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                zippedOut.putNextEntry(e);
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();

                zippedOut.finish();
            }
        }catch(Exception e){
            log.error("Error occurred while generating ev csv files. {}",e.getMessage());
        }


    }


}
