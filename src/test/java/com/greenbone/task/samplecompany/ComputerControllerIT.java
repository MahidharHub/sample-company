package com.greenbone.task.samplecompany;


import com.greenbone.task.samplecompany.domain.Computer;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = SamplecompanyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComputerControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Order(1)
    void createComputerTest() throws Exception {
        Computer computer = new Computer(1L,"00-1B-63-84-55-11", "101.122.212.101", "IJS", "Allotted 9");

        ResponseEntity<Computer> response = restTemplate.postForEntity(createPort("/computers/"),
                computer, Computer.class);

        assertThat(response.getBody().getEmployee().equals("IJS"));
    }

    @Test
    @Order(2)
    void getComputersTest() throws Exception {

        ResponseEntity<Computer[]> response = restTemplate.getForEntity(createPort("/computers/"),
                Computer[].class);

        Computer[] computers = response.getBody();

        assertThat(computers[0].getEmployee().equals("IJS"));

    }

    @Test
    @Order(3)
    void getAllFreeComputersTest() throws Exception {

        Computer computer = new Computer(2L,"00-1B-63-84-55-33", "101.122.212.101", "", "Allotted 1");
        ResponseEntity<Computer> response = restTemplate.postForEntity(createPort("/computers/"),
                computer, Computer.class);

        ResponseEntity<Computer[]> responseGetAllComputers = restTemplate.getForEntity(createPort("/computers/available"),
                Computer[].class);

        Computer[] computers = responseGetAllComputers.getBody();

        assertThat(computers[0].getEmployee().equals(""));
        assertThat(computers[0].getMac().equals("00-1B-63-84-55-33"));
    }

    @Test
    @Order(4)
    void updateComputersTest() throws Exception {

        Computer computer = new Computer("00-1B-63-84-55-11", "101.122.212.101", "KKK", "Allotted 9");

        HttpEntity<Computer> request = new HttpEntity<Computer>(
                new Computer("00-1B-63-84-55-11", "101.122.212.101", "KKK", "Allotted 9"));

        restTemplate.exchange(createPort("/computers/1"), HttpMethod.PUT, request, Void.class);

        System.out.println("createPort ::" + createPort("/computers/1"));

        ResponseEntity<Computer> response = restTemplate.getForEntity(createPort("/computers/1"), Computer.class);

        assertThat(response.getBody().getEmployee().equals("KKK"));
    }

    @Test
    @Order(5)
    void getComputersWithIDTest() throws Exception {

        ResponseEntity<Computer> response = restTemplate.getForEntity(createPort("/computers/1"), Computer.class);

        assertThat(response.getBody().getEmployee().equals("IJS"));
    }

    @Test
    @Order(6)
    void deleteComputersWithIDTest() throws Exception {

        ResponseEntity<Computer[]> responseBeforeDeletion = restTemplate.getForEntity(createPort("/computers/"),
                Computer[].class);
        Computer[] computersListBeforeDeletion = responseBeforeDeletion.getBody();
        int countBeforeDeletion = computersListBeforeDeletion.length;

        restTemplate.delete(createPort("/computers/1"));

        ResponseEntity<Computer[]> responseAfterDeletion = restTemplate.getForEntity(createPort("/computers/"),
                Computer[].class);
        Computer[] computersListAfterDeletion = responseAfterDeletion.getBody();
        int countAfterDeletion = computersListAfterDeletion.length;

        assertThat((countBeforeDeletion-1) == countAfterDeletion );
    }

    @Test
    @Order(7)
    void informAdminServiceTest() throws Exception {

        Computer computer = new Computer("00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 14");
        Computer computer1 = new Computer("00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 15");
        Computer computer2 = new Computer("00-1B-63-84-55-11", "101.122.212.101", "HSS", "Allotted 16");

        ResponseEntity<Computer> response = restTemplate.postForEntity(createPort("/computers/"),
                computer, Computer.class);
        ResponseEntity<Computer> response1 = restTemplate.postForEntity(createPort("/computers/"),
                computer1, Computer.class);
        ResponseEntity<Computer> response2 = restTemplate.postForEntity(createPort("/computers/"),
                computer2, Computer.class);

        assertThat(response.getBody().getEmployee().equals("HSS"));
    }





    private String createPort(final String uri) {
        return "http://localhost:" + port + uri;
    }
}
