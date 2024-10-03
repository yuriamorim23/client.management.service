package com.example.client.managemante.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private static final String TOPIC = "your_topic_name";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/publish")
    public String sendMessageToKafka(@RequestParam String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message sent to Kafka Topic: " + TOPIC;
    }
}