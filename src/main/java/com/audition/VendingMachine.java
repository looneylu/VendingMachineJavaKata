package com.audition;

import java.text.DecimalFormat;

import com.audition.coin.CoinHandler;
import com.audition.coin.Coin;
import com.audition.product.Product;
import com.audition.product.ProductHandler;

public class VendingMachine {	
	
	private CoinHandler coinHandler;
	private ProductHandler productHandler;
	private double coinReturn = 0;
	
	private String displayString = "INSERT COIN"; 
	
	public double insertCoin(Coin coin){
		getCoinHandler().insertCoin(coin);
		setStringToDisplayAfterCoinInsert();
		
		return getCoinHandler().getTotalValueOfCoinsInserted();
	}
	
	public Boolean vendCola(){
		return vendResult(Product.COLA);
	}
	
	public Boolean vendChips(){
		return vendResult(Product.CHIPS);
	}
	
	public Boolean vendCandy(){
		return vendResult(Product.CANDY);
	}
	
	public double cancelVend(){
		setCoinReturn(getCoinHandler().returnCoins(0));
		getCoinHandler().acceptTrasaction();
		
		return getCoinReturn();
	}

	public CoinHandler getCoinHandler(){
		return coinHandler;
	}
	
	public void setCoinHandler(CoinHandler aCoinHandler){
		this.coinHandler = aCoinHandler;
	}

	public String getDisplayString() {
		return displayString;
	}

	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}
	
	public ProductHandler getProductHandler() {
		return productHandler;
	}


	public void setProductHandler(ProductHandler productHandler) {
		this.productHandler = productHandler;
	}
	
	public double getCoinReturn() {
		return coinReturn;
	}

	public void setCoinReturn(double coinReturn) {
		this.coinReturn = coinReturn;
	}
	
	private void setStringToDisplayAfterCoinInsert() {
		DecimalFormat doubleToFormat = new DecimalFormat(".##");
		
		if (coinHandler.getTotalValueOfCoinsInserted() > 0){
			setDisplayString(String.valueOf(doubleToFormat.format(coinHandler.getTotalValueOfCoinsInserted())));
		} else{ 
			setDisplayString("INSERT COIN");
		}
	}
	
	private Boolean vendProduct(Product product) {
		double moneyInserted = getCoinHandler().getTotalValueOfCoinsInserted();
		
		if(moneyInserted >= product.getProductValue() && getProductHandler().vendProduct(product)){
			return true;
		}
		return false;
	}
	
	private Boolean vendResult(Product product) {
		if (getCoinHandler().getTotalValueOfCoinsInserted() < product.getProductValue()){
			setDisplayString("INSERT COIN");
		}else{
			if (vendProduct(product)){
				setDisplayString("THANK YOU");
				setCoinReturn(getCoinHandler().returnCoins(product.getProductValue()));
				getCoinHandler().acceptTrasaction();
				return true;
			} else{
				setDisplayString("SOLD OUT");
			}
		}
		return false;
	}

}
