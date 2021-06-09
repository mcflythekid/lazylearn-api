package com.lazylearn.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazylearn.api.config.env.WiredEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

/**
 * @author McFly the Kid
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {
    private static String[] ARGS;

    public static void main(String[] args) {
        ARGS = args;
        SpringApplication.run(App.class, args);
    }

    @Bean
    public WiredEnv wiredEnv() throws IOException {
        return new ObjectMapper().readValue(new File(ARGS[0]), WiredEnv.class);
    }
}
