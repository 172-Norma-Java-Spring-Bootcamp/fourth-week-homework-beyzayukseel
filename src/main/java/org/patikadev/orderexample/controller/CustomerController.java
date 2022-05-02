package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCustomerDto;
import org.patikadev.orderexample.dto.response.CustomerResponseDto;
import org.patikadev.orderexample.service.CustomerService;
import org.patikadev.orderexample.validator.implementation.CreateCustomerValidator;
import org.patikadev.orderexample.validator.implementation.CustomerIDValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CreateCustomerValidator createCustomerValidator;
    private final CustomerIDValidator customerIdValidator;


    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDto createCustomerDTO) {
        createCustomerValidator.validate(createCustomerDTO);
        customerService.create(createCustomerDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
        customerIdValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerIdValidator.validate(id);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

}
