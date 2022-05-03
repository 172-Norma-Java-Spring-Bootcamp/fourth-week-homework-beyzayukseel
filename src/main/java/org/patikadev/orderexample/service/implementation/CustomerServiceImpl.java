package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.CustomerDtoConverter;
import org.patikadev.orderexample.dto.request.CreateCustomerDto;
import org.patikadev.orderexample.dto.response.CustomerResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.repository.CustomerRepository;
import org.patikadev.orderexample.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDtoConverter customerDtoConverter;
    private final CustomerRepository customerRepository;

    @Override
    public void create(CreateCustomerDto createCustomerDTO) {
        Customer customer = customerDtoConverter.convertToDto(createCustomerDTO);
        customerRepository.save(customer);
        log.info("Customer ID -> {} date: {} created", customer.getId(), new Date());
    }

    @Override
    public CustomerResponseDto getCustomer(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotFoundException("Customer not found"));
        return customerDtoConverter.convertToCustomer(customer);
    }

    @Override
    public Boolean existCustomerById(Long customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotFoundException("Customer not found"));

        if (customer.getIsDeleted()) {
            throw new ServiceOperationException.CustomerAlreadyDeleted("Customer has already been deleted");
        } else {
            customer.setIsDeleted(Boolean.TRUE);
            customerRepository.save(customer);
        }
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAllCustomers()
                .stream()
                .map(customerDtoConverter::convertToCustomer)
                .collect(Collectors.toList());
    }
}

