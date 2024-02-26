package com.programmingtechie.productservice.repository;

import com.programmingtechie.productservice.model.Category;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Category> findByNameRegex(String search);
}
