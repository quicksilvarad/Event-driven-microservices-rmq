package com.quicksilvarad.orderservice.controller;

import com.quicksilvarad.orderservice.dto.Order;
import com.quicksilvarad.orderservice.dto.OrderEvent;
import com.quicksilvarad.orderservice.dto.Payment;
import com.quicksilvarad.orderservice.publisher.OrderPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/rmq")
public class OrderController {

    private OrderPublisher orderPublisher;

    public OrderController(OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> sendMessage(@RequestBody Order order)
    {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Created");
        orderEvent.setMessage("Order has been created.");
        orderEvent.setOrder(order);
         orderPublisher.sendMessage(orderEvent);
        return new ResponseEntity<>("Order successfully placed", HttpStatus.OK);
    }

    @PostMapping("/paymentPublish")
    public ResponseEntity<String> sendEmail(@RequestBody Payment payment)
    {
        orderPublisher.sendPayment(payment);
        return new ResponseEntity<>("Payment successful!", HttpStatus.OK);
    }
}
