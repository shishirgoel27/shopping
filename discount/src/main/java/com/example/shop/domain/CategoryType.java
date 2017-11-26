package com.example.shop.domain;

import com.example.shop.exception.ObjectNotFoundException;

public enum CategoryType {

	SHIRTS, TROUSERS, FOOTWEAR, MENSWEAR, WOMENSWEAR, CASUALS, JEANS, DRESSES;

	public static CategoryType fromString(String type) {
		for(CategoryType c : values()) {
			if(c.name().toLowerCase().equalsIgnoreCase(type)) {
				return c;
			}
		}
		throw new ObjectNotFoundException("No category found for %s",type);
	}

}
