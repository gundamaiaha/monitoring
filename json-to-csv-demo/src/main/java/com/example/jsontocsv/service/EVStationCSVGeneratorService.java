package com.example.jsontocsv.service;

import com.example.jsontocsv.model.EVStation;
import com.example.jsontocsv.model.EVStationInfo;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EVStationCSVGeneratorService {


    @Value("${evFiles.directory-path}")
    private String evFileDirectoryPath;


    public List<File> generateEVStationCSVs(EVStationInfo evStationInfo) {
        List<File> evFiles = new ArrayList<>();
        evFiles.add(generateEVChargingStationDetailsCSV(evStationInfo.getEvStations()));
        evFiles.add(generateEVChargingStationsCSV(evStationInfo.getEvStations()));
        return evFiles;

    }


    private File generateEVChargingStationDetailsCSV(List<EVStation> evStations) {
        File tempFile = null;
        try {
            File directoryPath = new File(
                    evFileDirectoryPath);

            tempFile = File.createTempFile("ev_file1", ".csv",
                    directoryPath);

            FileWriter outputfile = new FileWriter(tempFile);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] headers = {"CHARGE_STATION_ID", "CHARGER_ID", "BILLING_TYPE",
                    "CHARGER_TYPE", "CHARGER_BID", "CHARGER_SPEED_TYPE", "CHARGER_STATUS",
                    "REALTIME_CHARGER", "CREATED_BY", "CREATED_DATE", "MODIFIED_BY",
                    "MODIFIED_DATE"};
            writer.writeNext(headers);

            for (EVStation evStation : evStations) {

                 if(evStation.getEvStationInfos() != null) {

                     evStation.getEvStationInfos().forEach(evStationCInfo -> {
                         String[] evStationDetails = new String[12];

                         evStationDetails[0] = evStation.getChargeStationId();
                         evStationDetails[1] = evStationCInfo.getChargerID();
                         evStationDetails[2] = evStationCInfo.getBillingType();
                         evStationDetails[3] = evStationCInfo.getChargerType();
                         evStationDetails[4] = evStationCInfo.getChargerBID();
                         evStationDetails[5] = evStationCInfo.getChargerSpeedType();
                         evStationDetails[6] = evStation.getStationStatus();
                         evStationDetails[7] = null;
                         evStationDetails[8] = "admin";
                         evStationDetails[9] = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                         evStationDetails[10] = "admin";
                         evStationDetails[11] = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                         writer.writeNext(evStationDetails);


                     });
                 }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;

    }


    private File generateEVChargingStationsCSV(List<EVStation> evStations) {
        File tempFile = null;
        try {
            File directoryPath = new File(
                    evFileDirectoryPath);

            tempFile = File.createTempFile("ev_file2", ".csv",
                    directoryPath);

            FileWriter outputfile = new FileWriter(tempFile);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] headers = {"CHARGE_STATION_ID", "CHARGE_STATION_NAME", "CHARGE_STATION_ADDRESS",
                    "OPERATION_TIME", "CHARGE_STATION_PHONE", "LATITUDE", "LONGITUDE",
                    "COORD_TYPE", "ALTITUDE", "RTS_LATITUDE", "RTS_LONGITUDE", "RTS_COORD_TYPE",
                    "RTS_ALTITUDE", "CHARGE_STATION_STATUS", "CHARGE_STATION_POI_ID", "ETC_INFO",
                    "CREATED_BY", "CREATED_DATE", "MODIFIED_BY",
                    "MODIFIED_DATE"};
            writer.writeNext(headers);

            for (EVStation evStation : evStations) {


                String[] evStationDetails = new String[20];

                evStationDetails[0] = evStation.getChargeStationId();
                evStationDetails[1] = evStation.getStationName();
                evStationDetails[2] = evStation.getStationAddress();
                evStationDetails[3] = evStation.getOTime();
                evStationDetails[4] = evStation.getStationPhone();
                if(evStation.getStationCoordinates() !=null) {
                    evStationDetails[5] = evStation.getStationCoordinates().getLatitude();
                    evStationDetails[6] = evStation.getStationCoordinates().getLongitude();
                    evStationDetails[7] = evStation.getStationCoordinates().getCoordinateType();
                    evStationDetails[8] = evStation.getStationCoordinates().getAltitude();
                }else {
                    evStationDetails[5] = "";
                    evStationDetails[6] = "";
                    evStationDetails[7] = "";
                    evStationDetails[8] = "";
                }
                if(evStation.getRtsCoordinates() != null) {
                    evStationDetails[9] = evStation.getRtsCoordinates().getLatitude();
                    evStationDetails[10] = evStation.getRtsCoordinates().getLongitude();
                    evStationDetails[11] = evStation.getRtsCoordinates().getCoordinateType();
                    evStationDetails[12] = evStation.getRtsCoordinates().getAltitude();
                }else {
                    evStationDetails[9] = "";
                    evStationDetails[10] = "";
                    evStationDetails[11] = "";
                    evStationDetails[12] = "";
                }
                evStationDetails[13] = evStation.getStationStatus();
                evStationDetails[14] = evStation.getPoiId();
                evStationDetails[15] = evStation.getExtraInfo();
                evStationDetails[16] = "admin";
                evStationDetails[17] = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                evStationDetails[18] = "admin";
                evStationDetails[19] = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                writer.writeNext(evStationDetails);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;

    }


//    public CsvSchema evnStationInfo(EVStationInfo evStationInfo) throws IOException {
//        CsvMapper mapper = new CsvMapper();
//        CsvSchema schema = CsvSchema.builder()
//                .addColumn("CHARGE_STATION_ID")
//                .addColumn("CHARGER_ID")
//                .addColumn("BILLING_TYPE")
//                .addColumn("CHARGER_TYPE")
//                .addColumn("CHARGER_BID")
//                .addColumn("CHARGER_SPEED_TYPE")
//                .addColumn("CHARGER_STATUS")
//                .addColumn("REALTIME_CHARGER")
//                .addColumn("CREATED_BY")
//                .addColumn("CREATED_BY")
//                .addColumn("CREATED_DATE")
//                .addColumn("CREATED_DATE")
//                .addColumn("MODIFIED_BY")
//                .addColumn("MODIFIED_DATE")
//                .build();
//
//        try (StringWriter stringWriter = new StringWriter()) {
//            stringWriter.append("test")
//                    .append(",").append("test2");
//
//            mapper.writer().with(schema).writeValues(stringWriter);
//
//        }
//
//
////        try(StringWriter strW = new StringWriter()){
////            strW.
////            SequenceWriter seqW = MAPPER.writerWithSchemaFor(Person.class)
////                    .writeValues(strW);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        return mapper;
//
//    }


}
