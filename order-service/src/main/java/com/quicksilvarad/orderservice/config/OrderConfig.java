package com.quicksilvarad.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Value(("${rabbitmq.exchange.name}"))
    private String exchange;
    @Value("${rabbitmq.stock.queue.name}")
    private String stockQueue;

    @Value("${rabbitmq.stock.routing.key}")
    private String stockRouting;

    @Value("${rabbitmq.payment.queue.name}")
    private String paymentQueue;

    @Value("${rabbitmq.payment.routing.key}")
    private String paymentRouting;


    //Queue - orderQueue
    @Bean
    public Queue stockQueue()
    {
        return new Queue(stockQueue);
    }
    //Exchange
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchange);
    }

    //Binding - Binding between exchage and queue using routing key
    @Bean
    public Binding stockBinding()
    {
        return BindingBuilder.bind(stockQueue()).to(exchange()).with(stockRouting);
    }

    @Bean
    public Queue paymentQueue()
    {
        return new Queue(paymentQueue);
    }

    @Bean
    public Binding paymentBinding()
    {
        return BindingBuilder.bind(paymentQueue()).to(exchange()).with(paymentRouting);
    }


    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
