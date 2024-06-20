package by.tishalovich.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitmqProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing-key.name}")
    private String routingKeyName;

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RabbitmqProducer.class);

    public void sendMessage(String message) {
        LOGGER.info(String.format("Produced message: %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }

}
