package com.blve.test.client;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import com.blve.client.SubscriberGeneralInfo;

public class GetSubscriberGeneralInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String endpoint = "http://gtsmschat.tigocloud.net/GetSubscriberInfo/GetSubscriberGeneralInfo?wsdl";
		String msisdn = "30992312";
		
		SubscriberGeneralInfo subscriberGeneralInfo = new SubscriberGeneralInfo();
		try {
			String customerType = subscriberGeneralInfo.GetSubscriberGeneralInfo(endpoint, msisdn);
			System.out.println("CustomerType = " + customerType);
		} catch (SOAPFaultException e) {
			System.out.println("***** SOAPFaultException *****");
			e.printStackTrace();
			
		} catch (SOAPException e) {
			System.out.println("***** SOAPException *****");
			e.printStackTrace();
		}
	}

}
