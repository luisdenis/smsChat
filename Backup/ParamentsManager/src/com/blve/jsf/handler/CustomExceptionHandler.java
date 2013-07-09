package com.blve.jsf.handler;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sun.faces.mgbean.ManagedBeanCreationException;

public class CustomExceptionHandler extends ExceptionHandlerWrapper 
{
    private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());
    private ExceptionHandler wrapped;
	 
    CustomExceptionHandler(ExceptionHandler exception) 
    {
        this.wrapped = exception;
    }
	 
    @Override
    public ExceptionHandler getWrapped() 
    {
        return wrapped;
    }
	 
    @Override
    public void handle() throws FacesException 
    {
 
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) 
        {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
 
            Throwable t = context.getException();
 
            final FacesContext fc = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
            final NavigationHandler nav = fc.getApplication().getNavigationHandler();
 
            try 
            {
                log.log(Level.WARN, "Critical Exception!", t);
                requestMap.put("exceptionMessage", t.getMessage());
            	
          		if (t != null && t.getCause() != null &&  t.getCause().getCause() != null 
                		&& (t.getCause().getCause().getCause() instanceof ViewExpiredException ||
                		    t.getCause() instanceof ManagedBeanCreationException)) 
            		{
          			 nav.handleNavigation(fc, null, "/faces/login.xhtml");
            		} 
            		else 
            		{
            			 nav.handleNavigation(fc, null, "/faces/error.xhtml");
            		}

                fc.renderResponse();

 
            } 
            finally 
            {
                i.remove();
            }
        }
        //parent hanle
        getWrapped().handle();
    }
}

