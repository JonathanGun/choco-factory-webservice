package com.chocofactory.webservice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IngredientStock {
	public int stockID;
	public int ingredientID;
	public int amount;
	public Date expiryDate;
	public static String dbname = "IngredientStock";
	
	public IngredientStock(int stockID, int ingredientID, int amount, Date expiryDate) {
		this.stockID = stockID;
		this.ingredientID = ingredientID;
		this.amount = amount;
		this.expiryDate = expiryDate;
	}
	
	public static String read(int ingredientID) {
		return "SELECT * FROM " + dbname + " WHERE IngredientID=" + ingredientID + " AND Amount > 0";
	}
	
	public static String readNotExpired(int ingredientID) {
		return "SELECT * FROM " + dbname + " WHERE IngredientID=" + ingredientID + " AND ExpiryDate > NOW() AND Amount > 0 ORDER BY ExpiryDate ASC";
	}
	
	public static String update(int stockID, int amount) {
		return "UPDATE " + dbname + " SET Amount=" + amount + " WHERE StockID=" + stockID;
	}
	
	public static String delete(int stockID) {
		return "DELETE FROM " + dbname + " WHERE StockID=" + stockID;
	}
	
	public static List<IngredientStock> fromResultSet(ResultSet resultset) {
		List<IngredientStock> ingredientStocks = new ArrayList<>();
		try {
			while(resultset.next()) {
				ingredientStocks.add(new IngredientStock(
						resultset.getInt(1),
						resultset.getInt(2),
						resultset.getInt(3),
						resultset.getDate(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ingredientStocks;
	}
}
