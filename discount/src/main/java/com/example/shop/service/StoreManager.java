package com.example.shop.service;

import com.example.shop.domain.Item;
import com.example.shop.exception.ObjectNotFoundException;

public interface StoreManager {
	
	public void setInputSource(String source);
	
	public void loadInventory() throws Exception;
	
	public void buildCategories();
	
	public Item getItem(int id) throws ObjectNotFoundException;
	
	public void addItem(String brand, String categoryType, double actualPrice);
	
}
