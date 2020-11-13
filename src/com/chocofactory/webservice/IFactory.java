package com.chocofactory.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IFactory {
//	Request
	public Request getRequest(@WebParam(name="id") int id);
	public String getRequestStatus(@WebParam(name="id") int id);
	public String addRequest(@WebParam(name="chocoid") int chocoid, @WebParam(name="amount") int amount);
	public String deliverRequest(@WebParam(name="id") int requestid);
	
//	Recipe
	public List<Recipe> getRecipe(@WebParam(name="id") int id);
	public String addRecipe(@WebParam(name="chocoid") int chocoid, @WebParam(name="ids") List<Integer> ids, @WebParam(name="amounts") List<Integer> amounts);
	
//	ChocoStock
	public ChocoStock getChocoStock(@WebParam(name="id") int id);
	
//	IngredientStock
	public List<IngredientStock> getIngredientStock(@WebParam(name="id") int id);
	public List<IngredientStock> getIngredientStockNotExpired(@WebParam(name="id") int id);
	public int getIngredientStockSum(@WebParam(name="id") int id);
	public boolean process(@WebParam(name="chocoid") int chocoid, @WebParam(name="amount") int amount);
	public boolean updateIngredientStock(@WebParam(name="stockid") int stockid, @WebParam(name="amount") int amount);
	public boolean deleteIngredientStock(@WebParam(name="stockid") int stockid);
	
//	Balance
	public int getBalance();
	public String addBalance(@WebParam(name="amount") int addAmount);
	public String updateBalance(@WebParam(name="amount") int amount);
}
