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

    private final String jsonTopic = "json_topic";
    private final String msgTopic = "msg_topic";

    private final String msgGroupId = "msg_group_id";
    private final String jsonGroupId = "json_group_id";

    @KafkaListener(topics = msgTopic, groupId = msgGroupId)
    public void consume(String msg) {
        log.info("Received String: {}", msg);
    }

    @KafkaListener(topics = jsonTopic, groupId = jsonGroupId, containerFactory = "userKafkaListenerContainerFactory")
    public void consumeJson(Book book) {
        log.info("Received Json: {}", book);
    }
}
