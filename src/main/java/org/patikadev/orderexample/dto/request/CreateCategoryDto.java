package org.patikadev.orderexample.dto.request;

import org.patikadev.orderexample.model.Category;

public record CreateCategoryDto(Category parent, String name) {
}
