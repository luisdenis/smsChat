package com.blve.jsf.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import com.blve.jsf.functions.JsfUtils;
import com.tigo.gt.ws.authservices.AuthService;
import com.tigo.gt.ws.authservices.AuthenticationService;
import com.tigo.gt.ws.authservices.UserInformationResponse;


@ManagedBean(name="LoginControllerBean")
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
    public void login() 
    {
    	boolean debug   = true;
    	String endpoint = "";
    	String ldapId 		= "";
    	
    	
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
    	
    	boolean result = false;
    	if(debug)
    	{
    		result = username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin");
    	}
    	else
    	{
    		try
    		{
	    		AuthenticationService authenticationService = new AuthenticationService();
	    		AuthService authService = authenticationService.getAuthServicePort();
	    		BindingProvider bpAuthService = (BindingProvider) authService;
	    		bpAuthService.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
	    		//UserInformationResponse userInformationResponse = authService.requestUserInfo(ldapId, username, password);
	    		String errorMessage = authService.RequestUserInfo(endpoint, ldapId, username, password);
				 // requestUserInfo(ldapId, username, password);
	    		//result = userInformationResponse.getErrorMessage().equalsIgnoreCase("success");
	    		result = errorMessage.equalsIgnoreCase("success");	
				System.out.println("errorMessage = " + errorMessage);
    		}
    		catch(SOAPFaultException e)
    		{
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Service not available!",
                        "Please Try Again!"));
                
                return;
    		}
    		catch(WebServiceException e)
    		{
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Service not available!",
                        "Please Try Again!"));
                
                return;
    		} catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	
        if (result) 
        {
            HttpSession session = JsfUtils.getSession();
            session.setAttribute("username", username);
 
            JsfUtils.redirect("home.xhtml");
            
        } 
        else 
        {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
            
           // WebUtils.redirect("accessDenied.xhtml");
        }
    }
    
 
    public void logout() 
    {
      HttpSession session = JsfUtils.getSession();
      session.invalidate();
      JsfUtils.redirect("login.xhtml");
   }
}
