package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.service.OrderService;
import org.patikadev.orderexample.validator.implementation.BasketIDValidator;
import org.patikadev.orderexample.validator.implementation.OrderIDValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderIDValidator orderIDValidator;
    private final BasketIDValidator basketIDValidator;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        orderIDValidator.validate(id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/save/{basketId}")
    public ResponseEntity<?> createOrder(@PathVariable Long basketId) {
        basketIDValidator.validate(basketId);
        orderService.saveOrder(basketId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderIDValidator.validate(id);
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
