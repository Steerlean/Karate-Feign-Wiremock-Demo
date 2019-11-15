package com.example.feign.feign;

import com.example.feign.feign.imp.BookClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
  name = "bookstore-server",
  fallback = BookClientFallback.class,
  qualifier = "bookClient"
)
public interface IBookClient {

    @RequestMapping(method = RequestMethod.GET, path = "/book/{id}")
    Book findById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, path = "/booktest")
    String bookTest();
}
