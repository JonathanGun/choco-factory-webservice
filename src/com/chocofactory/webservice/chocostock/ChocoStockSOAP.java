package com.chocofactory.webservice.chocostock;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;

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
	
	public static Integer createChocoStock(int id, String name, int price) {
		return FactoryDAO.insert(ChocoStock.create(id, name, price));
	}
	
	public boolean addChocoStock(int chocoid, int addAmount) {
		return this.updateChocoStock(chocoid, this.getChocoStock(chocoid).amount + addAmount);
	}
	
	public static boolean addChocoStockStatic(int chocoid, int addAmount) {
		return ChocoStockSOAP.updateChocoStockStatic(chocoid, ChocoStockSOAP.getChocoStockStatic(chocoid).amount + addAmount);
	}
	
	public boolean updateChocoStock(int chocoid, int amount) {
		return FactoryDAO.insert(ChocoStock.update(chocoid, amount)) >= 0;
	}
	
	public static boolean updateChocoStockStatic(int chocoid, int amount) {
		return FactoryDAO.insert(ChocoStock.update(chocoid, amount)) >= 0;
	}

}
