package com.blve.jsf.handler;

import javax.faces.FacesException;
import javax.faces.application.ProjectStage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.sun.faces.mgbean.ManagedBeanCreationException;
import org.apache.log4j.Logger;
import java.util.Iterator;
import java.io.IOException;

public class WebExceptionHandler extends ExceptionHandlerWrapper 
{
	private static Logger logger = Logger.getLogger( WebExceptionHandler.class);
    private ExceptionHandler wrapped;

    public  WebExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
	   public void handle() throws FacesException {
	   	FacesContext fc = FacesContext.getCurrentInstance();
	    	if (fc.isProjectStage(ProjectStage.Development)) 
	    	{
	            getWrapped().handle();
	        } 
	    	else 
	    	{
	            for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) 
	            {
	            	ExceptionQueuedEvent event = i.next();
	            	ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
	            	String redirectPage = null;
	            	Throwable t = context.getException();
	            	try 
	            	{
	            		if (t != null && t.getCause() != null &&  t.getCause().getCause() != null 
                    		&& (t.getCause().getCause().getCause() instanceof ViewExpiredException ||
                    		    t.getCause() instanceof ManagedBeanCreationException)) 
	            		{
	            			redirectPage = "/faces/login.xhtml";
	            		} 
	            		else 
	            		{
	            			redirectPage = "/faces/error.xhtml";
	            		}
	            	} 
	            	finally 
	            	{
	            		i.remove();
	            	}
	            	ExternalContext extContext = fc.getExternalContext();
	            	String url = extContext.encodeActionURL(fc.getApplication().getViewHandler().getActionURL(fc, redirectPage));
	            	try 
	            	{
	            		logger.error(t.getMessage());
	            		extContext.redirect(url);
	            	} 
	            	catch (IOException ioe) 
	            	{
		            	logger.error(ioe.getMessage());
		                throw new FacesException(ioe);
	            	} 
	            	catch (IllegalStateException ioe) 
	            	{
	            		logger.error(ioe.getMessage());
	            		throw new FacesException(ioe);
	            	}
	            	break;
	            }
        }
    }
}