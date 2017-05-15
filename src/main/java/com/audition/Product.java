package com.audition;

public enum Product {
	COLA(1.00),
	CHIPS(.5),
	CANDY(.65);
	
	private double productValue;
	
	Product(double value){
		productValue = value;
	}
	
	public double getProductValue(){
		return productValue;
	}
}