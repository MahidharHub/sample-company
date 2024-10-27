package com.greenbone.task.samplecompany.repository;

import com.greenbone.task.samplecompany.domain.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Respository to handle all the queries to interact with database
 */
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {


    String AVAILABLE_QUERY = "SELECT * FROM COMPUTER where EMPLOYEE='' ";
    String ASSIGNED_TO_EMPLOYEE_QUERY = "SELECT * FROM COMPUTER where EMPLOYEE=:employeeName ";

    /**
     * Method to return assigned computer to a specific employee
     * @param employee
     * @return
     */
    int countByEmployee(String employee);

    /**
     * Method to return all available computers
     * @return
     */
    @Query(value = AVAILABLE_QUERY, nativeQuery = true)
     Collection<Computer> findAllAvailableComputers();

    /**
     * Method to return all assigned computers to a specific employee
     * @param employeeName
     * @return
     */
    @Query(value = ASSIGNED_TO_EMPLOYEE_QUERY, nativeQuery = true)
    //@Query(value = ASSIGNED_TO_EMPLOYEE_QUERY_1)
    Collection<Computer> findAllComputersAssignedToEmployee(@Param("employeeName") String employeeName);

}
