package by.tishalovich.rabbitmq.controller;

import by.tishalovich.rabbitmq.producer.RabbitmqProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitmqProducer producer;

    @PostMapping("{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

}
