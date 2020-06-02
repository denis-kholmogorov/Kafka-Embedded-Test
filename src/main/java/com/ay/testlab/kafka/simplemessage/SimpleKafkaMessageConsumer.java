package com.ay.testlab.kafka.simplemessage;

import com.ay.testlab.kafka.dto.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class SimpleKafkaMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaMessageConsumer.class);

    private Car message = null;

    @KafkaListener(topics = "${kafka.topic.simpleMessageTopic}")
    public void receive(Car payload) {
        LOGGER.info("Received payload='{}'", payload);
        this.message = payload;
    }

    //added for testing purpose
    public Car message(){
        LOGGER.info("Message has read " + this.message);
        return this.message;
    }
}
