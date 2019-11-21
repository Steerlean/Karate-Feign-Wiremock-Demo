package com.example.feign.feign;

import com.example.feign.controller.BookController;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = { BookControllerAPITest.LocalRibbonClientConfiguration.class })
public class BookControllerAPITest {

    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(wireMockConfig().dynamicPort());

    @Autowired
    public BookController bookController;

    @Before
    public void setup() throws IOException {
        stubFor(get(urlEqualTo("/book/12345")).willReturn(
          aResponse().withStatus(HttpStatus.OK.value()).withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(StreamUtils.copyToString(getClass().getClassLoader().getResourceAsStream("fixtures/book.json"),
              Charset.defaultCharset()))));

        stubFor(get(urlEqualTo("/booktest")).willReturn(
          aResponse().withStatus(HttpStatus.OK.value()).withHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)
            .withBody("DUMMY")));
    }

    @Test
    public void testBookTestString() {
        ResponseEntity<String> stringResponseEntity = bookController.bookTitle();

        assertThat(stringResponseEntity.getBody(), is("DUMMY"));
        assertThat(stringResponseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void testBookTestStringFallback() {
        stubFor(get(urlEqualTo("/booktest")).willReturn(aResponse().withStatus(404)));

        ResponseEntity<String> stringResponseEntity = bookController.bookTitle();

        assertThat(stringResponseEntity.getBody(), is("Fallback123"));
        assertThat(stringResponseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void testFindById() {
        ResponseEntity<Book> bookResponseEntity = bookController.bookSearch("12345");

        Book expectedBookResponse = new Book("12345", "Some Title", "some ISBN");

        assertThat(bookResponseEntity.getBody(), is(expectedBookResponse));
        assertThat(bookResponseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void testFindByIdFallback() {
        stubFor(get(urlEqualTo("/book/12345")).willReturn(aResponse().withStatus(404)));

        ResponseEntity<Book> bookResponseEntity = bookController.bookSearch("12345");

        Book expectedBookResponse = new Book("fallback-id", "default", "default");

        assertThat(bookResponseEntity.getBody(), is(expectedBookResponse));
        assertThat(bookResponseEntity.getStatusCodeValue(), is(200));
    }

    @TestConfiguration
    public static class LocalRibbonClientConfiguration {
        @Bean
        public ServerList<Server> ribbonServerList() {
            return new StaticServerList<>(new Server("localhost", wiremock.port()));
        }
    }
}
