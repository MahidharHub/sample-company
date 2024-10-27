package com.greenbone.task.samplecompany.controller;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.exception.ComputerNotFoundException;
import com.greenbone.task.samplecompany.service.ComputerService;
import com.greenbone.task.samplecompany.service.InformService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/computers")
@Log
public class ComputerController {


    @Autowired
    ComputerService computerService;

    @Autowired
    InformService informService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Computer> listAllComputers() {
        log.info("Inside ComputerController :" + " listAllComputers");
        return computerService.getAllComputers();

    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Computer> createComputer(@Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " createComputer");

        Computer computerCreated = computerService.createComputer(computer);
        informService.checkComputersAllottedExceeded(computerCreated);
        return ResponseEntity.ok().body(computerCreated);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Computer getComputerById(@PathVariable(value = "id") long id) {
        log.info("Inside ComputerController :" + " getComputerById");
        Optional<Computer> computer = computerService.getComputer(id);
        if (!computer.isPresent()) throw new ComputerNotFoundException("id" + id);
        return computer.get();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Computer updateComputer(@PathVariable(value = "id") long id, @Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " updateComputer");
        return computerService.updateComputer(id, computer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComputer(@PathVariable(value = "id") Long id) {
        log.info("Inside ComputerController :" + " deleteComputer");
        computerService.deleteComputer(id);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public Collection<Computer> availableComputers() {
        log.info("Inside ComputerController :" + " availableComputers");
        return computerService.getAvailableComputers();
    }




}
