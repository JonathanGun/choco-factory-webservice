package com.chocofactory.webservice.recipe;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;

@WebService(endpointInterface="com.chocofactory.webservice.recipe.IRecipeSOAP")
public class RecipeSOAP  implements IRecipeSOAP{

	public List<Recipe> getRecipe(int id) {
		return Recipe.fromResultSet(FactoryDAO.select(Recipe.read(id)));
	}
	
	public static List<Recipe> getRecipeStatic(int id) {
		return Recipe.fromResultSet(FactoryDAO.select(Recipe.read(id)));
	}
	
	public boolean addRecipe(int chocoid, List<Integer> ids, List<Integer> amounts) {
		return FactoryDAO.insert(Recipe.create(chocoid, ids, amounts));
	}
}
