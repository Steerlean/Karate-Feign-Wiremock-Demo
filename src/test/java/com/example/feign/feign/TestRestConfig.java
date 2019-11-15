package com.example.feign.feign;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class TestRestConfig {

    @Bean
    RestTemplate testRestTemplate() throws Exception {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(
          HttpClientBuilder.create().setRetryHandler(new HttpRequestRetryHandler() {
              @Override
              public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                  return true;
              }
          }).build()));
    }

}
