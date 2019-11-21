package com.example.feign.karate.bookservice;

import com.example.feign.karate.TestBase;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BookServiceRunner extends TestBase {
    private static WireMockServer wireMockServer = new WireMockServer();

    @BeforeClass
    public static void setUp() throws Exception {
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(
          get(urlEqualTo("/booktest"))
            .willReturn(aResponse()
              .withStatus(HttpStatus.OK.value())
              .withHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)
              .withBody("DUMMY")));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        wireMockServer.stop();
    }
}
