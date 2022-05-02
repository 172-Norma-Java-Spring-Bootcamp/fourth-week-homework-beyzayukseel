package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BasketItemConverter;
import org.patikadev.orderexample.converter.CustomerDtoConverter;
import org.patikadev.orderexample.dto.request.CreateBasketDto;
import org.patikadev.orderexample.dto.response.BasketItemResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.repository.BasketRepository;
import org.patikadev.orderexample.service.BasketItemService;
import org.patikadev.orderexample.service.BasketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketItemService basketItemService;
    private final BasketItemConverter basketItemConverter;
    private final CustomerDtoConverter customerDtoConverter;

    @Override
    public void addBasket(CreateBasketDto createBasketDto) {
        Customer customer = new Customer();
        customer.setId(createBasketDto.customerResponseDto().id());
        Basket basket = new Basket();
        basket.setCustomer(customer);
        basket.setTotalPrice(BigDecimal.TEN);
        basketRepository.save(basket);
        log.info("Basket ID -> {} date: {} created", basket.getId(), new Date());
    }

    @Override
    public Basket getBasketById(Long id) {
        Basket basket = basketRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.BasketNotFoundException("basqet not found"));

        List<BasketItemResponseDto> items = basketItemService.getItemsFromBasket(id);
        List<BasketItem> basketItems = items
                .stream()
                .map(basketItemConverter::convertToBasketItem)
                .collect(Collectors.toList());

        basket.setItems(basketItems);
        return basket;
    }

    @Override
    public Customer getCustomerByBasketId(Long id) {
        return basketRepository.findCustomerIdByBasket(id);
    }

    @Override
    public Basket getBasketByUserId(Long userId) {
        Basket basket = basketRepository.findByCustomer_Id(userId);
        return getBasketById(basket.getId());
    }

    @Override
    public void deleteBasket(Long basketId) {
        basketRepository.deleteById(basketId);
    }
}
