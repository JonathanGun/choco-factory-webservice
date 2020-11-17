package com.chocofactory.webservice.chocostock;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChocoStock {
	public int chocoID;
	public String name;
	public int amount;
	public int price;
	public static String dbname = "ChocoStock";
	
	public ChocoStock(int chocoID, String name, int amount, int price) {
		this.chocoID = chocoID;
		this.name = name; 
		this.amount = amount;
		this.price = price;
	}
	
	public static String read(int chocoID) {
		return "SELECT * FROM " + dbname + " WHERE ChocoID='" + chocoID + "'";
	}
	
	public static String create(int chocoID, String name, int price) {
		int amount = 0;
		return "INSERT INTO " + dbname + " VALUES(" + chocoID + ",'" + name + "'," + amount + "," + price + ")";
	}
	
	public static String read() {
		return "SELECT * FROM " + dbname + " ORDER BY amount ASC";
	}
	
	public static String update(int chocoid, int amount) {
		return "UPDATE " + dbname + " SET Amount=" + amount + " WHERE ChocoID=" + chocoid;
	}
	
	public static List<ChocoStock> fromResultSetAll(ResultSet resultset) {
		List<ChocoStock> chocostocks = new ArrayList<>();
		try {
			while(resultset.next()) {
				chocostocks.add(new ChocoStock(
						resultset.getInt(1),
						resultset.getString(2),
						resultset.getInt(3),
						resultset.getInt(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chocostocks;
	}
	
	public static ChocoStock fromResultSet(ResultSet resultset) {
		try {
			while(resultset.next()) {
				return new ChocoStock(
						resultset.getInt(1),
						resultset.getString(2),
						resultset.getInt(3),
						resultset.getInt(4)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
