package com.example.shop.domain;

import com.example.shop.exception.InvalidInputException;

public class Category implements HasDiscount {

	private double categoryDiscount;

	private CategoryType type;

	private Category parent;

	public Category(String type, double discount, Category parent) {
		this.type = CategoryType.fromString(type);
		setCategoryDiscount(discount);
		this.parent = parent;
	}

	public void setCategoryDiscount(double discount) {
		if (discount < 0.0 && discount > 1.0) {
			throw new InvalidInputException("Discount should be between 0.0 and 1.0");
		}
		this.categoryDiscount = discount;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public double getDiscountRate() {
		if (parent == null) {
			return categoryDiscount;
		}
		return Math.max(parent.getDiscountRate(), categoryDiscount);
	}

	@Override
	public String toString() {
		return "Category [type=" + type + ", categoryDiscount=" + categoryDiscount + "]";
	}

}
