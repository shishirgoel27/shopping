package com.example.shop.service;

import java.util.List;

import com.example.shop.domain.Category;
import com.example.shop.domain.Item;
import com.example.shop.exception.ObjectNotFoundException;

public interface ShoppingStore {
	
	public double getBillAmount(List<Integer> itemIds);

	public void addCategory(String type, Category category);

	public void addItem(int index, String brand, String categoryType, double actualPrice);

	public Item getItem(int id) throws ObjectNotFoundException;

	public int getInventorySize();

}
