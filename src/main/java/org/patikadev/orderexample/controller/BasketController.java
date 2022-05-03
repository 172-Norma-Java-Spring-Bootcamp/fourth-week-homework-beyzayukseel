package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateBasketDto;
import org.patikadev.orderexample.dto.request.CreateBasketItemDto;
import org.patikadev.orderexample.dto.response.BasketItemResponseDto;
import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.service.BasketItemService;
import org.patikadev.orderexample.service.BasketService;
import org.patikadev.orderexample.validator.implementation.BasketIDValidator;
import org.patikadev.orderexample.validator.implementation.CreateBasketItemValidator;
import org.patikadev.orderexample.validator.implementation.CreateBasketValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;
    private final BasketItemService basketItemService;
    private final BasketIDValidator basketIdValidator;
    private final CreateBasketValidator createBasketValidator;
    private final CreateBasketItemValidator createBasketItemValidator;

    @PostMapping()
    public ResponseEntity<?> createBasket(@RequestBody CreateBasketDto createBasketDto) {
        createBasketValidator.validate(createBasketDto);
        basketService.addBasket(createBasketDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToBasket(@RequestBody CreateBasketItemDto createBasketItemDto) {
        createBasketItemValidator.validate(createBasketItemDto);
        basketItemService.addItemToBasket(createBasketItemDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable Long id) {
        basketIdValidator.validate(id);
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @GetMapping("/items/{basketId}")
    public ResponseEntity<List<BasketItemResponseDto>> getItemsFromBasket(@PathVariable Long basketId) {
        basketIdValidator.validate(basketId);
        return ResponseEntity.ok(basketItemService.getItemsFromBasket(basketId));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Basket> getBasketByCustomerId(@PathVariable Long id) {
        basketIdValidator.validate(id);
        return ResponseEntity.ok(basketService.getBasketByUserId(id));
    }

    @DeleteMapping("/basketId/{basketId}")
    public ResponseEntity<?> deleteBasket(@PathVariable Long basketId) {
        basketService.deleteBasket(basketId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{basketItemId}")
    public ResponseEntity<?> deleteBasketItem(@PathVariable Long basketItemId) {
        basketItemService.deleteBasketItem(basketItemId);
        return ResponseEntity.ok().build();
    }
}
