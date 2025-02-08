package com.motavieirafelipe.delivery_service.consumer;

import com.motavieirafelipe.delivery_service.event.OrderCreatedEvent;
import com.motavieirafelipe.delivery_service.model.Delivery;
import com.motavieirafelipe.delivery_service.repository.DeliveryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class OrderConsumer {

    private final DeliveryRepository deliveryRepository;

    public OrderConsumer(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Bean
    public Consumer<OrderCreatedEvent> orderCreated() {

        return event -> {
            Delivery delivery = new Delivery();
            delivery.setOrderId(event.getOrderId());
            delivery.setCustomerName(event.getCustomerName());
            delivery.setProduct(event.getProduct());
            delivery.setQuantity(event.getQuantity());
            delivery.setStatus("IN_PROGRESS");
            deliveryRepository.save(delivery);
        };
    }
}