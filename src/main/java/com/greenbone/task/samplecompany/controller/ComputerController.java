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


    @GetMapping("/")
    public Iterable<Computer> listAllComputers() {
        log.info("Inside ComputerController :" + " listAllComputers");
        return computerService.getAllComputers();

    }


    @PostMapping("/")
    public ResponseEntity<Computer> createComputer(@Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " createComputer");

        Computer computerCreated = computerService.createComputer(computer);
        informService.checkComputersAllottedExceeded(computerCreated);
        return ResponseEntity.ok().body(computerCreated);

    }

    @GetMapping("/{id}")
    public Computer getComputerById(@PathVariable(value = "id") long id) {
        log.info("Inside ComputerController :" + " getComputerById");
        Optional<Computer> computer = computerService.getComputer(id);
        if (!computer.isPresent()) throw new ComputerNotFoundException("id" + id);
        return computer.get();

    }

    @PutMapping("/{id}")
    public Computer updateComputer(@PathVariable(value = "id") long id, @Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " updateComputer");
        return computerService.updateComputer(id, computer);
    }

    @DeleteMapping("/{id}")
    public void deleteComputer(@PathVariable(value = "id") Long id) {
        log.info("Inside ComputerController :" + " deleteComputer");
        computerService.deleteComputer(id);
    }

    @GetMapping("/available")
    public Collection<Computer> availableComputers() {
        log.info("Inside ComputerController :" + " availableComputers");
        return computerService.getAvailableComputers();
    }




}
