package com.greenbone.task.samplecompany.repository;

import com.greenbone.task.samplecompany.domain.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {


    String AVAILABLE_QUERY = "SELECT * FROM COMPUTER where EMPLOYEE='' ";

    // String ASSIGNED_TO_EMPLOYEE_QUERY_1 = "SELECT c.id,c.mac,c.ipAddress,c.employee,c.description FROM Computer c WHERE c.employee=:employeeName ";

    String ASSIGNED_TO_EMPLOYEE_QUERY = "SELECT * FROM COMPUTER where EMPLOYEE=:employeeName ";

    int countByEmployee(String employee);

    @Query(value = AVAILABLE_QUERY, nativeQuery = true)
     Collection<Computer> findAllAvailableComputers();

    @Query(value = ASSIGNED_TO_EMPLOYEE_QUERY, nativeQuery = true)
    //@Query(value = ASSIGNED_TO_EMPLOYEE_QUERY_1)
    Collection<Computer> findAllComputersAssignedToEmployee(@Param("employeeName") String employeeName);

}
