package com.example.feign.karate;

import com.intuit.karate.junit4.Karate;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Karate.class)
public abstract class TestBase {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("demo.server.port", 9090 + "");
    }
}
