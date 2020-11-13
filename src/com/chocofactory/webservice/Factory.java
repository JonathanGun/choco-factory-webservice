package com.chocofactory.webservice;
import java.io.IOException;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="com.chocofactory.webservice.IFactory")
public class Factory implements IFactory {
	
	private FactoryDAO db;

	public Factory() {
		try {
			this.db = new FactoryDAO();
		} catch (IOException e) {
			this.db = null;
		}
	}
	
//	ChocoRequest
	public Request getRequest(int id) {
		return Request.fromResultSet(this.db.select(Request.read(id)));
	}
	
	public String getRequestStatus(int id) {
		Request request = this.getRequest(id);
		return (request == null)? null: request.status;
	}
	
	public String addRequest(int chocoid, int amount) {
		return this.db.insert(Request.create(chocoid, amount));
	}
	
	public String updateRequest(int requestid, String status) {
		return this.db.update(Request.update(requestid, status));
	}
	
	public String deliverRequest(int requestid) {
		return this.updateRequest(requestid, "Delivered");
	}
	
//	Recipe
	public List<Recipe> getRecipe(int id) {
		return Recipe.fromResultSet(this.db.select(Recipe.read(id)));
	}
	
	public String addRecipe(int chocoid, List<Integer> ids, List<Integer> amounts) {
		return this.db.insert(Recipe.create(chocoid, ids, amounts));
	}
	
//	ChocoStock
	public ChocoStock getChocoStock(int id) {
		return ChocoStock.fromResultSet(this.db.select(ChocoStock.read(id)));
	}
	
//	IngredientStock
	public List<IngredientStock> getIngredientStock(int id) {
		return IngredientStock.fromResultSet(this.db.select(IngredientStock.read(id)));
	}
	
	public boolean updateIngredientStock(int stockid, int amount) {
		return this.db.updateUtil(IngredientStock.update(stockid, amount)) == 1;
	}
	
	public List<IngredientStock> getIngredientStockNotExpired(int id) {
		return IngredientStock.fromResultSet(this.db.select(IngredientStock.readNotExpired(id)));
	}
	
	public int getIngredientStockSum(int id) {
		return this.getIngredientStockNotExpired(id).stream().map(ingredient -> ingredient.amount).reduce(0, (a, b) -> a+b);
	}
	
	public boolean process(int chocoid, int amount) {
		if(amount <= 0) {
			return true;
		}
		List<Recipe> recipes = this.getRecipe(chocoid);
		int can_make = Integer.MAX_VALUE;
		for(Recipe recipe: recipes) {
			can_make = Math.min(can_make, Math.floorDiv(this.getIngredientStockSum(recipe.ingredientID), recipe.amount));
		}
		if (can_make < amount) {
			return false;
		}
		for(Recipe recipe: recipes) {
			int amount_need = recipe.amount * amount;
			System.out.println("Ingredient " + recipe.ingredientID + " needs " + recipe.amount + " each");
			for(IngredientStock ingredient: this.getIngredientStockNotExpired(recipe.ingredientID)) {
				amount_need -= ingredient.amount;
				if(amount_need < 0) {
					this.updateIngredientStock(ingredient.stockID, -amount_need);
					break;
				} else {
					this.updateIngredientStock(ingredient.stockID, 0);
				}
			}
		}
		return true;
	}
	
	public boolean deleteIngredientStock(int stockid) {
		return this.db.updateUtil(IngredientStock.delete(stockid)) == 1;
	}
	
//	Balance
	public int getBalance() {
		Balance balance = Balance.fromResultSet(this.db.select(Balance.read()));
		return (balance == null)? null: balance.balance;
	}
	
	public String addBalance(int addAmount) {
		return this.updateBalance(this.getBalance() + addAmount);
	}
	
	public String updateBalance(int amount) {
		return this.db.update(Balance.update(amount));
	}
}
