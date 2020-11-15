package com.chocofactory.webservice.ingredientstock;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IIngredientStockSOAP {
	public List<IngredientAmount> getIngredientStockList();
	public List<IngredientStock> getIngredientStock(@WebParam(name="id") int id);
	public List<IngredientStock> getIngredientStockNotExpired(@WebParam(name="id") int id);
	public int getIngredientStockSum(@WebParam(name="id") int id);
	public boolean process(@WebParam(name="chocoid") int chocoid, @WebParam(name="amount") int amount);
	public boolean addIngredientStock(@WebParam(name="ingredientid") int ingredientid, @WebParam(name="amount") int amount);
	public boolean updateIngredientStock(@WebParam(name="stockid") int stockid, @WebParam(name="amount") int amount);
	public boolean deleteIngredientStock(@WebParam(name="stockid") int stockid);
}
