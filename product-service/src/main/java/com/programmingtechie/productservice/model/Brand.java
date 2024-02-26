package com.programmingtechie.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "brand")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Brand {

    @Id
    private String id;
    private String name;
    private String cover_image;
    //@DBRef
    //@Builder.Default
    //private List<Product> products = new ArrayList<>();
}
