package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.exception.AssignedComputersNotFoundException;
import com.greenbone.task.samplecompany.exception.AvailableComputersNotFoundException;
import com.greenbone.task.samplecompany.exception.InvalidEmployeeException;
import com.greenbone.task.samplecompany.repository.ComputerRepository;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private ComputerRepository repository;


    /**
     * @return
     */
    @Override
    public List<Computer> getAllComputers() {
        log.info("Inside ComputerServiceImpl :: getAllComputers");
        return repository.findAll();
    }

    /**
     * @param id
     * @return
     */

    @Override
    public Optional<Computer> getComputer(Long id) {
        log.info("Inside ComputerServiceImpl :: getComputer");
        return repository.findById(id);
    }

    /**
     * @param computer
     * @return
     */

    @Override
    public Computer createComputer(Computer computer) {
        log.info("Inside ComputerServiceImpl :: createComputer");
        return repository.save(checkEmployeeValidation(computer));
    }

    /**
     * @param id
     * @param computer
     * @return
     */
    @Override
    public Computer updateComputer(Long id, Computer computer) {
        log.info("Inside ComputerServiceImpl :: updateComputer");
        computer.setId(id);
        return repository.save(checkEmployeeValidation(computer));
    }

    /**
     * @param id
     */
    @Override
    public void deleteComputer(Long id) {
        log.info("Inside ComputerServiceImpl :: deleteComputer");
        repository.deleteById(id);

    }

    /**
     * @return
     */

    @Override
    public Collection<Computer> getAvailableComputers() {
        log.info("Inside ComputerServiceImpl :: getAvailableComputers");
        Collection<Computer> computerCollection = repository.findAllAvailableComputers();
        if (computerCollection.isEmpty())
            throw new AvailableComputersNotFoundException("No available computers  ");
        return computerCollection;
    }

    @Override
    public Collection<Computer> getAssignedComputersToEmployee(String employee) {
        log.info("Inside ComputerServiceImpl :: getAssignedComputersToEmployee");
        Collection<Computer> computerCollection = repository.findAllComputersAssignedToEmployee(employee);
        if (computerCollection.isEmpty())
            throw new AssignedComputersNotFoundException("No assigned computers for employee : " + employee);
        return computerCollection;

    }

    /**
     * @param computer
     * @return
     */
    private Computer checkEmployeeValidation(Computer computer) {
        log.info("Inside ComputerServiceImpl :: checkEmployeeValidation");
        computer.setEmployee(computer.getEmployee().replaceAll("\\s", ""));
        if (!computer.getEmployee().equals(StringUtils.EMPTY) && (computer.getEmployee().length() != 3)) {
            throw new InvalidEmployeeException("Employee Name length should be in 3 characters");
        }

        return computer;
    }


}
