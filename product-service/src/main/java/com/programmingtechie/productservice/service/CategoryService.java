package com.programmingtechie.productservice.service;

import com.mongodb.lang.NonNull;
import com.programmingtechie.productservice.dto.CategoryRequest;
import com.programmingtechie.productservice.dto.CategoryResponse;
import com.programmingtechie.productservice.model.Category;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.CategoryRepository;
import com.programmingtechie.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public void createCategory(CategoryRequest categoryRequest) {
        //List<Product> products = categoryRequest.getProducts().stream().map(this::getProducts).toList();
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .cover_image(categoryRequest.getCover_image())
                .build();

        if (category != null) {
            categoryRepository.save(category);
            log.info("Category {} is saved", category.getId());
        }
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .cover_image(category.getCover_image())
                .build();
    }

    //private Product getProducts(@NonNull String productIDString) {
    //    return productRepository.findById(productIDString).get();
    //}
}
