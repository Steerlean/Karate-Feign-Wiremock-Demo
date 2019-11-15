package com.example.feign.feign;
//
//import com.github.tomakehurst.wiremock.junit.WireMockRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
//  "feign.hystrix.enabled=true"
//})
////@ContextConfiguration(classes = {WeatherClientTest.LocalRibbonClientConfiguration.class})
public class WeatherClientTest {
//    //    @ClassRule
//    //    public WireMockClassRule wireMockRule = new WireMockClassRule(wireMockConfig().port(8089));
//    //        public static WireMockClassRule wiremock = new WireMockClassRule(WireMockConfiguration.options().port(8089));
//    //    public static WireMockClassRule wiremock = new WireMockClassRule(wireMockConfig());
//
//    //    @Autowired
//    //    private RestTemplate testRestTemplate;
//
//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
//
//    @Autowired
//    private IWeatherClient weatherClient;
//
//    @Test
//    public void contextLoadsTest() throws Exception {
//        stubFor(get(urlEqualTo("/raw/56Df0itX"))
//          //        stubFor(get(urlMatching(".*"))
//          .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/plain").withBody("Hello World!")));
//
//        ResponseEntity<String> weather = this.weatherClient.getWeather();
//
//        assertEquals("Hello World!", weather.getBody());
//    }
//
//    //    @TestConfiguration
//    //    public static class LocalRibbonClientConfiguration {
//    //        @Bean
//    //        public ServerList<Server> ribbonServerList() {
//    //            return new StaticServerList<>(new Server("localhost", wireMockRule.port()));
//    //        }
//    //    }
//
//    //    @Test
//    //    public void contextLoads() throws Exception {
//    //        //        stubFor(get(urlEqualTo("https://pastebin.com/raw/56Df0itX"))
//    //        stubFor(get(urlMatching(".*")).willReturn(aResponse()
//    //          //            .proxiedFrom("https://pastebin.com")
//    //          .withHeader("Content-Type", "text/plain").withBody("Hello World!")));
//    //
//    //        RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
//    //          HttpClientBuilder.create().setRetryHandler(new HttpRequestRetryHandler() {
//    //              @Override
//    //              public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//    //                  return true;
//    //              }
//    //          }).build()));
//    //
//    //        RequestEntity<String> request = new RequestEntity<>("things", HttpMethod.GET,
//    //          URI.create("https://pastebin.com/raw/56Df0itX"));
//    //
//    //        ResponseEntity<String> weather = testRestTemplate.exchange(request, String.class);
//    //
//    //        //        ResponseEntity<String> weather = this.weatherClient.getWeather();
//    //
//    //        assertEquals("Hello World!", weather.getBody());
//    //    }
//
}