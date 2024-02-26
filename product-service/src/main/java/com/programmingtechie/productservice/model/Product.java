package com.programmingtechie.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    @DBRef
    @Builder.Default
    private List<Category> categories = new ArrayList<>();
    @DBRef
    @Builder.Default
    private List<Brand> brands = new ArrayList<>();
    private Integer rating;
    private Integer review_count;
    private String sku;
    private List<String> sizes;
    private List<String> colors;
}
