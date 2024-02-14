package com.quicksilvarad.paymentservice.consumer;

import com.quicksilvarad.paymentservice.dto.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @RabbitListener(queues = "${rabbitmq.payment.queue.name}")
    public void consume(Payment payment)
    {
        LOGGER.info(String.format("Payment payload consumed!-> %s", payment.toString()));

    }
}
