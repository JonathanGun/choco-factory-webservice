package com.chocofactory.webservice.request;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Request{
	public int requestID;
	public int chocoID;
	public int amount;
	public String status;
	public Date requestDate;
	public static String dbname = "ChocoRequest";
	
	public Request(
			int requestID,
			int chocoID,
			int amount,
			String status,
			Date requestDate) {
		this.requestID = requestID;
		this.chocoID = chocoID;
		this.amount = amount;
		this.status = status;
		this.requestDate = requestDate;
	}
	
	public static String read() {
		return "SELECT * FROM " + dbname + " ORDER BY RequestDate ASC";
	}
	
	public static String read(int requestID) {
		return "SELECT * FROM " + dbname + " WHERE RequestID='" + requestID + "'";
	}
	
	public static String create(int chocoid, int amount) {
		return "INSERT INTO " + dbname + "(`ChocoID`, `Amount`, `RequestDate`) VALUES " + 
				"(" +
					chocoid + "," +
					amount + "," +
					"'" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "'" +
				")";
	}
	
	public static String update(int requestid, String status) {
		return "UPDATE " + dbname + " SET Status='" + status + "' WHERE RequestID=" + requestid;
	}
	
	public static List<Request> fromResultSetAll(ResultSet resultset) {
		List<Request> requests = new ArrayList<>();
		try {
			while(resultset.next()) {
				requests.add(new Request(
						resultset.getInt(1),
						resultset.getInt(2),
						resultset.getInt(3),
						resultset.getString(4),
						resultset.getDate(5)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return requests;
	}
	
	public static Request fromResultSet(ResultSet resultset) {
		try {
			while(resultset.next()) {
				return new Request(
						resultset.getInt(1),
						resultset.getInt(2),
						resultset.getInt(3),
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
