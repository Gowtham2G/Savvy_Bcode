package com.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.product.dto.ProductRequestDto;
import com.product.dto.ProductResponseDto;
import com.product.service.ProductService;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto dto) {
        return productService.addProduct(dto);
    }

    @GetMapping("/get")
    public List<ProductResponseDto> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{category}")
    public List<ProductResponseDto> getByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }
}
