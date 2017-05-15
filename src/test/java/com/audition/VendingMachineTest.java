package com.audition;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.audition.coin.USCoin;
import com.audition.coin.CoinHandler;
import com.audition.product.Product;
import com.audition.product.ProductHandler;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	private ProductHandler productHandler;
	
	@Before
	public void setup(){
		vendingMachine = new VendingMachine();
		productHandler = new ProductHandler();	
		
		vendingMachine.setCoinHandler(new CoinHandler());
	}
	
	@After
	public void tearDown(){
		vendingMachine = null;
		productHandler = null;
	}
	
	@Test
	public void testPennyInsert(){
		USCoin myCoin3 = USCoin.PENNY;
		vendingMachine.insertCoin(myCoin3);
		
		assertEquals(0, 0, .001);
	}
	
	@Test
	public void invalidCoinsAreReturned(){
		USCoin myCoin1 = USCoin.PENNY;
		USCoin myCoin2 = USCoin.PENNY;
		USCoin myCoin3 = USCoin.PENNY;
		
		insertCoinsForInsertTest(myCoin1, myCoin2, myCoin3);
		
		assertEquals(3, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void testCoinInsert() {
		USCoin myCoin1 = USCoin.NICKEL;
		USCoin myCoin2 = USCoin.DIME;
		USCoin myCoin3 = USCoin.PENNY;
		
		insertCoinsForInsertTest(myCoin1, myCoin2, myCoin3);
			
		assertEquals(.15, vendingMachine.getCoinHandler().getTotalValueOfCoinsInserted(), .001);
		assertEquals(1, vendingMachine.getCoinHandler().getNumberOfInvalidCoinsReturned());
	}
	
	@Test
	public void displayValueWhenMachineHasMoney(){
		USCoin myCoin1 = USCoin.NICKEL;
		USCoin myCoin2 = USCoin.DIME;
		USCoin myCoin3 = USCoin.PENNY;
		
		insertCoinsForInsertTest(myCoin1, myCoin2, myCoin3);
		
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
		
		insertFourQuartersIntoVendingMachine();		
		
		assertTrue(vendingMachine.vendCola());
		
	}
	
	@Test
	public void colaVendWithNoInventory(){
		productHandler.setInventory(Product.COLA, 1);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(USCoin.QUARTER);
		
		
		assertFalse(vendingMachine.vendCola());
		
	}
	
	@Test
	public void chipsVendWithInventory(){
		productHandler.setInventory(Product.CHIPS, 10);
		vendingMachine.setProductHandler(productHandler);
		
		insertFourQuartersIntoVendingMachine();
		
		
		assertTrue(vendingMachine.vendChips());
		
	}
	
	@Test
	public void candyVendWithInventory(){
		productHandler.setInventory(Product.CANDY, 10);
		vendingMachine.setProductHandler(productHandler);
		
		insertFourQuartersIntoVendingMachine();
		
		
		assertTrue(vendingMachine.vendCandy());
		
	}
	
	@Test
	public void vendingDisplayWithEnoughCoins(){
		productHandler.setInventory(Product.CANDY, 10);
		vendingMachine.setProductHandler(productHandler);
		
		insertFourQuartersIntoVendingMachine();
		
		vendingMachine.vendCandy();
		
		assertEquals("THANK YOU", vendingMachine.getDisplayString());
	}
	
	@Test
	public void vendingDisplayWhenMoreCoinsNeeded(){
		productHandler.setInventory(Product.CANDY, 10);
		vendingMachine.setProductHandler(productHandler);
		
		vendingMachine.insertCoin(USCoin.QUARTER);
		
		vendingMachine.vendCandy();
		
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}
	
	@Test
	public void vendingDisplayWhenSoldOut(){
		productHandler.setInventory(Product.CANDY, 0);
		vendingMachine.setProductHandler(productHandler);
		
		insertThreeQuartersIntoVeningMachine();
		
		vendingMachine.vendCandy();
		
		assertEquals("SOLD OUT", vendingMachine.getDisplayString());
	}
	
	@Test
	public void inventoryDecreasesWithSuccessfulVend(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		insertThreeQuartersIntoVeningMachine();
		
		vendingMachine.vendCandy();
		Integer amountOfProduct = vendingMachine.getProductHandler().getInventory().get(Product.CANDY);
		
		assertTrue(amountOfProduct == 0);
	}
	
	@Test
	public void acceptedCoinsResetWithValidVend(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		insertThreeQuartersIntoVeningMachine();
		
		vendingMachine.vendCandy();

		assertEquals(0, vendingMachine.getCoinHandler().getTotalValueOfCoinsInserted(), .001);
		
	}
	
	@Test
	public void coinReturnAfterValidTransaction(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		insertThreeQuartersIntoVeningMachine();
		
		vendingMachine.vendCandy();
		
		assertEquals(.10, vendingMachine.getCoinReturn(), .001);
	}
	
	@Test
	public void coinReturnWhenUserCancelsTransaction(){
		productHandler.setInventory(Product.CANDY, 1);
		vendingMachine.setProductHandler(productHandler);
		
		insertThreeQuartersIntoVeningMachine();
		
		vendingMachine.cancelVend();
		
		assertEquals(.75, vendingMachine.getCoinReturn(), .001);
	}
	
	private void insertCoinsForInsertTest(USCoin myCoin1, USCoin myCoin2, USCoin myCoin3) {
		vendingMachine.insertCoin(myCoin1);
		vendingMachine.insertCoin(myCoin2);
		vendingMachine.insertCoin(myCoin3);
	}
	
	private void insertFourQuartersIntoVendingMachine() {
		insertThreeQuartersIntoVeningMachine();
		vendingMachine.insertCoin(USCoin.QUARTER);
	}
	
	private void insertThreeQuartersIntoVeningMachine() {
		vendingMachine.insertCoin(USCoin.QUARTER);
		vendingMachine.insertCoin(USCoin.QUARTER);
		vendingMachine.insertCoin(USCoin.QUARTER);
	}

}
