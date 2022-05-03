package org.patikadev.orderexample.converter;


import org.patikadev.orderexample.dto.request.CreateCustomerDto;
import org.patikadev.orderexample.dto.response.CustomerResponseDto;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.model.CustomerAddress;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public record CustomerDtoConverter(CustomerAddressConverter customerAddressConverter) {

    public Customer convertToDto(CreateCustomerDto createCustomerDTO) {
        Customer customer = new Customer();
        customer.setUsername(createCustomerDTO.userName());
        customer.setEmail(createCustomerDTO.email());
        customer.setIdentity(createCustomerDTO.identity());
        customer.setGender(createCustomerDTO.gender());
        customer.setPassword(createCustomerDTO.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("BaranBuyuk");
        customer.setIsDeleted(Boolean.FALSE);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(createCustomerDTO.customerAddressDto().phoneNumber());
        customerAddress.setCountry(createCustomerDTO.customerAddressDto().country());
        customerAddress.setCity(createCustomerDTO.customerAddressDto().city());
        customerAddress.setPostalCode(createCustomerDTO.customerAddressDto().postalCode());
        customerAddress.setDescription(createCustomerDTO.customerAddressDto().description());

        customerAddress.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);

        return customer;
    }
    public CustomerResponseDto convertToCustomer(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(), customer.getIdentity(),
                customer.getGender(),
                customer.getPassword(),
                customerAddressConverter.convertToCustomerAddressDto(customer.getCustomerAddress()));
    }
}
