package com.audition;

public enum Coin {
	PENNY(.01, 1),
	NICKEL(.05, 2),
	DIME(.1,3),
	QUARTER(.25,4);
	
	private double coinValue;
	private int coinWeight;
	
	Coin(double value, int weight){
		setCoinValue(value);
		setCoinWeight(weight);
	}

	public double getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(double coinValue) {
		this.coinValue = coinValue;
	}

	public int getCoinWeight() {
		return coinWeight;
	}

	public void setCoinWeight(int coinWeight) {
		this.coinWeight = coinWeight;
	}
}
