package com.chocofactory.webservice;

import javax.xml.ws.Endpoint;
import com.chocofactory.webservice.balance.BalanceSOAP;
import com.chocofactory.webservice.chocostock.ChocoStockSOAP;
import com.chocofactory.webservice.ingredientstock.IngredientStockSOAP;
import com.chocofactory.webservice.recipe.RecipeSOAP;
import com.chocofactory.webservice.request.RequestSOAP;
import com.chocofactory.webservice.user.UserSOAP;

public class FactoryPublisher {
	public static void main(String[] args) {
		FactoryDAO.initialize();
		Endpoint.publish("http://localhost:8080/balance/", new BalanceSOAP());
		Endpoint.publish("http://localhost:8080/chocostock/", new ChocoStockSOAP());
		Endpoint.publish("http://localhost:8080/ingredientstock/", new IngredientStockSOAP());
		Endpoint.publish("http://localhost:8080/recipe/", new RecipeSOAP());
		Endpoint.publish("http://localhost:8080/request/", new RequestSOAP());
		Endpoint.publish("http://localhost:8080/user/", new UserSOAP());
	}
}
