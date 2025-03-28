package com.strategicimperatives;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringBootMainTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception {

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/recommendations/1", List.class);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(10, responseEntity.getBody().size());
    }
}