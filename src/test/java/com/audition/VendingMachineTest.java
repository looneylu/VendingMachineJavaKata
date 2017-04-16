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
		fail("Not yet implemented");
	}

}
