package com.example.shop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.shop.domain.Category;
import com.example.shop.domain.Item;
import com.example.shop.service.ShoppingStore;

@Component
@Order(2)
public class ShoppingStoreService implements ShoppingStore, CommandLineRunner {

	private Map<Integer, Item> inventory = new HashMap<Integer, Item>();

	private Collection<Category> categories;

	@Override
	public double getBillAmount(List<Integer> itemIds) {
		double sum = 0;
		for(Integer id: itemIds) {
			Item item = inventory.get(id);
			double finalDiscount = Math.max(item.getBrand().getDiscountRate(), item.getCategory().getDiscountRate());
			sum += item.getActualPrice() - (item.getActualPrice() * finalDiscount);
		}
		return sum;
	}

	@Override
	public int getInventorySize() {
		return inventory.size();
	}

	@Override
	public void setCategories(List<Category> categories) {
		this.categories = new ArrayList<Category>();
		this.categories.addAll(categories);
	}

	@Override
	public boolean addItem(int index, String brand, String categoryType, double actualPrice) {
		Item item = new Item(brand, categoryType, actualPrice);
		return ((inventory.put(index, item)) == null);
	}

	@Override
	public Item getItem(int id) {
		return inventory.get(id);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args == null || args.length == 0) {
			throw new Exception("No input source provided");
		}
		int numRequest = Integer.parseInt(args[1]);
		double[] bills = new double[numRequest];
		for (int i = 0; i < numRequest; i++) {
			List<Integer> idList = Arrays.asList(args[i+2].split(",")).parallelStream().map(id -> Integer.parseInt(id))
					.collect(Collectors.toList()); 
			bills[i] = getBillAmount(idList);
		}
 		
		for(int j=0;j<bills.length;j++) {
			System.out.println(bills[j]);
		}
	}

}
