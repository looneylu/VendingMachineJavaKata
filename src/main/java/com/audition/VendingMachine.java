package com.audition;

public class VendingMachine {
	private CoinHandler coinHandler = new CoinHandler();

	public double insertCoin(Coin coin){
		
		getCoinHandler().insertCoin(coin);
		
		return getCoinHandler().getTotalValueOfCoinsInserted();
	}
	
	public CoinHandler getCoinHandler() {
		return coinHandler;
	}

	public void setCoinHandler(CoinHandler coinHandler) {
		this.coinHandler = coinHandler;
	}
	
	
}
