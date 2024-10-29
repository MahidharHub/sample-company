package com.greenbone.task.samplecompany.util;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.service.ComputerService;
import com.greenbone.task.samplecompany.service.InformService;
import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
@Log
public class ComputerCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ComputerService computerService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    InformService informService;

    @Value("${greenbone.csv.path}")
    private String greenboneCSVPath;

    @Value("${greenbone.csv.temp.path}")
    private String greenboneCSVTempPath;

    @Override
    public void run(String... args) throws Exception {
        log.info("Inside ComputerCommandLineRunner :: run");

        Resource resource = resourceLoader.getResource(greenboneCSVPath);
        InputStream inputStream = resource.getInputStream();
        File targetFile = new File(greenboneCSVTempPath);
        Path path = targetFile.toPath();
        Files.copy(
                inputStream,
                targetFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        processCSV(path);

        IOUtils.closeQuietly(inputStream);
    }


    private void processCSV(Path path) {

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
                if (!computerCreated.getEmployee().isBlank())
                    informService.checkComputersAllottedExceeded(computerCreated);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


