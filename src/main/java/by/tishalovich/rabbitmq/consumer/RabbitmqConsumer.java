package by.tishalovich.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConsumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.string-queue.name}"})
    public void consume(Message<String> message) {
        LOGGER.info(String.format("Consumed message: %s", message.getPayload()));
    }

}
