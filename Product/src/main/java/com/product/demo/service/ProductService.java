package com.product.demo.service;

import java.util.List;

import com.product.demo.dto.ProductRequestDto;
import com.product.demo.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto dto);

    List<ProductResponseDto> getProductsByCategory(String category);

    List<ProductResponseDto> getAllProducts();
    
    ProductResponseDto getProductById(Long id);

}
