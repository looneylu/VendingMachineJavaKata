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
	
	public Boolean vendProduct(Product chosenProduct){
		
		if (inventory.get(chosenProduct) > 0 ){
			
			Integer newAmount = getAmountOfProduct(chosenProduct) - 1;
			reduceProductInventoryByOne(chosenProduct, newAmount);

			return true;
		}
		
		return false;
	}

	private void reduceProductInventoryByOne(Product chosenProduct, Integer newAmount) {
		inventory.remove(chosenProduct);
		inventory.put(chosenProduct, newAmount);
	}
	
	private Integer getAmountOfProduct(Product product){
		return getInventory().get(product);
	}
}
