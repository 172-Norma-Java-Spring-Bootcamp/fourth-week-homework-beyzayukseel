package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCustomerAddressDto;
import org.patikadev.orderexample.dto.response.CustomerAddressResponseDto;
import org.patikadev.orderexample.model.CustomerAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressConverter {

    public CreateCustomerAddressDto convertCustomerAddressDto(CustomerAddress customerAddress) {
        return new CreateCustomerAddressDto(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }

    public CustomerAddressResponseDto convertToCustomerAddressDto(CustomerAddress customerAddress) {
        return new CustomerAddressResponseDto(customerAddress.getId(),
                customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }
}

