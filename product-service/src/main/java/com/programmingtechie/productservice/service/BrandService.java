package com.programmingtechie.productservice.service;

import com.mongodb.lang.NonNull;
import com.programmingtechie.productservice.dto.BrandRequest;
import com.programmingtechie.productservice.dto.BrandResponse;
import com.programmingtechie.productservice.model.Brand;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.BrandRepository;
import com.programmingtechie.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public void createBrand(BrandRequest brandRequest) {
        //List<Product> products = brandRequest.getProducts().stream().map(this::getProducts).toList();
        Brand brand = Brand.builder()
                .name(brandRequest.getName())
                .cover_image(brandRequest.getCover_image())
                //.products(products)
                .build();

        if (brand != null) {
            brandRepository.save(brand);
            log.info("Brand {} is saved", brand.getId());
        }
    }

    public List<BrandResponse> getAllCategories() {
        List<Brand> categories = brandRepository.findAll();
        
        return categories.stream().map(this::mapToBrandResponse).toList();
    }

    private BrandResponse mapToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .cover_image(brand.getCover_image())
                //.products(brand.getProducts())
                .build();
    }

    //private Product getProducts(@NonNull String productIDString) {
    //    return productRepository.findById(productIDString).get();
    //}
}
