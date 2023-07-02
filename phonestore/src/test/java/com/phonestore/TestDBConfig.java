package com.phonestore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableAutoConfiguration
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
public class TestDBConfig {
 
 @Value("${spring.datasource.driver-class-name}")
 String jdbcDriverName;
 @Value("${spring.datasource.url}")
 String url;
 @Value("${spring.datasource.username}")
 String user;
 @Value("${spring.datasource.password}")
 String password;

}

