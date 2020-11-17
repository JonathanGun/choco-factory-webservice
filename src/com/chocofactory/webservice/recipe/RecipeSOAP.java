package com.chocofactory.webservice.recipe;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;
import com.chocofactory.webservice.chocostock.ChocoStockSOAP;

@WebService(endpointInterface="com.chocofactory.webservice.recipe.IRecipeSOAP")
public class RecipeSOAP  implements IRecipeSOAP{

	public List<Recipe> getRecipe(int id) {
		return Recipe.fromResultSet(FactoryDAO.select(Recipe.read(id)));
	}
	
	public static List<Recipe> getRecipeStatic(int id) {
		return Recipe.fromResultSet(FactoryDAO.select(Recipe.read(id)));
	}
	
	public Integer addRecipe(int chocoid, String name, int price, List<Integer> ids, List<Integer> amounts) {
		if(ChocoStockSOAP.createChocoStock(chocoid, name, price) != null) {
			return FactoryDAO.insert(Recipe.create(chocoid, ids, amounts));
		}
		return null;
	}
}
