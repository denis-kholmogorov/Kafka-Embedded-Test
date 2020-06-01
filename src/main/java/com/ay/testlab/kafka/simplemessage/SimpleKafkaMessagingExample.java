package com.ay.testlab.kafka.simplemessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * SimpleKafkaMessaging uses Spring Boot CommandLineRunner to send 100 consecutive messages to Kafka server
 */
@Component
public class SimpleKafkaMessagingExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaMessagingExample.class);

    @Autowired
    private SimpleKafkaMessageProducer sender;

    @Autowired
    private SimpleKafkaMessageConsumer consumer;

    @Value("${kafka.topic.simpleMessageTopic}")
    private String topicName;

    public void execute() throws InterruptedException {
        LOGGER.info("SimpleKafkaMessagingExample is executing...");
            sender.send(topicName, "SimpleKafkaMessaging - Message No = 1");
            Thread.sleep(1000);
            read();
    }
    public void read(){
        LOGGER.info("Reading");
        consumer.message();
    }

    @Bean
    public SimpleKafkaMessageProducer simpleKafkaMessageProducer(){
        return new SimpleKafkaMessageProducer();
    }

    @Bean
    public SimpleKafkaMessageConsumer simpleKafkaMessageConsumer(){
        return new SimpleKafkaMessageConsumer();
    }
}
