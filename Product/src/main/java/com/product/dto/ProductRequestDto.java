package com.product.dto;

import java.util.List;

import lombok.Data;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

	 private String name;
	    private String description;
	    private Double price;
	    private Integer stock;
	    private String category;
	    private List<String> imageUrls;
}
