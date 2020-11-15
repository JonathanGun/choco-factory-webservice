package com.chocofactory.webservice.request;

import java.util.List;
import javax.jws.WebService;

import com.chocofactory.webservice.FactoryDAO;


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
	
	public boolean addRequest(int chocoid, int amount) {
		return FactoryDAO.insert(Request.create(chocoid, amount));
	}
	
	public boolean updateRequest(int requestid, String status) {
		return FactoryDAO.updateUtil(Request.update(requestid, status)) >= 0;
	}
	
	public boolean deliverRequest(int requestid) {
		return this.updateRequest(requestid, "Delivered");
	}
}
