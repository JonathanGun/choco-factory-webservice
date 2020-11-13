package com.chocofactory.webservice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
	public int chocoID;
	public int ingredientID;
	public int amount;
	public static String dbname = "Recipe";
	
	public Recipe(int chocoID, int ingredientID, int amount) {
		this.chocoID = chocoID;
		this.ingredientID = ingredientID;
		this.amount = amount;
	}
	
	public static String read(int chocoID) {
		return "SELECT * FROM " + dbname + " WHERE ChocoID='" + chocoID + "'";
	}
	
	public static String create(int chocoid, List<Integer> ids, List<Integer> amounts) {
		String addSQL="INSERT INTO " + dbname + " VALUES ";
		for(int i = 0; i < Math.min(ids.size(), amounts.size()); i++) {
			addSQL += "(" +
					chocoid +","+
					ids.get(i) +","+
					amounts.get(i) +
				"),";
		}
		return addSQL.substring(0, addSQL.length() - 1);
	}
	
	public static List<Recipe> fromResultSet(ResultSet resultset) {
		List<Recipe> recipes = new ArrayList<>();
		try {
			while(resultset.next()) {
				recipes.add(new Recipe(
						resultset.getInt(1),
						resultset.getInt(2),
						resultset.getInt(3)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return recipes;
	}
}
