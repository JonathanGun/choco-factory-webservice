package com.chocofactory.webservice.ingredientstock;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;
import com.chocofactory.webservice.chocostock.ChocoStockSOAP;
import com.chocofactory.webservice.recipe.Recipe;
import com.chocofactory.webservice.recipe.RecipeSOAP;

@WebService(endpointInterface="com.chocofactory.webservice.ingredientstock.IIngredientStockSOAP")
public class IngredientStockSOAP implements IIngredientStockSOAP{
	
	public List<IngredientAmount> getIngredientStockList(){
		return IngredientStock.fromResultSetAll(FactoryDAO.select(IngredientStock.readNotExpired()));
	}
	
	public List<IngredientStock> getIngredientStock(int id) {
		return IngredientStock.fromResultSet(FactoryDAO.select(IngredientStock.read(id)));
	}
	
	public Integer addIngredientStock(int ingredientid, int amount) {
		return FactoryDAO.insert(IngredientStock.create(ingredientid, amount));
	}
	
	public boolean updateIngredientStock(int stockid, int amount) {
		return FactoryDAO.updateUtil(IngredientStock.update(stockid, amount)) == 1;
	}
	
	public List<IngredientStock> getIngredientStockNotExpired(int id) {
		return IngredientStock.fromResultSet(FactoryDAO.select(IngredientStock.readNotExpired(id)));
	}
	
	public int getIngredientStockSum(int id) {
		return this.getIngredientStockNotExpired(id).stream().map(ingredient -> ingredient.amount).reduce(0, (a, b) -> a+b);
	}
	
	public boolean process(int chocoid, int amount) {
		if(amount <= 0) {
			return true;
		}
		List<Recipe> recipes = RecipeSOAP.getRecipeStatic(chocoid);
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
		ChocoStockSOAP.addChocoStockStatic(chocoid, amount);
		return true;
	}
	
	public boolean deleteIngredientStock(int stockid) {
		return FactoryDAO.updateUtil(IngredientStock.delete(stockid)) == 1;
	}

}
