package com.example.shop.domain;

import com.example.shop.exception.InvalidInputException;

public class Category implements HasDiscount {

	private static Category self;

	private double discount;

	private CategoryType type;

	private Category parent;

	private Category() {

	}

	public static Category getInstance(String type) {
		CategoryType categoryType = CategoryType.fromString(type);
		if (self == null || self.type != categoryType) {
			synchronized (Category.class) {
				if (self == null || self.type != categoryType) {
					self = new Category();
					self.type = categoryType;
				}

			}
		}
		return self;
	}
	
	public void setDiscount(double discount) {
		if(discount < 0.0 && discount > 1.0) {
			throw new InvalidInputException("Discount should be between 0.0 and 1.0");
		}
		this.discount=discount;
	}
	
	public void setParent(Category parent) {
		this.parent=parent;
	}

	@Override
	public double getDiscountRate() {
		if (parent == null) {
			return discount;
		}
		return Math.max(parent.getDiscountRate(), discount);
	}

	@Override
	public String toString() {
		return "Category [type=" + type + ", discount=" + discount + "]";
	}

}
