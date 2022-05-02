package org.patikadev.orderexample.dto.response;

public record CustomerAddressResponseDto(Long id, String phoneNumber, String country, String city, String postalCode,
                                         String description) {
}
