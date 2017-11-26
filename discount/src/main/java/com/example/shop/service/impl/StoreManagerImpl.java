package com.example.shop.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

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
			inputStreamSource = new UrlResource("file:///" + source);
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
				addItem(Integer.parseInt(itemValues[0]), itemValues[1], itemValues[2],
						Double.parseDouble(itemValues[3]));
			}
		}
		str = null;
		reader.close();
		reader = null;
	}

	@Override
	public void buildCategories() {
		Category ancestor1 = new Category("menswear", 0.0, null);
		store.addCategory("menswear", ancestor1);
		store.addCategory("shirts", new Category("shirts", 0.0, ancestor1));
		store.addCategory("trousers", new Category("trousers", 0.0, ancestor1));
		store.addCategory("casuals", new Category("casuals", 0.3, ancestor1));
		store.addCategory("jeans", new Category("jeans", 0.2, ancestor1));
		Category ancestor2 = new Category("womenswear", 0.5, null);
		store.addCategory("womenswear", ancestor2);
		store.addCategory("dresses", new Category("dresses", 0.0, ancestor2));
		store.addCategory("footwear", new Category("footwear", 0.0, ancestor2));
	}

	@Override
	public Item getItem(int id) throws ObjectNotFoundException {
		return store.getItem(id);
	}

	@Override
	public void addItem(String brand, String categoryType, double actualPrice) {
		addItem(store.getInventorySize() + 1, brand, categoryType, actualPrice);
	}

	private void addItem(int index, String brand, String categoryType, double actualPrice) {
		store.addItem(index, brand, categoryType, actualPrice);
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
