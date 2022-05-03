package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateBrandDto;
import org.patikadev.orderexample.dto.response.BrandResponseDto;
import org.patikadev.orderexample.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandDtoConverter {

    public Brand convertToBrand(CreateBrandDto createBrandDto) {
        Brand brand = new Brand();
        brand.setName(createBrandDto.name());
        return brand;
    }

    public BrandResponseDto convertToBrandResponseDto(Brand brand) {
        return new BrandResponseDto(brand.getId(), brand.getName());
    }


}
