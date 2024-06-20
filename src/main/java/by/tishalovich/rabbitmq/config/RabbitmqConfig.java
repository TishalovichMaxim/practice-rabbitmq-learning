package by.tishalovich.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue stringQueue(
            @Value("${spring.rabbitmq.string-queue.name}") String queueName) {

        return new Queue(queueName);
    }

    @Bean
    public Queue jsonQueue(
            @Value("${spring.rabbitmq.json-queue.name}") String queueName) {

        return new Queue(queueName);
    }

    @Bean
    public TopicExchange exchange(
            @Value("${spring.rabbitmq.exchange.name}") String exchangeName) {

        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingToStringQueue(
            @Value("${spring.rabbitmq.routing-key.string.name}") String routingKey,
            @Qualifier("stringQueue") Queue queue, TopicExchange exchange) {

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    @Bean
    public Binding bindingToJsonQueue(
            @Value("${spring.rabbitmq.routing-key.json.name}") String routingKey,
            @Qualifier("jsonQueue") Queue queue, TopicExchange exchange) {

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
