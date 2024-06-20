package by.tishalovich.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queue(@Value("${spring.rabbitmq.queue.name}") String queueName) {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange exchange(
            @Value("${spring.rabbitmq.exchange.name}") String exchangeName) {

        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(
            @Value("${spring.rabbitmq.routing-key.name}") String routingKey,
            Queue queue, TopicExchange exchange) {

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey);
    }

}
