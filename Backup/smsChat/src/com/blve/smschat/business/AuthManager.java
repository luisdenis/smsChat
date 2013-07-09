package com.blve.smschat.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;


import javax.xml.soap.SOAPException;

import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;


import com.blve.smschat.service.AuthService;



public class AuthManager implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
	        boolean debug   = true;
	    	String endpoint = "";
	    	String ldapId 		= "";
	    	boolean result = false;
	    	String userName = authentication.getName();
	        String password = authentication.getCredentials().toString();
	    	
//	        String endpoint = "http://gtsmschat.tigocloud.net/AuthService/AuthService?wsdl";
//			String ldapId = "200";
//			String user = "blvegrp_ralonso";
//			String password = "Tigo123ra";
	        
			Properties properties = new Properties();
	    	try {
	    		String filename = "authservice.properties";
	    		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
				properties.load(inputStream);
				debug 	= properties.getProperty("debug", "1").equalsIgnoreCase("1");
				ldapId 	= properties.getProperty("ldapId", "200");
				endpoint= properties.getProperty("endpoint");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	
	    	
	    	if(debug) {
	    		result = userName.equalsIgnoreCase("ldenis") && password.equalsIgnoreCase("ldenis");
	    	}else{
	    		 AuthService authService = new AuthService();
	 			try {
	 				String errorMessage = authService.RequestUserInfo(endpoint, ldapId, userName, password);
	 				result = errorMessage.equalsIgnoreCase("success");
	 				System.out.println("errorMessage = " + errorMessage);
	 			} catch (SOAPFaultException e) {
	 				System.out.println("***** SOAPFaultException *****");
	 				e.printStackTrace();
	 				
	 			} catch (SOAPException e) {
	 				System.out.println("***** SOAPException *****");
	 				e.printStackTrace();
	 			}

	    	}
	    	
	    	  if (result){
					GrantedAuthority[] grantedAuths =  new GrantedAuthorityImpl[1];
					GrantedAuthorityImpl role = new GrantedAuthorityImpl("ROLE_USER");
					grantedAuths[0] = role;
					Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials() , grantedAuths);
					return auth;
	          }else {
	        	  return null;
	          }
	}

	@Override
	public boolean supports(Class authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}
