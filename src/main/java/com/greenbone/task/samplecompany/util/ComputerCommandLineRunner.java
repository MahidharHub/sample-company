package com.greenbone.task.samplecompany.util;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.service.ComputerService;
import com.greenbone.task.samplecompany.service.InformService;
import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Log
public class ComputerCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ComputerService computerService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    InformService informService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Inside ComputerCommandLineRunner :: run");
        Resource resource = resourceLoader.getResource("classpath:computers.csv");
        InputStream inputStream = resource.getInputStream();
        File file = new File("c1.csv");
        FileUtils.copyInputStreamToFile(inputStream, file);
        Path path = file.toPath();



        try (
                Reader reader = Files.newBufferedReader(path);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {

            for (CSVRecord csvRecord : csvParser) {
                Computer computer = new Computer();
                computer.setMac(csvRecord.get(1));
                computer.setIpAddress(csvRecord.get(2));
                computer.setEmployee(csvRecord.get(3));
                computer.setDescription(csvRecord.get(4));
                Computer computerCreated = computerService.createComputer(computer);
                if(!computerCreated.getEmployee().isBlank())
                    informService.checkComputersAllottedExceeded(computerCreated);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
