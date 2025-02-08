package com.motavieirafelipe.order_service.controller;

import com.motavieirafelipe.order_service.event.OrderCreatedEvent;
import com.motavieirafelipe.order_service.model.Order;
import com.motavieirafelipe.order_service.producer.OrderProducer;
import com.motavieirafelipe.order_service.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderController(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        order.setStatus("CREATED");
        order = orderRepository.save(order);

        // Publish event to RabbitMQ
        orderProducer.sendOrderCreatedEvent(new OrderCreatedEvent (
                order.getId(),
                order.getCustomerName(),
                order.getProduct(),
                order.getQuantity()
        ));

        return order;
    }
}