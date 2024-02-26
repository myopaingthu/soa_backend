package com.programmingtechie.productservice.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.programmingtechie.productservice.dto.BrandRequest;
import com.programmingtechie.productservice.dto.BrandResponse;
import com.programmingtechie.productservice.service.BrandService;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody BrandRequest brandRequest) {
        brandService.createBrand(brandRequest);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getAllCategories() {
        return brandService.getAllCategories();
    }
}