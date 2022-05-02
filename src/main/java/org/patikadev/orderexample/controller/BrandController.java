package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateBrandDto;
import org.patikadev.orderexample.service.BrandService;
import org.patikadev.orderexample.validator.implementation.CreateBrandValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    private final CreateBrandValidator createBrandDtoValidator;

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody CreateBrandDto createBrandDto) {
        createBrandDtoValidator.validate(createBrandDto);
        brandService.createBrand(createBrandDto);
        return ResponseEntity.ok().build();
    }

}
