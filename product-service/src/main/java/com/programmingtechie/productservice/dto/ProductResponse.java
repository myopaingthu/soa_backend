package com.programmingtechie.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import com.programmingtechie.productservice.model.Brand;
import com.programmingtechie.productservice.model.Category;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<Brand> brands;
    private List<Category> categories;
    private Integer rating;
    private Integer review_count;
    private String sku;
    private List<String> sizes;
    private List<String> colors;
}
