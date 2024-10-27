package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;
import com.greenbone.task.samplecompany.exception.InvalidEmployeeException;
import com.greenbone.task.samplecompany.repository.ComputerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerServiceImpl implements ComputerService{
    @Autowired
    private ComputerRepository repository;


    /**
     *
     * @return
     */
    @Override
    public List<Computer> getAllComputers() {
        return repository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */

    @Override
    public Optional<Computer> getComputer(Long id) {
        return repository.findById(id);
    }

    /**
     *
     * @param computer
     * @return
     */

    @Override
    public Computer createComputer(Computer computer) {

        return repository.save(checkEmployeeValidation(computer));
    }

    /**
     *
     * @param id
     * @param computer
     * @return
     */
    @Override
    public Computer updateComputer(Long id, Computer computer) {
        computer.setId(id);
        return repository.save(computer);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteComputer(Long id) {
        repository.deleteById(id);

    }

    /**
     *
     * @return
     */

    @Override
    public Collection<Computer> getAvailableComputers() {
        return repository.findAllAvailableComputers();
    }

    /**
     *
     * @param computer
     * @return
     */
    private Computer checkEmployeeValidation(Computer computer){
        computer.setEmployee(computer.getEmployee().replaceAll("\\s", ""));
        if(!computer.getEmployee().equals(StringUtils.EMPTY) &&  (computer.getEmployee().length()!=3)){
            throw new InvalidEmployeeException("Employee Name length should be in 3 characters");
        }

        return computer;
    }


}
