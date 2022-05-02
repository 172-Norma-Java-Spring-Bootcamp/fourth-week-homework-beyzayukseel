package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateBrandDto;
import org.patikadev.orderexample.dto.response.BrandResponseDto;

public interface BrandService {
    Boolean checkBrand(String name);

    void createBrand(CreateBrandDto createBrandDto);

    BrandResponseDto getBrandByName(String name);
}
