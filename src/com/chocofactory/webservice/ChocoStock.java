package com.chocofactory.webservice;

import java.sql.ResultSet;

public class ChocoStock {
	public int chocoID;
	public int amount;
	public static String dbname = "ChocoStock";
	
	public ChocoStock(int chocoID, int amount) {
		this.chocoID = chocoID;
		this.amount = amount;
	}
	
	public static String read(int chocoID) {
		return "SELECT * FROM " + dbname + " WHERE ChocoID='" + chocoID + "'";
	}
	
	public static ChocoStock fromResultSet(ResultSet resultset) {
		try {
			while(resultset.next()) {
				return new ChocoStock(
						resultset.getInt(1),
						resultset.getInt(2)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
