package com.blve.test.client;

import javax.xml.soap.SOAPException;
import org.thirdparty.Base64Exception;
import com.blve.client.SMSService;

public class SendSMSNotification {

	public SendSMSNotification() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Base64Exception 
	 */
	public static void main(String[] args) throws SOAPException, Base64Exception {
		// TODO Auto-generated method stub
    	String username = "BLVE";
    	String password = "S_m67<h@t";
    	String endpoint = "https://wap.tigo.com.gt/wstest/Services?wsdl";
    	String phonenumber = "53759792";
    	String shortnumber = "1234";
    	String message	   = "prueba";
    	
    	
    	SMSService smsService = new SMSService(endpoint, username, password);
    	int response = smsService.SendSMSNotification(phonenumber, shortnumber, message);
    	System.out.println("Response = " + response);
	}

}
