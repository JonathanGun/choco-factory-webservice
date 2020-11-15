package com.chocofactory.webservice.user;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserSOAP {
	public boolean login(@WebParam(name="username") String username, @WebParam(name="password") String password);
}
