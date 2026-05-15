package com.example.mockito.service;

import com.example.mockito.model.Product;
import com.example.mockito.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void testFindProductByIdReturnsProductWhenFound() {
        // arrange - preparacion
        Long productId = 1L;
        Product mockProduct = new Product(productId,"Laptop",1500.00);
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        Optional<Product> product = productService.findProductById(productId);

        // Assert
        assertTrue(product.isPresent());
        assertEquals(mockProduct, product.get());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindProductByIdReturnsProductWhenNotFound() {
        // arrange - preparacion
        Long productId = 10L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        Optional<Product> product = productService.findProductById(productId);

        // Assert
        assertFalse(product.isPresent());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetAllProductsReturnsListOfProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Laptop",1500),
                new Product(2L,"Mouse",25));
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getAllProduct();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
        assertEquals("Laptop",products.get(0).getName());
        assertEquals("Mouse",products.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSaveProductSavesValidProduct(){
        Product validProduct = new Product(null, "Teclado",50.00);
        doNothing().when(productRepository).save(validProduct);

        productService.saveProduct(validProduct);

        verify(productRepository, times(1)).save(validProduct);
    }

    @Test
    void testSaveProductThrowsExceptionWhenPriceIsZeroOrLess(){
        Product invalidProduct = new Product(null, "Teclado",0.00);

        assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(invalidProduct);
        });

        verify(productRepository, never()).save(any(Product.class));
    }
}