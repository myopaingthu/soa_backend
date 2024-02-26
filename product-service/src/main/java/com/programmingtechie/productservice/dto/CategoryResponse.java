package com.programmingtechie.productservice.dto;

import java.util.List;

import com.programmingtechie.productservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String id;
    private String name;
    private String cover_image;
    //private List<Product> products;
}
