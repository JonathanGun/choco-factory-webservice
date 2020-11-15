package com.chocofactory.webservice.ingredientstock;

public class IngredientAmount {
	public int ingredientID;
	public int amount;
	public int expired;
	
	public IngredientAmount(int ingredientID, int amount, int expired) {
		this.ingredientID = ingredientID;
		this.amount = amount;
		this.expired = expired;
	}
}
