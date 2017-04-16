package com.audition;

import java.text.DecimalFormat;

public class VendingMachine {
	private CoinHandler coinHandler = new CoinHandler();
	private ProductHandler productHandler = new ProductHandler();
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
	
	public CoinHandler getCoinHandler() {
		return coinHandler;
	}

	public void setCoinHandler(CoinHandler coinHandler) {
		this.coinHandler = coinHandler;
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
				getCoinHandler().acceptTrasaction();
				return true;
			} else{
				setDisplayString("SOLD OUT");
			}
		}
		return false;
	}
	
}
