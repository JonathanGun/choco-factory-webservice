package com.chocofactory.webservice.recipe;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IRecipeSOAP {
	public List<Recipe> getRecipe(@WebParam(name="id") int id);
	public boolean addRecipe(@WebParam(name="chocoid") int chocoid, @WebParam(name="ids") List<Integer> ids, @WebParam(name="amounts") List<Integer> amounts);
}
