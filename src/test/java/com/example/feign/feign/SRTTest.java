package com.example.feign.feign;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SRTTest {

    ExecutorService executorService = Executors.newFixedThreadPool(200);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089));

    @Test
    public void restTemplate() throws Exception {
        stubFor(get(urlEqualTo("/raw/56Df0itX"))
//          .withRequestBody(equalTo("things"))
          .willReturn(aResponse().withStatus(200)));

        final RestTemplate restTemplate = new RestTemplate();

        List<Future<Integer>> futures = newArrayList();
        for (int i = 0; i < 2000; i++) {
            final int count = i;
            futures.add(executorService.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    RequestEntity<String> request =
                      new RequestEntity<>(
//                        "things",
                        HttpMethod.GET,
//                        URI.create("http://localhost:" + "8080" + "/put-this")
                        URI.create("https://pastebin.com/raw/56Df0itX")
                      );
                    ResponseEntity<String> response = restTemplate.exchange(request, String.class);
                    System.out.println("Sent " + count);

                    return response.getStatusCode().value();
                }
            }));
        }

        for (Future<Integer> future: futures) {
            assertThat(future.get(), is(200));
        }
    }
}

