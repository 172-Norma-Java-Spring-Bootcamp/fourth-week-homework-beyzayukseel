package org.patikadev.orderexample.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.patikadev.orderexample.model.Category;

public record CategoryResponseDto(Long id, String name, @JsonIgnore Category parent) {
}
