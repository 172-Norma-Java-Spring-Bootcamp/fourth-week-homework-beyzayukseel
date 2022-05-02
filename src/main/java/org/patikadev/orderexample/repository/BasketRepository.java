package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByCustomer_Id(Long id);

    @Query("SELECT b.customer FROM Basket b  WHERE b.id= ?1 ")
    Customer findCustomerIdByBasket(Long id);
}
