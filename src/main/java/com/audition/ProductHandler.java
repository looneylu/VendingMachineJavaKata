package com.audition;

import java.util.HashMap;

public class ProductHandler {
	private HashMap <Product, Integer> inventory = new HashMap <Product, Integer>();
	

	public HashMap <Product, Integer> getInventory() {
		return inventory;
	}

	public void setInventory(Product vendProduct, Integer quantity) {		
		getInventory().put(vendProduct, quantity);
	}
}
