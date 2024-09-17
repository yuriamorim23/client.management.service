package com.example.client.managemante.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "your_topic_name", groupId = "your-group-id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}