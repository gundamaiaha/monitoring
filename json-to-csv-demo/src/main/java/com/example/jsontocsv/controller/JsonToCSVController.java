package com.example.jsontocsv.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/conversion")
public class JsonToCSVController {




    @PostMapping("/jsonToCsv")
    public void jsonToCsv(HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile file) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(file.getInputStream());
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                        .writeValue(response.getWriter(), jsonTree);
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ file.getName() + "_converted.csv";
        response.setHeader(headerKey, headerValue);


    }




}
