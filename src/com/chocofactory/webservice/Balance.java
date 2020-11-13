package com.chocofactory.webservice;

import java.sql.ResultSet;

public class Balance {
	public int balance;
	public static String dbname = "Balance";
	
	public Balance(int balance) {
		this.balance = balance;
	}
	
	public static String read() {
		return "SELECT * FROM " + dbname;
	}
	
	public static String update(int amount) {
		return "UPDATE " + dbname + " SET Balance=" + amount;
	}
	
	public static Balance fromResultSet(ResultSet resultset) {
		try {
			while(resultset.next()) {
				return new Balance(
						resultset.getInt(1)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
