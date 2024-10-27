package com.greenbone.task.samplecompany;

import com.greenbone.task.samplecompany.domain.Computer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;




public class SupplyComputers {


        public List<Computer> getComputerRecords() throws FileNotFoundException {
            SupplyComputers supplyComputers = new SupplyComputers();
            File file = ResourceUtils.getFile("classpath:computers.csv");
            Path path = file.toPath();  //Paths.get(ClassLoader.getSystemResource("src/test/resources/computers.csv").toURI());
            List<Computer> computerList = new ArrayList<Computer>();
            try (
                    Reader reader = Files.newBufferedReader(path);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            ) {

                for (CSVRecord csvRecord : csvParser) {
                    Computer computer = new Computer();
                    computer.setId(Long.parseLong(csvRecord.get(0)));
                    computer.setMac(csvRecord.get(1));
                    computer.setIpAddress(csvRecord.get(2));
                    computer.setEmployee(csvRecord.get(3));
                    computer.setDescription(csvRecord.get(4));
                    computerList.add(computer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
             return computerList;
        }

    public List<Computer> getFreeComputerRecords() throws FileNotFoundException {
        SupplyComputers supplyComputers = new SupplyComputers();
        File file = ResourceUtils.getFile("classpath:computers.csv");
        Path path = file.toPath();  //Paths.get(ClassLoader.getSystemResource("src/test/resources/computers.csv").toURI());
        List<Computer> computerList = new ArrayList<Computer>();
        try (
                Reader reader = Files.newBufferedReader(path);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {

            for (CSVRecord csvRecord : csvParser) {
                if(StringUtils.isBlank(csvRecord.get(3))){
                Computer computer = new Computer();
                computer.setId(Long.parseLong(csvRecord.get(0)));
                computer.setMac(csvRecord.get(1));
                computer.setIpAddress(csvRecord.get(2));
                computer.setEmployee(csvRecord.get(3));
                computer.setDescription(csvRecord.get(4));
                computerList.add(computer);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return computerList;
    }

    public Computer getOneComputerRecord() throws FileNotFoundException {
        SupplyComputers supplyComputers = new SupplyComputers();
        File file = ResourceUtils.getFile("classpath:computers.csv");
        Path path = file.toPath();  //Paths.get(ClassLoader.getSystemResource("src/test/resources/computers.csv").toURI());
        Computer computer = new Computer();
        try (
                Reader reader = Files.newBufferedReader(path);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            CSVRecord csvRecord = csvParser.getRecords().get(0);
                    computer.setId(Long.parseLong(  csvRecord.get(0)));
                    computer.setMac(  csvRecord.get(1));
                    computer.setIpAddress(  csvRecord.get(2));
                    computer.setEmployee(  csvRecord.get(3));
                    computer.setDescription(  csvRecord.get(4));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return computer;
    }

}
