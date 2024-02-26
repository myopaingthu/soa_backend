package com.programmingtechie.productservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    private String name;
    private String cover_image;
    //private List<String> products;
}
