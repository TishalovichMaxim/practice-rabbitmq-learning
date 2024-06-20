package by.tishalovich.rabbitmq.producer;

import by.tishalovich.rabbitmq.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitmqJsonProducer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RabbitmqJsonProducer.class);

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangerName;

    @Value("${spring.rabbitmq.routing-key.json.name}")
    private String routingKeyName;

    private final RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("Json message produced: %s", user.toString()));
        rabbitTemplate.convertAndSend(exchangerName, routingKeyName, user);
    }

}
