package com.chocofactory.webservice.ingredientstock;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	public static String read() {
		return "SELECT IngredientID, SUM(amount) AS Amount FROM " + dbname + " GROUP BY (IngredientID) ORDER BY Amount ASC";
	}

	public static String read(int ingredientID) {
		return "SELECT * FROM " + dbname + " WHERE IngredientID=" + ingredientID + " AND Amount > 0 ORDER BY ExpiryDate ASC";
	}
	
	public static String readPK(int stockID) {
		return "SELECT * FROM " + dbname + " WHERE StockID=" + stockID;
	}
	
	public static String create(int ingredientID, int amount) {
		return "INSERT INTO " + dbname + "(`IngredientID`,`Amount`,`ExpiryDate`) VALUES " +
				"(" +
					ingredientID + "," +
					amount + "," +
					"'" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now().plusYears(1)) + "'" +
				")";
	}
	
	public static String readNotExpired() {
		return "SELECT IngredientID, Amount, IFNULL(Expired, 0) AS Expired FROM ("
				+ "("
					+ "SELECT IngredientID, SUM(amount) AS Amount FROM " + dbname + " WHERE ExpiryDate > NOW() GROUP BY (IngredientID)"
				+ ") AS T LEFT JOIN ("
					+ "SELECT IngredientID, SUM(amount) AS Expired FROM " + dbname + " WHERE ExpiryDate <= NOW() GROUP BY (IngredientID)"
				+ ") AS U "
				+ "USING (IngredientID)) "
				+ "ORDER BY Amount";
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
	
	public static List<IngredientAmount> fromResultSetAll(ResultSet resultset) {
		List<IngredientAmount> ingredientStocks = new ArrayList<>();
		try {
			while(resultset.next()) {
				ingredientStocks.add(new IngredientAmount(
						resultset.getInt(1),
						resultset.getInt(2),
						resultset.getInt(3)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ingredientStocks;
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
