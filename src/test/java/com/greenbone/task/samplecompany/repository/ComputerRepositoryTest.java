package com.greenbone.task.samplecompany.repository;


import com.greenbone.task.samplecompany.domain.Computer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:computerdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ComputerRepositoryTest {

    @Autowired
    private ComputerRepository computerRepository;

    Computer computer = new Computer(5L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 14");
    Computer computer1 = new Computer(6L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 15");
    Computer computer2 = new Computer(7L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 16");

    Computer computer3 = new Computer(5L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Free 1");
    Computer computer4 = new Computer(6L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Free 2");
    Computer computer5 = new Computer(7L, "00-1B-63-84-55-11", "101.122.212.101", "HSS", "Free 3");


    @BeforeEach
    void setUp() {


        computerRepository.save(computer);
        computerRepository.save(computer1);
        computerRepository.save(computer2);
        computerRepository.save(computer3);
        computerRepository.save(computer4);
        computerRepository.save(computer5);
    }

    @AfterEach
    public void tearDown() {
        // Release test data after each test method
        computerRepository.delete(computer);
        computerRepository.delete(computer1);
        computerRepository.delete(computer2);
        computerRepository.delete(computer3);
        computerRepository.delete(computer4);
        computerRepository.delete(computer5);
    }

    @Test
    void findAllComputersTest() {
        List<Computer> computerList = computerRepository.findAll();
        assertThat(computerList.size() == 6);

    }

    @Test
    void countByEmployeeTest() {
        int  computerCount = computerRepository.countByEmployee("HSS");
        assertThat(computerCount == 3);

    }


    @Test
    void findAllAvailableComputersTest() {
        Collection<Computer> computerList  = computerRepository.findAllAvailableComputers();
        assertThat(computerList.size() == 3);

    }

}
