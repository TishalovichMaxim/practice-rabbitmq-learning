package by.tishalovich.rabbitmq.controller;

import by.tishalovich.rabbitmq.model.User;
import by.tishalovich.rabbitmq.producer.RabbitmqJsonProducer;
import by.tishalovich.rabbitmq.producer.RabbitmqProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitmqProducer producer;

    private final RabbitmqJsonProducer jsonProducer;

    @PostMapping("{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping
    public ResponseEntity<User> sendUser(@RequestBody User user) {
        Random random = new Random();
        user.setId(random.nextInt());

        jsonProducer.sendJsonMessage(user);

        return ResponseEntity.ok(user);
    }

}
