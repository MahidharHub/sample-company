package com.greenbone.task.samplecompany.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.greenbone.task.samplecompany.SupplyComputers;
import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.service.ComputerService;
import com.greenbone.task.samplecompany.service.InformService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(ComputerController.class)

class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputerService computerService;

    @MockBean
    private InformService informService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void listAllComputersTest() throws Exception {

        Mockito.when(
                computerService.getAllComputers()).thenReturn(new SupplyComputers().getComputerRecords());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(9)))
                .andExpect(jsonPath("$[0].employee", is("HSS")));

    }


    @Test
    void getSingleComputersTest() throws Exception {

        Mockito.when(
                computerService.getComputer(1L)).thenReturn(Optional.of(new SupplyComputers().getOneComputerRecord()));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computers/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee", is("HSS")));
    }

    @Test
    void getAvailableComputersTest() throws Exception {

        Mockito.when(
                computerService.getAvailableComputers()).thenReturn( new SupplyComputers().getFreeComputerRecords());


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computers/available")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].mac", is("00-1B-63-84-55-11")));



    }


    @Test
    void createComputerTest() throws Exception {

        Computer computer   = new  Computer(1L, "00-1B-63-84-55-C7","192.168.123.191","","Alllotment 3");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/computers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(computer))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                         .andDo(print());

    }



}
