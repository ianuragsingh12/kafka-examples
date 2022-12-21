package com.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AnuragSingh
 */
@RestController
public class KafkaJsonProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaJsonProducer.class);

    @Autowired
    private KafkaTemplate<String, Book> kafkaTemplate;

    private final String jsonTopic = "json_topic";

    @PostMapping("/publish")
    public String reportCurrentTime(@RequestBody Book book) {
        log.info("Publishing book: {}", book);
        kafkaTemplate.send(jsonTopic, book);
        return "Book published " + book.toString();
    }
}
