package com.programmingtechie.productservice.repository;

import com.programmingtechie.productservice.model.Brand;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BrandRepository extends MongoRepository<Brand, String> {
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Brand> findByNameRegex(String search);
}
