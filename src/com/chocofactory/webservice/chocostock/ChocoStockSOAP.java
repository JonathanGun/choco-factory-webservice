package com.chocofactory.webservice.chocostock;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;
import com.chocofactory.webservice.request.Request;

@WebService(endpointInterface="com.chocofactory.webservice.chocostock.IChocoStockSOAP")
public class ChocoStockSOAP  implements IChocoStockSOAP {

	public List<ChocoStock> getChocoStockList() {
		return ChocoStock.fromResultSetAll(FactoryDAO.select(ChocoStock.read()));
	}
	
	public ChocoStock getChocoStock(int id) {
		return ChocoStock.fromResultSet(FactoryDAO.select(ChocoStock.read(id)));
	}
	
	public static ChocoStock getChocoStockStatic(int id) {
		return ChocoStock.fromResultSet(FactoryDAO.select(ChocoStock.read(id)));
	}
	
	public static boolean createChocoStock(int id, int price) {
		return FactoryDAO.insert(Request.create(id, price));
	}
	
	public boolean addChocoStock(int chocoid, int addAmount) {
		return this.updateChocoStock(chocoid, this.getChocoStock(chocoid).amount + addAmount);
	}
	
	public static boolean addChocoStockStatic(int chocoid, int addAmount) {
		return ChocoStockSOAP.updateChocoStockStatic(chocoid, ChocoStockSOAP.getChocoStockStatic(chocoid).amount + addAmount);
	}
	
	public boolean updateChocoStock(int chocoid, int amount) {
		return FactoryDAO.updateUtil(ChocoStock.update(chocoid, amount)) == 1;
	}
	
	public static boolean updateChocoStockStatic(int chocoid, int amount) {
		return FactoryDAO.updateUtil(ChocoStock.update(chocoid, amount)) == 1;
	}

}
