package org.patikadev.orderexample.dto.request;

import org.patikadev.orderexample.model.Gender;


public record CreateCustomerDto(String userName, String email, Long identity, Gender gender, String password,
                                CreateCustomerAddressDto customerAddressDto) {

}
