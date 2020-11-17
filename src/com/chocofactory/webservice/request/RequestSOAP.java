package com.chocofactory.webservice.request;

import java.util.List;
import javax.jws.WebService;

import com.chocofactory.webservice.FactoryDAO;
import com.chocofactory.webservice.balance.BalanceSOAP;
import com.chocofactory.webservice.chocostock.ChocoStock;
import com.chocofactory.webservice.chocostock.ChocoStockSOAP;


@WebService(endpointInterface="com.chocofactory.webservice.request.IRequestSOAP")
public class RequestSOAP implements IRequestSOAP{
	public List<Request> getRequestList() {
		return Request.fromResultSetAll(FactoryDAO.select(Request.read()));
	}
	
	public Request getRequest(int id) {
		return Request.fromResultSet(FactoryDAO.select(Request.read(id)));
	}
	
	public String getRequestStatus(int id) {
		Request request = this.getRequest(id);
		return (request == null)? null: request.status;
	}
	
	public boolean isDelivered(int id) {
		return this.getRequestStatus(id).equals("Delivered");
	}
	
	public Integer addRequest(int chocoid, int amount) {
		Integer ans = FactoryDAO.insert(Request.create(chocoid, amount));
		System.out.println(ans);
		return ans;
	}
	
	public boolean updateRequest(int requestid, String status) {
		return FactoryDAO.updateUtil(Request.update(requestid, status)) >= 0;
	}
	
	public boolean deliverRequest(int requestid) {
		Request rq = Request.fromResultSet(FactoryDAO.select(Request.read(requestid)));
		ChocoStock cs = ChocoStockSOAP.getChocoStockStatic(rq.chocoID);
		if (cs.amount >= rq.amount) {
			if(ChocoStockSOAP.addChocoStockStatic(rq.chocoID, -rq.amount)) {
				if(BalanceSOAP.addBalanceStatic(cs.price * rq.amount)) {
					return this.updateRequest(requestid, "Delivered");
				}
			}
		}
		return false;
	}
}
