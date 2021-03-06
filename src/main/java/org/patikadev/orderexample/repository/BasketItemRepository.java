package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    List<BasketItem> findAllByBasket_Id(Long id);
}
