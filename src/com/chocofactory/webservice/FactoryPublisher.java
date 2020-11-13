package com.chocofactory.webservice;

import javax.xml.ws.Endpoint;

public class FactoryPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/", new Factory()); 
	}
}
