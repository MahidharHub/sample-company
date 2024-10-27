package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.repository.ComputerRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Log
@ExtendWith(MockitoExtension.class)
class ComputerServiceTest {

    @InjectMocks
    ComputerServiceImpl computerService;

    @Mock
    ComputerRepository computerRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testGetAllComputers() {


        log.info("Success");
        List<Computer> list = new ArrayList<>();
        Computer computer  = new Computer(1L, "00-1B-63-84-55-B7","192.168.123.191","MMS","Alllotment 3");
        Computer computer1  = new Computer(2L, "00-1B-63-84-55-B7","192.168.123.191","MMS","Alllotment 3");
        Computer computer2  = new Computer(3L, "00-1B-63-84-55-B7","192.168.123.191","MMS","Alllotment 3");
        Computer computer3  = new Computer(4L, "00-1B-63-84-55-B7","192.168.123.191","MMS","Alllotment 3");
        list.add(computer);
        list.add(computer1);
        list.add(computer2);
        list.add(computer3);

        when(computerRepository.findAll()).thenReturn(list);

        //test
        List<Computer> computerList = computerService.getAllComputers();


        assertEquals(4, computerList.size());
        verify(computerRepository, times(1)).findAll();
    }

    @Test
    void testCreateComputer() {


        log.info("inside testCreateComputer ");
        Computer computerNew  = new Computer(1L, "00-1B-63-84-55-B7","192.168.123.191","MMS","Alllotment 3");

        //Mocking  computerRepository
        when(computerRepository.save(computerNew)).thenReturn(computerNew);

        //When
       Computer computerResponse = computerService.createComputer(computerNew);

        //Then
        assertEquals(computerNew.getEmployee(),computerResponse.getEmployee());
    }

    @Test
    void testUpdateComputer() {


        log.info("Inside testUpdateComputer");
        Computer computerNew  = new Computer(1L, "00-1B-63-84-55-B7","192.168.123.191","KKR","Alllotment 4");

        //Mocking  computerRepository
        when(computerRepository.save(computerNew)).thenReturn(computerNew);

        //When
        Computer computerResponse = computerService.updateComputer(1L,computerNew);

        //Then
        assertEquals(computerNew.getEmployee(),computerResponse.getEmployee());
    }

    @Test
    void testDeleteComputer() {

        log.info("Success");
        final Long id = 1l;

        //When
        computerService.deleteComputer(id);

        //Then
        Mockito.verify(computerRepository).deleteById(id);
    }


    @Test
    void testGetAvailableComputers() {

        List<Computer> list = new ArrayList<>();
        Computer computer  = new Computer(1L, "00-1B-63-84-55-B7","192.168.123.191","","Alllotment 3");
        Computer computer1  = new Computer(2L, "00-1B-63-84-55-B7","192.168.123.191","","Alllotment 3");

        list.add(computer);
        list.add(computer1);

        when(computerRepository.findAllAvailableComputers()).thenReturn(list);

        //test
        Collection<Computer> computerList = computerService.getAvailableComputers();

        assertEquals(list.size(), computerList.size());


    }





}
