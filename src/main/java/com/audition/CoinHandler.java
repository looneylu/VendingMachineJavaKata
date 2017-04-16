package com.audition;

public class CoinHandler {
	private double totalValueOfCoinsInserted = 0.00;
	private int numberOfInvalidCoinsReturned = 0;
	
	public void insertCoin(Coin coin){
		if (coinIsValid(coin)){
			setTotalValueOfCoinsInserted(coin.getCoinValue());
		} else{
			setNumberOfInvalidCoinsReturned();
		}
	}

	public double getTotalValueOfCoinsInserted() {
		return totalValueOfCoinsInserted;
	}

	public void setTotalValueOfCoinsInserted(double totalValueOfCoinsInserted) {
		this.totalValueOfCoinsInserted += totalValueOfCoinsInserted;
	}

	public int getNumberOfInvalidCoinsReturned() {
		return numberOfInvalidCoinsReturned;
	}

	public void setNumberOfInvalidCoinsReturned() {
		this.numberOfInvalidCoinsReturned += 1;
	}
	
	private Boolean coinIsValid(Coin coin) {
		if (coin.getCoinWeight() < 2)
			return false;
	
		return true;
	}
}
