package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.Gender;

public record CustomerResponseDto(Long id, String userName, String email, Long identity, Gender gender, String password,
                                  CustomerAddressResponseDto customerAddressResponseDto) {
}
