package com.savvy.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressRequestDto {

	 private String fullName;
	    private String addressLine;
	    private String city;
	    private String state;
	    private String postalCode;
	    private String country;
	    private String phoneNumber;
}
