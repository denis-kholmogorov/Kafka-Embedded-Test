package com.ay.testlab.kafka;

import com.ay.testlab.kafka.dto.Car;
import com.ay.testlab.kafka.simplemessage.SimpleKafkaMessageConsumer;
import com.ay.testlab.kafka.simplemessage.SimpleKafkaMessageProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
public class SimpleKafkaMessageTest {

    @Value("${kafka.topic.simpleMessageTopic}")
    private String topicName;

    private KafkaTemplate<String, Car> template;

    @Autowired
    private SimpleKafkaMessageConsumer consumer;

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1,false, "testingTopic");

    @Before
    public void setUp() throws Exception {
        /* Create senderDefault*/
        Map<String, Object> senderProps = KafkaTestUtils.senderProps(kafkaEmbedded.getBrokersAsString());
        senderProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        ProducerFactory<String, Car> producerFactory = new DefaultKafkaProducerFactory<>(senderProps);
        template = new KafkaTemplate<>(producerFactory);
        template.setDefaultTopic(topicName);

    }

    @Test
    public void testSendReceive() throws Exception {
        template.sendDefault(new Car("Red", 2, 250));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals("2", consumer.message().getCountDoors().toString());
    }

    @Test
    public void testReceive() throws Exception {
        template.sendDefault(new Car("Green1", 4, 80));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals("Green1", consumer.message().getColor());
    }



}
