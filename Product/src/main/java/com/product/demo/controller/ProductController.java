package com.product.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.product.demo.dto.ProductRequestDto;
import com.product.demo.dto.ProductResponseDto;
import com.product.demo.service.ProductService;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
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
