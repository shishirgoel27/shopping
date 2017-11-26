package com.example.shop.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.example.shop.domain.Category;
import com.example.shop.domain.Item;
import com.example.shop.exception.ObjectNotFoundException;
import com.example.shop.service.ShoppingStore;
import com.example.shop.service.StoreManager;

@Component
@Order(1)
public class StoreManagerImpl implements StoreManager, CommandLineRunner {

	private InputStreamSource inputStreamSource;

	@Autowired
	private ShoppingStore store;

	@Override
	public void setInputSource(String source) {
		try {
			inputStreamSource = new UrlResource("file:///"+source);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void loadInventory() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamSource.getInputStream()));
		String str = null;
		while ((str = reader.readLine()) != null) {
			String[] itemValues = str.split(",");
			if (itemValues.length == 4) {
				addItem(Integer.parseInt(itemValues[0]), itemValues[1], itemValues[2], Double.parseDouble(itemValues[3]));
			}
		}
		str = null;
		reader.close();
		reader = null;
	}

	@Override
	public void buildCategories() {
		List<Category> categories = new ArrayList<Category>();
		Category ancestor1 = buildCategory("menswear", 0.0, null);
		categories.add(ancestor1);
		categories.add(buildCategory("shirts", 0.0, ancestor1));
		categories.add(buildCategory("trousers", 0.0, ancestor1));
		categories.add(buildCategory("casuals", 0.3, ancestor1));
		categories.add(buildCategory("jeans", 0.2, ancestor1));
		Category ancestor2 = buildCategory("womenswear", 0.5, null);
		categories.add(ancestor2);
		categories.add(buildCategory("footwear", 0.0, ancestor2));
		store.setCategories(categories);
	}

	private Category buildCategory(String category, double discount, Category parent) {
		Category c = Category.getInstance(category);
		c.setDiscount(discount);
		c.setParent(parent);
		return c;
	}

	@Override
	public Item getItem(int id) throws ObjectNotFoundException {
		return store.getItem(id);
	}
	
	@Override
	public boolean addItem(String brand, String categoryType, double actualPrice) {
		return addItem(store.getInventorySize()+1, brand, categoryType, actualPrice);
	}

	public boolean addItem(int index, String brand, String categoryType, double actualPrice) {
		return store.addItem(index, brand, categoryType, actualPrice);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args == null || args.length == 0) {
			throw new Exception("No input source provided");
		}
		setInputSource(args[0]);
		buildCategories();
		loadInventory();
	}

}
