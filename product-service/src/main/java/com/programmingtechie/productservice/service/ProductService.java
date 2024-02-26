package com.programmingtechie.productservice.service;

import com.mongodb.lang.NonNull;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Brand;
import com.programmingtechie.productservice.model.Category;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.BrandRepository;
import com.programmingtechie.productservice.repository.CategoryRepository;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public void createProduct(ProductRequest productRequest) {
        List<Category> categories = productRequest.getCategories().stream().map(this::getCategories).toList();
        List<Brand> brands = productRequest.getBrands().stream().map(this::getBrands).toList();
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .categories(categories)
                .brands(brands)
                .rating(productRequest.getRating())
                .sku(productRequest.getSku())
                .review_count(productRequest.getReview_count())
                .sizes(productRequest.getSizes())
                .colors(productRequest.getColors())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(
            List<String> categoryIds,
            List<String> brandIds,
            String sortOrder,
            String search,
            String minPrice,
            String maxPrice) {
        List<Category> categories = new ArrayList<>();
        List<Brand> brands = new ArrayList<>();
        if (categoryIds != null) {
            categories = categoryIds.stream().map(this::getCategories).toList();
        }
        if (brandIds != null) {
            brands = brandIds.stream().map(this::getBrands).toList();
        }

        List<Product> products = findProductsByFilters(categories, brands, sortOrder, search, minPrice, maxPrice);

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categories(product.getCategories())
                .brands(product.getBrands())
                .rating(product.getRating())
                .review_count(product.getReview_count())
                .sku(product.getSku())
                .colors(product.getColors())
                .sizes(product.getSizes())
                .build();
    }

    private Category getCategories(@NonNull String categoryIDString) {
        return categoryRepository.findById(categoryIDString).get();
    }

    private Brand getBrands(@NonNull String brandIDString) {
        return brandRepository.findById(brandIDString).get();
    }

    private List<Product> findProductsByFilters(
            List<Category> categories,
            List<Brand> brands,
            String sortOrder,
            String search,
            String minPrice,
            String maxPrice) {
        Query query = new Query();

        if (categories != null && !categories.isEmpty()) {
            query.addCriteria(Criteria.where("categories").in(categories));
        }

        if (brands != null && !brands.isEmpty()) {
            query.addCriteria(Criteria.where("brands").in(brands));
        }

        if (sortOrder != null) {
            if (sortOrder.equals("asc")) {
                query.with(Sort.by(Sort.Direction.ASC, "price"));
            } else {
                query.with(Sort.by(Sort.Direction.DESC, "price"));
            }
        }

        if (search != null) {
            List<Category> categoriesByName = getCategoriesByDescription(search);
            List<Brand> brandsByName = getBrandsByDescription(search);
            Criteria searchCriteria = new Criteria().orOperator(
                    Criteria.where("name").regex(search, "i"),
                    Criteria.where("description").regex(search, "i"),
                    Criteria.where("categories").in(categoriesByName),
                    Criteria.where("brands").in(brandsByName));
            query.addCriteria(searchCriteria);
        }

        if (minPrice != null && maxPrice != null) {
            log.info("minPrice: {}, maxPrice: {}", minPrice, maxPrice);
            Criteria rangCriteria = new Criteria().andOperator(
                    Criteria.where("price").gte(minPrice),
                    Criteria.where("price").lte(maxPrice));
            query.addCriteria(rangCriteria);
        }

        return mongoTemplate.find(query, Product.class);
    }

    private List<Category> getCategoriesByDescription(@NonNull String search) {
        return categoryRepository.findByNameRegex(search);
    }

    private List<Brand> getBrandsByDescription(@NonNull String search) {
        return brandRepository.findByNameRegex(search);
    }

    public ProductResponse getProductById(String productId) {
        Product product = productRepository.findById(productId).get();
        return mapToProductResponse(product);
    }
}
