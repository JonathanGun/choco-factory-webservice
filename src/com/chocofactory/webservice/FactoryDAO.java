package com.chocofactory.webservice;

import java.sql.*;
import java.util.Properties;
import java.io.*;
 

public class FactoryDAO {
	static Connection connection;
	static Statement statement;
	private static String DB_DRIVER;
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	
	public static Properties readPropertiesFile(String fileName) throws IOException {
	    FileInputStream fis = null;
	    Properties prop = null;
	    try {
	    	fis = new FileInputStream(fileName);
	    	prop = new Properties();
	    	prop.load(fis);
	    } catch(FileNotFoundException fnfe) {
	    	fnfe.printStackTrace();
	    } catch(IOException ioe) {
	    	ioe.printStackTrace();
	    } finally {
	    	fis.close();
	    }
	    return prop;
	}

	public static void initialize() {
		try {
			Properties prop = readPropertiesFile("src/com/chocofactory/webservice/jdbc.properties");
			DB_DRIVER = prop.getProperty("jdbc.driverClassName");
			DB_URL = prop.getProperty("jdbc.url");
			DB_USERNAME = prop.getProperty("jdbc.username");
			DB_PASSWORD = prop.getProperty("jdbc.password");
			
	        System.out.println("jdbc.driverClassName = " + DB_DRIVER);
	        System.out.println("jdbc.url = " + DB_URL);
	        System.out.println("jdbc.username = " + DB_USERNAME);
	        System.out.println("jdbc.password = " + DB_PASSWORD);
	        
			System.out.println("Initializing DB Connection");
			Class.forName(DB_DRIVER);
			connection=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet select(String query) {
		try {
			statement=connection.createStatement();
			System.out.println(query);
			return statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int updateUtil(String query) {
		try {
			statement=connection.createStatement();
			System.out.println(query);
			return statement.executeUpdate(query);
		}  catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static String update(String query) {
		int count = updateUtil(query);
		return (count >= 0)? "Updated Successfully": "Error Updating Database";
	}
	
	public static boolean insert(String query) {
		int count = updateUtil(query);
		return (count >= 0);
	}
}