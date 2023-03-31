package com.example.springbootdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    private static final int devServerPort = 8080;
    private static final int prodServerPort = 8081;
    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(devServerPort);
    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(prodServerPort);

    private String getBodyFromContainer(GenericContainer<?> app, int serverPort) {
        ResponseEntity<String> appEntity = restTemplate.getForEntity("http://localhost:" + app.getMappedPort(serverPort) + "/profile", String.class);
        return appEntity.getBody();
    }

    @Test
    void prodAppTest() {
        // arrange
        String answer = "Current profile is production";

        // act + assert
        Assertions.assertEquals(answer, getBodyFromContainer(prodApp, prodServerPort));
    }

    @Test
    void devAppTest() {
        // arrange
        String answer = "Current profile is dev";

        // act + assert
        Assertions.assertEquals(answer, getBodyFromContainer(devApp, devServerPort));
    }

}
