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
    private static WiredEnv WIRED_ENV;

    public static void main(String[] args) throws IOException {
        WIRED_ENV = new ObjectMapper().readValue(new File(args[0]), WiredEnv.class);
        System.setProperty("LOG_DIR", WIRED_ENV.getLogDir());

        SpringApplication.run(App.class, args);
    }

    @Bean
    public WiredEnv wiredEnv() {
        return WIRED_ENV;
    }
}
