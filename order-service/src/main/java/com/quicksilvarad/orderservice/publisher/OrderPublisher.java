package com.quicksilvarad.orderservice.publisher;

import com.quicksilvarad.orderservice.dto.OrderEvent;
import com.quicksilvarad.orderservice.dto.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPublisher.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.stock.routing.key}")
    private String stockKey;

    @Value("${rabbitmq.payment.routing.key}")
    private String paymentKey;

    private RabbitTemplate rabbitTemplate;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent)
    {
        LOGGER.info(String.format("Message sent -> %s",orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange,stockKey,orderEvent);
    }

    public void sendPayment(Payment payment)
    {
        LOGGER.info(String.format("Message sent -> %s",payment.toString()));
        rabbitTemplate.convertAndSend(exchange, paymentKey,payment);
    }
}
