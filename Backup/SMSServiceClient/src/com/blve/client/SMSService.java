package com.blve.client;

import gt.tigo.smsservice.Promotion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

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

import org.thirdparty.Base64;
import org.thirdparty.Base64Exception;
import org.w3c.dom.NodeList;



public class SMSService {
	public static boolean DEBUG = true;
	
	private String endpoint;
	private String username;
	private String password;
	private static Random random;

	private SOAPMessage soapMessage;
	private SOAPPart soapPart;
	private SOAPEnvelope soapEnvelope;
	private SOAPFactory factory;

	static 
	{
		try 
		{
			random = SecureRandom.getInstance("SHA1PRNG");
		} 
		catch (NoSuchAlgorithmException nsae) 
		{
			nsae.printStackTrace();
		}
	}
	
	public int SendSMSNotification(String phonenumber, String shortnumber, String message) throws Base64Exception, SOAPException
	{
		String prefixSms = "sms";
		String uriSms = "https://wap.tigo.com.gt/ws/SMSService";
		
		soapEnvelope.setAttribute("xmlns:" + prefixSms, uriSms);

		addHeaderUsernameToken();
		
		SOAPElement ephonenumber = factory.createElement("phonenumber", prefixSms, uriSms);
		ephonenumber.addTextNode(phonenumber);
		
		SOAPElement eshortnumber = factory.createElement("shortnumber", prefixSms, uriSms);
		eshortnumber.addTextNode(shortnumber);
		
		SOAPElement emessage = factory.createElement("message", prefixSms, uriSms);
		emessage.addTextNode(message);


		SOAPElement requestParams = factory.createElement("SendSMSNotificationRequestParms", prefixSms, uriSms);
		requestParams.addChildElement(ephonenumber);
		requestParams.addChildElement(eshortnumber);
		requestParams.addChildElement(emessage);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(requestParams);
		
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
		
		int vasResponse = -1;
		NodeList lists = response.getSOAPBody().getElementsByTagNameNS(uriSms, "SendSMSNotificationResponseParms");
		
		if(lists.getLength() > 0)
		{
			NodeList nodeResponse = lists.item(0).getChildNodes();
			String value = nodeResponse.item(0).getFirstChild().getNodeValue();
			 vasResponse = value == null ?  vasResponse : Integer.parseInt(value);
				
		}
		
		return  vasResponse;
	}
	
	public int UnSubscribeToService(String phonenumber, String shortnumber, String subscriptionid) throws Base64Exception, SOAPException
	{
		String prefixSms = "sms";
		String uriSms = "https://wap.tigo.com.gt/ws/SMSService";
		
		soapEnvelope.setAttribute("xmlns:" + prefixSms, uriSms);

		addHeaderUsernameToken();
		
		SOAPElement ephonenumber = factory.createElement("phonenumber", prefixSms, uriSms);
		ephonenumber.addTextNode(phonenumber);
		
		SOAPElement eshortnumber = factory.createElement("shortnumber", prefixSms, uriSms);
		eshortnumber.addTextNode(shortnumber);
		
		SOAPElement esubscriptionid = factory.createElement("subscriptionid", prefixSms, uriSms);
		esubscriptionid.addTextNode(subscriptionid);


		SOAPElement requestParams = factory.createElement("UnSubscribeToServiceRequestParms", prefixSms, uriSms);
		requestParams.addChildElement(ephonenumber);
		requestParams.addChildElement(eshortnumber);
		requestParams.addChildElement(esubscriptionid);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(requestParams);
		
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
		
		int vasResponse = -1;
		NodeList lists = response.getSOAPBody().getElementsByTagNameNS(uriSms, "UnSubscribeToServiceResponseParms");
		
		if(lists.getLength() > 0)
		{
			NodeList nodeResponse = lists.item(0).getChildNodes();
			String value = nodeResponse.item(0).getFirstChild().getNodeValue();
			 vasResponse = value == null ?  vasResponse : Integer.parseInt(value);
				
		}
		
		return  vasResponse;
	}
	
	public int Accreditation(String phonenumberA, String phonenumberB, String code)  throws Base64Exception, SOAPException
	{
		String prefixSms = "sms";
		String uriSms = "https://wap.tigo.com.gt/ws/SMSService";
		String prefixSms1 = "sms1";
		String uriSms1 = "https://wap.tigo.com.gt/ws/SMSServiceDataObjects";
		
		soapEnvelope.setAttribute("xmlns:" + prefixSms, uriSms);
		soapEnvelope.setAttribute("xmlns:" + prefixSms1, uriSms1);

		addHeaderUsernameToken();
		
		SOAPElement eparameterID1 = factory.createElement("parameterid", prefixSms1, uriSms1);
		eparameterID1.addTextNode("1");
		
		SOAPElement eparameterName1 = factory.createElement("parametername", prefixSms1, uriSms1);
		eparameterName1.addTextNode("phoneNumberB");
		
		SOAPElement eparameterValue1 = factory.createElement("parametervalue", prefixSms1, uriSms1);
		eparameterValue1.addTextNode(phonenumberB);
		
		SOAPElement eparameterType1 = factory.createElement("parametertype", prefixSms1, uriSms1);
		eparameterType1.addTextNode("string");
		
		SOAPElement parameter1 = factory.createElement("Parameter", prefixSms1, uriSms1);
		parameter1.addChildElement(eparameterID1);
		parameter1.addChildElement(eparameterName1);
		parameter1.addChildElement(eparameterValue1);
		parameter1.addChildElement(eparameterType1);
		
		SOAPElement earrayofparameters = factory.createElement("arrayofparameters", prefixSms, uriSms);
		earrayofparameters.addChildElement(parameter1);
		
		
		SOAPElement ephonenumber = factory.createElement("phonenumber", prefixSms, uriSms);
		ephonenumber.addTextNode(phonenumberA);
		
		SOAPElement eaccreditationcode = factory.createElement("accreditationcode", prefixSms, uriSms);
		eaccreditationcode.addTextNode(code);

		SOAPElement erequestParams = factory.createElement("AccreditationRequestParms", prefixSms, uriSms);
		erequestParams.addChildElement(ephonenumber);
		erequestParams.addChildElement(eaccreditationcode);
		erequestParams.addChildElement(earrayofparameters);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(erequestParams);
		
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
		
		int vasResponse = -1;
		NodeList lists = response.getSOAPBody().getElementsByTagNameNS(uriSms, "AccreditationResponseParms");
		
		if(lists.getLength() > 0)
		{
			NodeList nodeResponse = lists.item(0).getChildNodes();
			String value = nodeResponse.item(0).getFirstChild().getNodeValue();
			 vasResponse = value == null ?  vasResponse : Integer.parseInt(value);
				
		}
		
		return  vasResponse;

	}
	
	public ArrayList<Promotion> GetAvailablePromotions(String phonenumber) throws Base64Exception, SOAPException
	{
		addHeaderUsernameToken();
		
		String prefixSms = "sms";
		String uriSms = "https://wap.tigo.com.gt/ws/SMSService";
		
		soapEnvelope.setAttribute("xmlns:" + prefixSms, uriSms);
		
		
    	SOAPElement ephoneNumber = factory.createElement("phonenumber", prefixSms, uriSms);
		ephoneNumber.addTextNode(phonenumber);

		SOAPElement erequestParams = factory.createElement("GetAvailablePromotionsRequestParms", prefixSms, uriSms);
		erequestParams.addChildElement(ephoneNumber);
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addChildElement(erequestParams);
	
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
		
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		
		
		NodeList lists = response.getSOAPBody().getElementsByTagName("Promotion");
		NodeList node;
		
		String promotionId;
		String name;
		String subscribeCode;
		String subscribeShortnumber;
		float promotionPrice;
		String description;
		
		int max = lists.getLength();
		
		for(int i = 0; i < max; i++)
		{
			node = lists.item(i).getChildNodes();
			
			promotionId 			= node.item(0).getFirstChild().getNodeValue().trim();
			name 					= node.item(1).getFirstChild().getNodeValue().trim();
			subscribeCode 			= node.item(2).getFirstChild().getNodeValue().trim();
			subscribeShortnumber 	= node.item(3).getFirstChild().getNodeValue().trim();
			promotionPrice 			= Float.parseFloat(node.item(4).getFirstChild().getNodeValue().trim());
			description 			= node.item(5).getFirstChild().getNodeValue().trim();
			
			promotions.add(new Promotion(promotionId, name, subscribeCode, subscribeShortnumber, promotionPrice, description));
		}
		
		
		
		return promotions;
		
	}
	
	private void addHeaderUsernameToken() throws Base64Exception, SOAPException
	{
		String created 	= generateCreated();
		String nonce 	= generateNonce();
		String digest	= generatePasswordDigest(nonce, created);
		
		
        String prefixWsse = "wsse";
        String uriWsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        
        String prefixWsu = "wsu";
        String uriWsu = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
		
        SOAPElement securityElem =
                factory.createElement("Security", prefixWsse, uriWsse);
        SOAPElement tokenElem =
                factory.createElement("UsernameToken", prefixWsse, uriWsse);
        tokenElem.addAttribute(QName.valueOf("wsu:Id"), "UsernameToken-2");
        tokenElem.addAttribute(QName.valueOf("xmlns:" + prefixWsu), uriWsu);
       
        SOAPElement usernameElem =
                factory.createElement("Username", prefixWsse, uriWsse);
        usernameElem.addTextNode(username);
        
        SOAPElement passwordElem =
                factory.createElement("Password", prefixWsse, uriWsse);
        passwordElem.addTextNode(digest);
        passwordElem.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
        
        SOAPElement nonceElem =
                factory.createElement("Nonce", prefixWsse, uriWsse);
        nonceElem.addTextNode(nonce);
        nonceElem.addAttribute(QName.valueOf("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        
        SOAPElement createdElem =
                factory.createElement("Created", prefixWsu, uriWsu);
        createdElem.addTextNode(created);
        
        tokenElem.addChildElement(usernameElem);
        tokenElem.addChildElement(passwordElem);
        tokenElem.addChildElement(nonceElem);
        tokenElem.addChildElement(createdElem);
        
        
        securityElem.addChildElement(tokenElem);
        
        SOAPHeader soapHeader = soapEnvelope.getHeader();
		soapHeader.addChildElement(securityElem);
	}
	
	private String generateNonce()  {
        byte[] nonceValue = new byte[16];
        random.nextBytes(nonceValue);
        
        return Base64.encode(nonceValue);
	}
	
	private String generateCreated() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		f.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTime());
	}
	
	private String generatePasswordDigest(String nonce, String created) throws Base64Exception {
		try {
			byte[] b1 = Base64.decode(nonce);
	        byte[] b2 = created.getBytes("UTF-8");
	
	        byte[] b3 = password.getBytes("UTF-8");
	        byte[] b4 = new byte[b1.length + b2.length + b3.length];
	        int i = 0;
	        int count = 0;
	        for (i = 0; i < b1.length; i++) 
	        {
	             b4[count++] = b1[i];
	        }
	        for (i = 0; i < b2.length; i++) 
	        {
	             b4[count++] = b2[i];
	        }
	        for (i = 0; i < b3.length; i++) 
	        {
	            b4[count++] = b3[i];
	        }
	        MessageDigest sha = MessageDigest.getInstance("SHA-1");
	        sha.reset();
	        sha.update(b4);
	        return Base64.encode(sha.digest());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Base64Exception("UnsupportedEncodingException");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new Base64Exception("NoSuchAlgorithmException");
		}
	}
	
	public SMSService(String endpoint, String username, String password) throws SOAPException {
		// TODO Auto-generated constructor stub
		this.endpoint = endpoint;
		this.username = username;
		this.password = password;
		
		soapMessage = MessageFactory.newInstance().createMessage();
		soapPart = soapMessage.getSOAPPart();
		soapEnvelope = soapPart.getEnvelope();
		factory = SOAPFactory.newInstance();
	}
	

}
