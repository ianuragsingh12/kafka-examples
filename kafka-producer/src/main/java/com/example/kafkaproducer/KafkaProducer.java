package com.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AnuragSingh
 */
@RestController
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Book> bookKafkaTemplate;

    @GetMapping("/publish/{message}")
    public void publishMessage(@PathVariable("message") String message) {
        kafkaTemplate.send(AppConstants.msgTopic, message);
        log.info("Sent message: {}", message);
    }

    @PostMapping("/publish")
    public String publishBook(@RequestBody Book book) {
        log.info("Publishing book: {}", book);
        bookKafkaTemplate.send(AppConstants.jsonTopic, book);
        return "Book published " + book.toString();
    }
}
