package com.greenbone.task.samplecompany.controller;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.exception.ComputerNotFoundException;
import com.greenbone.task.samplecompany.service.ComputerService;
import com.greenbone.task.samplecompany.service.InformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    /**
     * List all computers from the database
     * @return
     */

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) })})
    @Operation(summary = "List all computers from the database")
    public Iterable<Computer> listAllComputers() {
        log.info("Inside ComputerController :" + " listAllComputers");
        return computerService.getAllComputers();

    }

    /**
     * Create a new computer in the database
     * @param computer
     * @return
     */

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) })})
    @Operation(summary = "Create a new computer in the database")
    public ResponseEntity<Computer> createComputer(@Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " createComputer");

        Computer computerCreated = computerService.createComputer(computer);
        informService.checkComputersAllottedExceeded(computerCreated);
        return ResponseEntity.ok().body(computerCreated);

    }

    /**
     * Retrieve computer by id
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) }),
            @ApiResponse(responseCode = "404",  description = "Resource Not Found",  content = @Content)
    })
    @Operation(summary = "Retrieve computer by id")
    public Computer getComputerById(@PathVariable(value = "id") long id) {
        log.info("Inside ComputerController :" + " getComputerById");
        Optional<Computer> computer = computerService.getComputer(id);
        if (!computer.isPresent()) throw new ComputerNotFoundException("id" + id);
        return computer.get();

    }

    /**
     * Update computer with id
     * @param id
     * @param computer
     * @return
     */
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) })
    })
    @Operation(summary = "Update computer with id")
    public Computer updateComputer(@PathVariable(value = "id") long id, @Valid @RequestBody Computer computer) {
        log.info("Inside ComputerController :" + " updateComputer");
        Computer computerUpdated = computerService.updateComputer(id, computer);
        informService.checkComputersAllottedExceeded(computerUpdated);
        return computerUpdated;
    }

    /**
     * Delete computer by id
     * @param id
     */

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) })
    })
    @Operation(summary = "Delete computer with id")
    public void deleteComputer(@PathVariable(value = "id") Long id) {
        log.info("Inside ComputerController :" + " deleteComputer");
        computerService.deleteComputer(id);
    }


    /**
     * Get all available free computers
     * @return
     */

    @GetMapping("/available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) }),
            @ApiResponse(responseCode = "404",  description = "Resource Not Found",  content = @Content)
    })
    @Operation(summary = "Get all available free computers")
    public Collection<Computer> availableComputers() {
        log.info("Inside ComputerController :" + " availableComputers");
        return computerService.getAvailableComputers();
    }

    /**
     * Get all assigned computers to a specific employee
      * @param employee
     * @return
     */
    @GetMapping("/assigned/{employee}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Computer.class)) }),
            @ApiResponse(responseCode = "404",  description = "Resource Not Found",  content = @Content)
    })
    @Operation(summary = "Get all assigned computers to a specific employee")
    public Collection<Computer> assignedComputersToEmployee(@NotNull  @PathVariable(value = "employee") String employee) {
        log.info("Inside ComputerController :" + " assignedComputers");
        return computerService.getAssignedComputersToEmployee(employee);
    }




}
