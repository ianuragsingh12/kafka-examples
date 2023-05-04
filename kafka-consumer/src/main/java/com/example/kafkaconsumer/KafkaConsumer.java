package com.example.kafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author AnuragSingh
 */
@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = AppConstants.msgTopic, groupId = AppConstants.msgGroupId)
    public void consume(String msg) {
        log.info("Received String: {}", msg);
    }

    @KafkaListener(topics = AppConstants.jsonTopic, groupId = AppConstants.jsonGroupId, containerFactory = "kafkaListenerBookContainerFactory")
    public void consumeJson(Book book) {
        log.info("Received Json: {}", book);
    }
}
