package com.ay.testlab.kafka;

import com.ay.testlab.kafka.simplemessage.SimpleKafkaMessagingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private SimpleKafkaMessagingExample simpleKafkaMessagingExample;

    @Bean
    @Profile("!test")
    public CommandLineRunner batchMessageConsumerRunner() {
        return args -> {
            simpleKafkaMessagingExample.execute();
        };
    }
}