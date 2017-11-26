package com.example.shop.domain;

import com.example.shop.exception.ObjectNotFoundException;

public enum Brand implements HasDiscount {

	ARROW("Arrow", 0.2), 
	VERO_MODA("Vero Moda", 0.6), 
	UCB("UCB", 0.0), 
	WRANGLER("Wrangler", 0.), 
	PROVOGUE("Progvogue", 0.2), 
	ADIDAS("Adidas", 0.05);

	private String brandName;

	private double discount;

	Brand(String brandName, double discount) {
		this.brandName = brandName;
		this.discount = discount;
	}

	@Override
	public double getDiscountRate() {
		return discount;
	};
	
	public static Brand fromString(String brand) {
		for(Brand b:values()) {
			if(b.brandName.toLowerCase().equalsIgnoreCase(brand)) {
				return b;
			}
		}
		throw new ObjectNotFoundException("No brand exists with name %s", brand);
	}

}
