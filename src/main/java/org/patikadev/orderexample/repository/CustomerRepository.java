package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.isDeleted=false")
    List<Customer> findAllCustomers();
}
