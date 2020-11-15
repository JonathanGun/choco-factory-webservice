package com.chocofactory.webservice.chocostock;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IChocoStockSOAP {
	public List<ChocoStock> getChocoStockList();
	public int getChocoStock(@WebParam(name="id") int id);
	public boolean addChocoStock(@WebParam(name="id") int id, @WebParam(name="amount") int addAmount);
	public boolean updateChocoStock(@WebParam(name="id") int id, @WebParam(name="amount") int amount);
}
