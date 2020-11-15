package com.chocofactory.webservice.balance;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IBalanceSOAP {
	public int getBalance();
	public boolean addBalance(@WebParam(name="amount") int addAmount);
	public boolean updateBalance(@WebParam(name="amount") int amount);
}
