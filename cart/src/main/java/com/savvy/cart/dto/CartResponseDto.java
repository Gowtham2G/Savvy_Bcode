package com.savvy.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
	private long id;
	private long userId;
	private long productId;
	private int quantity;

}
