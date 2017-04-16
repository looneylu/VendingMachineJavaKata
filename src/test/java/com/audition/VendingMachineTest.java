package com.audition;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
		vendingMachine = null;
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

}
