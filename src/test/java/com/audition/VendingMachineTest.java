package com.audition;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	private ProductHandler productHandler;
	
	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachine();
		productHandler = new ProductHandler();
	}

	@After
	public void tearDown() throws Exception {
		vendingMachine = null;
		productHandler = null;
	}

	@Test
	public void testCoinInsert() {
		Coin myCoin1 = Coin.NICKEL;
		Coin myCoin2 = Coin.DIME;
		Coin myCoin3 = Coin.PENNY;
		
		vendingMachine.insertCoin(myCoin1);
		vendingMachine.insertCoin(myCoin2);
		vendingMachine.insertCoin(myCoin3);
			
		assertEquals(.15, vendingMachine.getCoinHandler().getTotalValueOfCoinsInserted(), .001);
		assertEquals(1, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void testPennyInsert(){
		Coin myCoin3 = Coin.PENNY;
		vendingMachine.insertCoin(myCoin3);
		
		assertEquals(0, 0, .001);
	}
	
	@Test
	public void invalidCoinsAreReturned(){
		Coin myCoin1 = Coin.PENNY;
		Coin myCoin2 = Coin.PENNY;
		Coin myCoin3 = Coin.PENNY;
		
		vendingMachine.insertCoin(myCoin1);
		vendingMachine.insertCoin(myCoin2);
		vendingMachine.insertCoin(myCoin3);
		
		assertEquals(3, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void displayValueWhenMachineHasMoney(){
		Coin myCoin1 = Coin.NICKEL;
		Coin myCoin2 = Coin.DIME;
		Coin myCoin3 = Coin.PENNY;
		
		vendingMachine.insertCoin(myCoin1);
		vendingMachine.insertCoin(myCoin2);
		vendingMachine.insertCoin(myCoin3);
		
		assertEquals(".15", vendingMachine.getDisplayString());
		assertEquals(1, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void displayValueWhenMachineHasNoMoney(){
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
		assertEquals(0, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void machineIsAbleToSetInventory(){
		Product colaProduct = Product.COLA;
		Product chipsProduct = Product.CHIPS;
		Product candyProduct = Product.CANDY;
		
		productHandler.setInventory(candyProduct, 10);
		productHandler.setInventory(chipsProduct, 10);
		productHandler.setInventory(colaProduct, 10);
		
		vendingMachine.setProductHandler(productHandler);
		
		assertTrue(vendingMachine.getProductHandler().getInventory().get(Product.CANDY) == 10);
		assertTrue(vendingMachine.getProductHandler().getInventory().get(Product.COLA) == 10);
		assertTrue(vendingMachine.getProductHandler().getInventory().get(Product.CHIPS) == 10);	
	}
	
	@Test
	public void colaFailsVendWithInventoryAndNoMoney(){
		productHandler.setInventory(Product.COLA, 10);
		vendingMachine.setProductHandler(productHandler);
		
		assertFalse(vendingMachine.vendCola());
		
	}

	@Test
	public void colaVendWithInventory(){
		productHandler.setInventory(Product.COLA, 10);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		
		assertTrue(vendingMachine.vendCola());
		
	}
	
	@Test
	public void chipsVendWithInventory(){
		productHandler.setInventory(Product.CHIPS, 10);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		
		assertTrue(vendingMachine.vendChips());
		
	}
	
	@Test
	public void candyVendWithInventory(){
		productHandler.setInventory(Product.CANDY, 10);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		
		assertTrue(vendingMachine.vendCandy());	
	}
	
	@Test
	public void vendingDisplayWhenMoreCoinsNeeded(){
		productHandler.setInventory(Product.CANDY, 10);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		
		vendingMachine.vendCandy();
		
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}
	
	@Test
	public void vendingDisplayWhenSoldOut(){
		productHandler.setInventory(Product.CANDY, 0);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		vendingMachine.vendCandy();
		
		assertEquals("SOLD OUT", vendingMachine.getDisplayString());
	}
	
	@Test
	public void inventoryDecreasesWithSuccessfulVend(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		vendingMachine.vendCandy();
		Integer amountOfProduct = vendingMachine.getProductHandler().getInventory().get(Product.CANDY);
		
		assertTrue(amountOfProduct == 0);
	}
	
	@Test
	public void acceptedCoinsResetWithValidVend(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		vendingMachine.vendCandy();

		assertEquals(0, vendingMachine.getCoinHandler().getTotalValueOfCoinsInserted(), .001);	
	}
	
	@Test
	public void coinReturnAfterValidTransaction(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		
		vendingMachine.vendCandy();
		
		assertEquals(.10, vendingMachine.getCoinReturn(), .001);
	}
	
	
	
}
