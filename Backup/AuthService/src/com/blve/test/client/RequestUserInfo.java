package com.blve.test.client;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import com.blve.client.AuthService;



public class RequestUserInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String endpoint = "http://gtsmschat.tigocloud.net/AuthService/AuthService?wsdl";
		String ldapId = "200";
		String user = "blvegrp_ralonso";
		String password = "Tigo123ra";
		
		AuthService authService = new AuthService();
		try {
			String errorMessage = authService.RequestUserInfo(endpoint, ldapId, user, password);
			System.out.println("errorMessage = " + errorMessage);
		} catch (SOAPFaultException e) {
			System.out.println("***** SOAPFaultException *****");
			e.printStackTrace();
			
		} catch (SOAPException e) {
			System.out.println("***** SOAPException *****");
			e.printStackTrace();
		}
	}

}
