package com.chocofactory.webservice.user;

import javax.jws.WebService;

import com.chocofactory.webservice.FactoryDAO;

@WebService(endpointInterface="com.chocofactory.webservice.user.IUserSOAP")
public class UserSOAP implements IUserSOAP {
	public boolean login(String username, String password) {
		return User.fromResultSet(FactoryDAO.select(User.read(username, password))) != null;
	}
}
