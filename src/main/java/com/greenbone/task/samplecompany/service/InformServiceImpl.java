package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.dto.AdminDTO;
import com.greenbone.task.samplecompany.repository.ComputerRepository;
import com.greenbone.task.samplecompany.util.WebConfig;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Log
public class InformServiceImpl implements InformService {

    @Autowired
    private ComputerRepository repository;

    @Autowired
    WebConfig webConfig;

    @Value("${greenbone.service.uri}")
    private String greenboneServiceUri;

    @Value("${greenbone.service.header.language.key}")
    private String greenboneServiceHeaderLanguageKey;

    @Value("${greenbone.service.header.language.value}")
    private String greenboneServiceHeaderLanguageValue;

    boolean adminServiceInformed = false;


    @Override
    public boolean informAdmin(Computer computer, int count) {

        log.info("Inside InformServiceImpl :: informAdmin");
        webConfig.webClient().post()
                .uri(greenboneServiceUri)
                .header(greenboneServiceHeaderLanguageKey, greenboneServiceHeaderLanguageValue)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(buildAdmin(computer,count)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AdminDTO.class)
                .subscribe(
                        responseEntity -> {
                            log.info("ComputerRepository : informAdmin() : " + "Status Code : " + responseEntity.getStatusCode() + "Employee : " + responseEntity.getBody().getEmployeeAbbreviation() + "Level : "  + responseEntity.getBody().getLevel() + "Message : " +  responseEntity.getBody().getMessage() );
                            adminServiceInformed = true;
                        },
                        error -> {

                            if (error instanceof WebClientResponseException) {
                                WebClientResponseException ex = (WebClientResponseException) error;
                                HttpStatusCode status = ex.getStatusCode();
                               log.info("InformServiceImpl : informAdmin() :  Error Status code :" + ex.getStatusCode() + "Error Message :" + ex.getMessage() );

                            } else {
                                log.info("InformServiceImpl : informAdmin() : Inform Service not available:"  + "Error Message :" + error.getMessage() );

                            }
                        }
                );

        return adminServiceInformed;
    }

    @Override
    public boolean checkComputersAllottedExceeded(Computer computer) {
        log.info("Inside InformServiceImpl :: checkComputersAllottedExceeded");
        boolean informAdminService = false;
        int count = repository.countByEmployee(computer.getEmployee());
        if (count>=3){
            informAdminService = informAdmin(computer, count);
        }
        return informAdminService;
    }

    private AdminDTO buildAdmin(Computer computer, int count){
        log.info("Inside InformServiceImpl :: buildAdmin");
        return  new AdminDTO.AdminBuilder()
                .setMessage("reached limit of " + count + " computers allotment")
                .setLevel("warning")
                .setEmployeeAbbreviation(computer.getEmployee())
                .build();
    }
}
