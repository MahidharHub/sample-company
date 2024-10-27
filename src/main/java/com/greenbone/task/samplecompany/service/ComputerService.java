package com.greenbone.task.samplecompany.service;


import com.greenbone.task.samplecompany.domain.Computer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ComputerService {

    List<Computer> getAllComputers();

    Optional<Computer> getComputer(Long id);

    Computer createComputer(Computer computer);

    Computer updateComputer(Long id, Computer computer);

    void deleteComputer(Long id);

    Collection<Computer> getAvailableComputers();



}
