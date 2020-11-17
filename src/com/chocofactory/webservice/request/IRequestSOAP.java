package com.chocofactory.webservice.request;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IRequestSOAP {
	public List<Request> getRequestList();
	public Request getRequest(@WebParam(name="id") int id);
	public String getRequestStatus(@WebParam(name="id") int id);
	public boolean isDelivered(@WebParam(name="id") int id);
	public Integer addRequest(@WebParam(name="chocoid") int chocoid, @WebParam(name="amount") int amount);
	public boolean deliverRequest(@WebParam(name="id") int requestid);
}
