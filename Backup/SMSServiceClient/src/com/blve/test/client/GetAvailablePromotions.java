package com.blve.test.client;

import gt.tigo.smsservice.Promotion;

 
import java.util.ArrayList;

import javax.xml.soap.SOAPException;


import org.thirdparty.Base64Exception;

import com.blve.client.SMSService;

public class GetAvailablePromotions {

	public GetAvailablePromotions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Base64Exception 
	 */
	public static void main(String[] args) throws SOAPException, Base64Exception {
		// TODO Auto-generated method stub
    	String username 	= "BLVE";
    	String password 	= "S_m67<h@t";
    	String endpoint 	= "https://wap.tigo.com.gt/wstest/Services?wsdl";
    	String phonenumber 	= "53098097";

    	
    	SMSService smsService = new SMSService(endpoint, username, password);
    	ArrayList<Promotion> promotions = smsService.GetAvailablePromotions(phonenumber);
    	
    	int max = promotions.size();
    	for(int i = 0; i < max; i++)
    	{
    		System.out.println(promotions.get(i).getDescription());
    	}
    	
	}

}
