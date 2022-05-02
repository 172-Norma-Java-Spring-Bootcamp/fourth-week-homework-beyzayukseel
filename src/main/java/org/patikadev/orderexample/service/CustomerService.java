package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCustomerDto;
import org.patikadev.orderexample.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    void create(CreateCustomerDto createCustomerDTO);

    CustomerResponseDto getCustomer(Long id);

    void deleteCustomer(Long id);

    List<CustomerResponseDto> getAllCustomers();
}
