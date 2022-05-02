package org.patikadev.orderexample.dto.request;


public record CreateCustomerAddressDto(String phoneNumber, String country, String city, String postalCode,
                                       String description) {
}
