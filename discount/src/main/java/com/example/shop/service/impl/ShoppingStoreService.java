package com.example.shop.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.shop.domain.Category;
import com.example.shop.domain.CategoryType;
import com.example.shop.domain.Item;
import com.example.shop.exception.InvalidInputException;
import com.example.shop.exception.ObjectNotFoundException;
import com.example.shop.service.ShoppingStore;

@Component
@Order(2)
public class ShoppingStoreService implements ShoppingStore, CommandLineRunner {

	private Map<Integer, Item> inventory = new HashMap<Integer, Item>();

	private Map<CategoryType, Category> categoryMap = new HashMap<>();

	@Override
	public double getBillAmount(List<Integer> itemIds) {
		double sum = 0;
		for (Integer id : itemIds) {
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
	public void addCategory(String type, Category category) {
		categoryMap.put(CategoryType.fromString(type), category);
	}

	@Override
	public void addItem(int index, String brand, String categoryType, double actualPrice) {
		Item item = new Item(brand, categoryMap.get(CategoryType.fromString(categoryType)), actualPrice);
		inventory.put(index, item);
	}

	@Override
	public Item getItem(int id) throws ObjectNotFoundException{
		if(inventory.containsKey(id)) {
			return inventory.get(id);
		}
		throw new ObjectNotFoundException("No item found for %d", id);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args == null || args.length < 2) {
			throw new Exception("No customer choices are provided");
		}
		int numRequest = Integer.parseInt(args[1]);
		if(args.length < (numRequest+2) ) {
			throw new InvalidInputException("Choices for the purchases are not present");
		}
		double[] bills = new double[numRequest];
		for (int i = 0; i < numRequest; i++) {
			String[] ids = args[i + 2].split("\\|");
			List<String> asList = Arrays.asList(ids);
			List<Integer> idList = asList.parallelStream().map(id -> Integer.parseInt(id)).collect(Collectors.toList());
			bills[i] = getBillAmount(idList);
		}

		for (int j = 0; j < bills.length; j++) {
			System.out.println(bills[j]);
		}
	}

}
