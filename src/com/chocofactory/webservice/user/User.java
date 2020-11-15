package com.chocofactory.webservice.user;

import java.sql.ResultSet;
import java.util.Date;

public class User {
	public int userID;
	public String username;
	public String password;
	public String email;
	public Date dateRegistered;
	public static String dbname = "User";
	
	public User(int userID, String username, String password, String email, Date dateRegistered) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateRegistered = dateRegistered;
	}
	
	public static String read(String username, String password) {
		return "SELECT * FROM " + dbname + " WHERE Username='" + username + "' AND Password='" + password + "'";
	}
	
	public static User fromResultSet(ResultSet resultset) {
		try {
			while(resultset.next()) {
				return new User(
						resultset.getInt(1),
						resultset.getString(2),
						resultset.getString(3),
						resultset.getString(4),
						resultset.getDate(5)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
