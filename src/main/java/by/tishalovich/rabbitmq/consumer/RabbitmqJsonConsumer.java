package by.tishalovich.rabbitmq.consumer;

import by.tishalovich.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqJsonConsumer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RabbitmqJsonConsumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.json-queue.name}"})
    public void consumeJsonMessage(User user) {
        LOGGER.info(String.format("Consumed json message: %s", user.toString()));
    }

}
