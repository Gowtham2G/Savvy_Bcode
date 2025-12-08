package com.product.service;

import java.util.List;

import com.product.dto.ProductRequestDto;
import com.product.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto dto);

    List<ProductResponseDto> getProductsByCategory(String category);

    List<ProductResponseDto> getAllProducts();
    
    ProductResponseDto getProductById(Long id);

}
