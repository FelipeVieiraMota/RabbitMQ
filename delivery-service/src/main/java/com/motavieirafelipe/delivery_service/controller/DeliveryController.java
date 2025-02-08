package com.motavieirafelipe.delivery_service.controller;


import com.motavieirafelipe.delivery_service.model.Delivery;
import com.motavieirafelipe.delivery_service.repository.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRepository repository;

    public DeliveryController(DeliveryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Delivery> createOrder() {
        return repository.findAll();
    }
}
