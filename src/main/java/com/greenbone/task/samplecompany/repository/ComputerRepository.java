package com.greenbone.task.samplecompany.repository;

import com.greenbone.task.samplecompany.domain.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {


    String AVAILABLE_QUERY = "SELECT * FROM COMPUTER where EMPLOYEE='' ";

    int countByEmployee(String employee);

    @Query(value = AVAILABLE_QUERY, nativeQuery = true)
     Collection<Computer> findAllAvailableComputers();

}
