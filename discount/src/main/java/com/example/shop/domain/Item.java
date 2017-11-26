package com.example.shop.domain;

public class Item {

	private Brand brand;
	private Category category;
	private double actualPrice;
	
	public Item() {
		
	}
	
	public Item(String brand, Category category, double price) {
		this.brand = Brand.fromString(brand);
		this.category = category;
		this.actualPrice = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

}
