package com.example.feign.feign;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = { BookClientTest.LocalRibbonClientConfiguration.class })
public class BookClientTest {

    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(wireMockConfig().dynamicPort());

    @Autowired
    public IBookClient bookClient;

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
        String result = bookClient.bookTest();

        assertNotNull("should not be null", result);
        assertThat(result, is("DUMMY"));
    }

    @Test
    public void testBookTestStringFallback() {
        stubFor(get(urlEqualTo("/booktest")).willReturn(aResponse().withStatus(404)));

        String result = bookClient.bookTest();

        assertNotNull("should not be null", result);
        assertThat(result, is("Fallback123"));
    }

    @Test
    public void testFindById() {
        Book result = bookClient.findById("12345");

        assertNotNull("should not be null", result);
        assertThat(result.getId(), is("12345"));
    }

    @Test
    public void testFindByIdFallback() {
        stubFor(get(urlEqualTo("/book/12345")).willReturn(aResponse().withStatus(404)));

        Book result = bookClient.findById("12345");

        assertNotNull("should not be null", result);
        assertThat(result.getId(), is("fallback-id"));
    }

    @TestConfiguration
    public static class LocalRibbonClientConfiguration {
        @Bean
        public ServerList<Server> ribbonServerList() {
            return new StaticServerList<>(new Server("localhost", wiremock.port()));
        }
    }
}
