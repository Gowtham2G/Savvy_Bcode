package com.savvy.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShippingAddressResponseDto {

	 private String fullName;
	    private String addressLine;
	    private String city;
	    private String state;
	    private String postalCode;
	    private String country;
	    private String phoneNumber;
	    
	    public ShippingAddressResponseDto(String fullName, String addressLine, String city,
                String state, String postalCode, String country,
                String phoneNumber) {
this.fullName = fullName;
this.addressLine = addressLine;
this.city = city;
this.state = state;
this.postalCode = postalCode;
this.country = country;
this.phoneNumber = phoneNumber;
}
}
