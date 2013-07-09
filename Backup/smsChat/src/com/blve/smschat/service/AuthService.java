package com.blve.smschat.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class AuthService {
	public static boolean DEBUG = true;
	static Logger log = Logger.getLogger(AuthService.class.getName());
	
	public String RequestUserInfo(String endpoint, String ldapId, String user, String password) throws SOAPFaultException, SOAPException
	{
		
		String uriWSA = "http://www.w3.org/2005/08/addressing";
		String prefixWSA = "wsa";
		
		SOAPMessage soapMessage= MessageFactory.newInstance().createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPFactory factory = SOAPFactory.newInstance();
		
		soapEnvelope.setAttribute("xmlns:" + prefixWSA, uriWSA);
		
		//Header
        SOAPElement elementTO = factory.createElement("TO", prefixWSA, uriWSA);
        elementTO.addTextNode(endpoint);
        
        SOAPElement elementMessageID = factory.createElement("MessageID", prefixWSA, uriWSA);
        elementMessageID.addTextNode(UUID.randomUUID().toString());
        
        SOAPElement elementAction = factory.createElement("Action", prefixWSA, uriWSA);
        elementAction.addTextNode("http://authservices.ws.gt.tigo.com/AuthService/requestUserInfo");
        
        SOAPHeader soapHeader = soapEnvelope.getHeader();
		soapHeader.addChildElement(elementTO);
		soapHeader.addChildElement(elementMessageID);
		soapHeader.addChildElement(elementAction);

		//Body
		SOAPElement elementRequestUserInfo = factory.createElement("requestUserInfo", "ns1", "http://authservices.ws.gt.tigo.com/");
		
		SOAPElement elementldapId = factory.createElement(new QName("ldapId"));
		elementldapId.addTextNode(ldapId);
		
		SOAPElement elementUser = factory.createElement(new QName("user"));
		elementUser.addTextNode(user);
		
		SOAPElement elementPassword = factory.createElement(new QName("password"));
		elementPassword.addTextNode(password);	
		
		elementRequestUserInfo.addChildElement(elementldapId);
		elementRequestUserInfo.addChildElement(elementUser);
		elementRequestUserInfo.addChildElement(elementPassword);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(elementRequestUserInfo);
		
		if(DEBUG)
		{
			try {
				System.out.println("***** INICIO REQUEST *****");
				  //soapMessage.writeTo(System.out);
				  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				  soapMessage.writeTo(outputStream);
				  String s = new String(outputStream.toByteArray());
				  log.info(s);  
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
		NodeList nodes = response.getSOAPBody().getFirstChild().getFirstChild().getChildNodes();
		
		
		String errorMessage = "NA";
		int max = nodes.getLength();
		for(int i = 0; i < max; i++ )
		{
			node = nodes.item(i);
			if(node.getLocalName().equalsIgnoreCase("errorMessage"))
			{
				errorMessage = node.getFirstChild().getNodeValue();
				break;
			}
		}
		
		
		return errorMessage;
		

	}

}
