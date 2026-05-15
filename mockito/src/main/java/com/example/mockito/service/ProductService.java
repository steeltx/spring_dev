package com.example.mockito.service;

import com.example.mockito.model.Product;
import com.example.mockito.repository.IProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void saveProduct(Product product){
        if(product.getPrice() <= 0){
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero");
        }
        productRepository.save(product);
    }

}
