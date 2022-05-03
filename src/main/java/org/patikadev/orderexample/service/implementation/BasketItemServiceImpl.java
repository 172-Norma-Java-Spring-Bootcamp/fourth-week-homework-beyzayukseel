package org.patikadev.orderexample.service.implementation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BasketItemConverter;
import org.patikadev.orderexample.dto.request.CreateBasketItemDto;
import org.patikadev.orderexample.dto.response.BasketItemResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.repository.BasketItemRepository;
import org.patikadev.orderexample.service.BasketItemService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasketItemServiceImpl implements BasketItemService {
    private final BasketItemRepository basketItemRepository;
    private final BasketItemConverter basketItemConverter;

    @Override
    public void addItemToBasket(CreateBasketItemDto createBasketItemDto) {
        BasketItem basketItem = new BasketItem();
        Basket basket = new Basket();
        basket.setId(createBasketItemDto.basketId());
        basket.setTotalPrice(basket.getTotalPrice().add(basketItem.getPrice()));
        basketItem.setBasket(basket);
        Product product = new Product();
        product.setId(createBasketItemDto.productId());
        basketItem.setProduct(product);
        basketItem.setTaxPrice(createBasketItemDto.taxPrice());
        basketItem.setQuantity(createBasketItemDto.quantity());
        basketItem.setPrice(createBasketItemDto.price());
        basketItem.setShippingPrice(createBasketItemDto.shippingPrice());
        basketItem.setDiscountPrice(createBasketItemDto.discountPrice());
        basketItemRepository.save(basketItem);
        log.info("BasketItem ID -> {} date: {} created", basketItem.getId(), new Date());
    }

    @Override
    public List<BasketItemResponseDto> getItemsFromBasket(Long basketId) {
        List<BasketItem> basketItem = basketItemRepository.findAllByBasket_Id(basketId);
        return basketItem
                .stream()
                .map(basketItemConverter::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBasketItem(Long basketItemId) {
        if (!basketItemRepository.existsById(basketItemId)) {
            throw new ServiceOperationException.BasketItemNotFoundException("Item not found");
        }
        basketItemRepository.deleteById(basketItemId);
    }
}
