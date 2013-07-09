package com.blve.jsf.functions;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JsfUtils 
{
	public static HttpSession getSession() 
	{
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	       
	public static HttpServletRequest getRequest() 
	{
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static void redirect(String action)
	{
    	FacesContext context = FacesContext.getCurrentInstance();  
    	HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    	
    	try {
			context.getExternalContext().redirect(request.getContextPath() + "/faces/" + action);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	/*
	      public static String getUserName()
	      {
	        
	      }
	       
	      public static String getUserId()
	      {
	        HttpSession session = getSession();
	        if ( session != null )
	            return (String) session.getAttribute("userid");
	        else
	            return null;
	      }
	}
	*/
	
	private JsfUtils() {
		// TODO Auto-generated constructor stub
	}
}
