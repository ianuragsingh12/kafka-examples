package com.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AnuragSingh
 */
@RestController
public class KafkaMsgProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaMsgProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String msgTopic = "msg_topic";

    @GetMapping("/publish/{message}")
    public void publishMessage(@PathVariable("message") String message) {
        kafkaTemplate.send(msgTopic, message);
        log.info("Sent message: {}", message);
    }
}
