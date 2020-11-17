package com.chocofactory.webservice.balance;

import javax.jws.WebService;

import com.chocofactory.webservice.FactoryDAO;

@WebService(endpointInterface="com.chocofactory.webservice.balance.IBalanceSOAP")
public class BalanceSOAP implements IBalanceSOAP {

	public int getBalance() {
		Balance balance = Balance.fromResultSet(FactoryDAO.select(Balance.read()));
		return (balance == null)? null: balance.balance;
	}
	
	public static int getBalanceStatic() {
		Balance balance = Balance.fromResultSet(FactoryDAO.select(Balance.read()));
		return (balance == null)? null: balance.balance;
	}
	
	public boolean addBalance(int addAmount) {
		return this.updateBalance(this.getBalance() + addAmount);
	}
	
	public static boolean addBalanceStatic(int addAmount) {
		return BalanceSOAP.updateBalanceStatic(BalanceSOAP.getBalanceStatic() + addAmount);
	}
	
	public boolean updateBalance(int amount) {
		return FactoryDAO.updateUtil(Balance.update(amount)) >= 0;
	}
	
	public static boolean updateBalanceStatic(int amount) {
		return FactoryDAO.updateUtil(Balance.update(amount)) >= 0;
	}
}
