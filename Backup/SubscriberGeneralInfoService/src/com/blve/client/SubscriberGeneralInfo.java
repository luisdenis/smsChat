package com.blve.client;

import java.io.IOException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SubscriberGeneralInfo {
	public static boolean DEBUG = true;
	
	public String GetSubscriberGeneralInfo(String endpoint, String msisdn) throws SOAPFaultException, SOAPException
	{

		SOAPMessage soapMessage= MessageFactory.newInstance().createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPFactory factory = SOAPFactory.newInstance();
		
		SOAPElement element = factory.createElement("string", "ns1", "http://services.tigo.com.gt/GetSubscriberGeneralInfo");
		element.addTextNode(msisdn);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(element);
		
		if(DEBUG)
		{
			try {
				System.out.println("***** INICIO REQUEST *****");
				soapMessage.writeTo(System.out);
				System.out.println("\n***** FIN REQUEST *****");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
		SOAPConnection conn = scf.createConnection();
	
		SOAPMessage response = conn.call(soapMessage, endpoint);
		conn.close();

		if(DEBUG)
		{
			try {
				System.out.println("***** INICIO RESPONSE *****");
				response.writeTo(System.out);
				System.out.println("***** FIN RESPONSE *****");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Node node;
		NodeList nodes = response.getSOAPBody().getFirstChild().getChildNodes();
		
		
		String customerType = "NA";
		int max = nodes.getLength();
		for(int i = 0; i < max; i++ )
		{
			node = nodes.item(i);
			if(node.getLocalName().equalsIgnoreCase("CustomerType"))
			{
				customerType = node.getFirstChild().getNodeValue();
				break;
			}
		}
		
		
		return customerType;

	}

}
