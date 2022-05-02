package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BrandDtoConverter;
import org.patikadev.orderexample.dto.request.CreateBrandDto;
import org.patikadev.orderexample.dto.response.BrandResponseDto;
import org.patikadev.orderexample.repository.BrandRepository;
import org.patikadev.orderexample.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandDtoConverter brandDtoConverter;

    @Override
    public Boolean checkBrand(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public void createBrand(CreateBrandDto createBrandDto) {
        brandRepository.save(brandDtoConverter.convertToBrand(createBrandDto));
        log.info("Brand date: {} created", new Date());
    }

    @Override
    public BrandResponseDto getBrandByName(String name) {
        return brandDtoConverter.convertToBrandResponseDto(brandRepository.findBrandByName(name));
    }
}
