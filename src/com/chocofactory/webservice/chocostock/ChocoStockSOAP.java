package com.chocofactory.webservice.chocostock;

import java.util.List;
import javax.jws.WebService;
import com.chocofactory.webservice.FactoryDAO;

@WebService(endpointInterface="com.chocofactory.webservice.chocostock.IChocoStockSOAP")
public class ChocoStockSOAP  implements IChocoStockSOAP {

	public List<ChocoStock> getChocoStockList() {
		return ChocoStock.fromResultSetAll(FactoryDAO.select(ChocoStock.read()));
	}
	
	public int getChocoStock(int id) {
		ChocoStock cs = ChocoStock.fromResultSet(FactoryDAO.select(ChocoStock.read(id)));
		return cs != null? cs.amount : null;
	}
	
	public static int getChocoStockStatic(int id) {
		ChocoStock cs = ChocoStock.fromResultSet(FactoryDAO.select(ChocoStock.read(id)));
		return cs != null? cs.amount : null;
	}
	
	public boolean addChocoStock(int chocoid, int addAmount) {
		return this.updateChocoStock(chocoid, this.getChocoStock(chocoid) + addAmount);
	}
	
	public static boolean addChocoStockStatic(int chocoid, int addAmount) {
		return ChocoStockSOAP.updateChocoStockStatic(chocoid, ChocoStockSOAP.getChocoStockStatic(chocoid) + addAmount);
	}
	
	public boolean updateChocoStock(int chocoid, int amount) {
		return FactoryDAO.updateUtil(ChocoStock.update(chocoid, amount)) == 1;
	}
	
	public static boolean updateChocoStockStatic(int chocoid, int amount) {
		return FactoryDAO.updateUtil(ChocoStock.update(chocoid, amount)) == 1;
	}

}
