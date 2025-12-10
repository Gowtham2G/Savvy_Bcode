package com.product.demo.dto;

import java.util.List;
import lombok.*;

@Builder
public class ProductResponseDto {

	private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private List<String> imageUrls;
    private Double rating;
	
}
