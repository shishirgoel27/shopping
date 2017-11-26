package com.example.shop.service;

import java.util.List;

import com.example.shop.domain.Category;
import com.example.shop.domain.Item;

public interface ShoppingStore {
	
	public double getBillAmount(List<Integer> itemIds);

	public void setCategories(List<Category> categories);

	public boolean addItem(int index, String brand, String categoryType, double actualPrice);

	public Item getItem(int id);

	public int getInventorySize();

}
