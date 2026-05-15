package com.example.mockito.repository;


import com.example.mockito.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    Optional<Product> findById(Long id);
    List<Product> findAll();
    void save(Product product);

}
