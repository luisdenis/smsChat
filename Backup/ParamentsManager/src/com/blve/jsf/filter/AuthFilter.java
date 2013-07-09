package com.blve.jsf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
    	try 
    	{
    		HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            
            
            String reqURI = req.getRequestURI();
            
            
            //Session found
            if(ses != null && ses.getAttribute("username") != null)
            {
	            if (reqURI.indexOf("/faces") == -1 || reqURI.indexOf("/login.xhtml") >= 0)
	            {	
	            	res.sendRedirect(req.getContextPath() + "/faces/home.xhtml");    
	            }
	            else
	            {
	            	chain.doFilter(request, response);    
	            }
            }
            else
            {
            	//Session not found
            	if (reqURI.indexOf("/faces") == -1 )
 	            {	
 	            	res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");    
 	            }
            	else if ( reqURI.indexOf("/login.xhtml") >= 0  || reqURI.indexOf("/error.xhtml") >= 0 || reqURI.indexOf("/expired.xhtml") >= 0)
	            {
	            	chain.doFilter(request, response);
	            }
            	else
 	            {	
 	            	res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");    
 	            }
            }
    	}
    	catch(Throwable t) 
    	{
    	}
    } 
 
    @Override
    public void destroy() 
    {
         
    }
}