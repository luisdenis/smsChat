package com.blve.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;

import com.blve.hibernate.functions.HibernateUtil;
import com.blve.hibernate.pojo.Application;
import com.blve.jsf.data.LazyApplicationDataModel;


@ManagedBean(name="ApplicationControllerBean")
@SessionScoped
public class ApplicationController  {
	
	private LazyDataModel<Application> lazyModel;
    private Application selectedApplication;
    private Application[] selectedApplications;
    private Application newApplication;
    private List<Application> applications;


	public  ApplicationController() 
	{
		// TODO Auto-generated constructor stub
		applications = new ArrayList<Application>();
        lazyModel = new LazyApplicationDataModel(applications);
	}

	public Application getNewApplication() 
	{
		if(newApplication == null)
		{
			newApplication = new Application();
		}
		
		return newApplication;
	}
	
	public void setNewApplication(Application newApplication) 
	{
		this.newApplication = newApplication;
	}
	
    public Application getSelectedApplication() {
		return selectedApplication;
	}

	public void setSelectedApplication(Application selectedApplication) 
	{
		this.selectedApplication = selectedApplication;
	}
	
	public Application[] getSelectedApplications() 
	{
		return selectedApplications;
	}
	
	public void setSelectedApplications(Application[] selectedApplications) 
	{
		this.selectedApplications = selectedApplications;
	}
	
	public LazyDataModel<Application> getLazyModel() 
	{
		return lazyModel;
	}
	

    public void doCreate() 
    {
    	if(newApplication != null)
    	{
       		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
	 		
    		try 
    		{
    			session.save(newApplication);
	    		transaction.commit();
	    		
    		} 
    		catch (ConstraintViolationException e)
    		{
    			transaction.rollback();

    		}
    		finally
    		{
    			session.close();
    		}
    	}
    }
        

    public void doUpdate()
    {
    	if(selectedApplication != null)
    	{ 
    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
    		
    		try 
    		{
    			session.update(selectedApplication);
	    		transaction.commit();
    		} 
    		catch (HibernateException e)
    		{
    			transaction.rollback();

    		}
    		finally
    		{
    			session.close();
    		}
    	}
    }
        
    public void doDelete()
    {
    	if(selectedApplications != null && selectedApplications.length > 0)
    	{
    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction; 
    		
    		Application application;
    		int max = selectedApplications.length -1;
    		for(int i = max; i >= 0 ; i--)
    		{
    			application = selectedApplications[i];
    			
    			transaction = session.beginTransaction();
    			try 
    	    	{
    				
    				session.delete(application);
    				transaction.commit();
    	    	}
    			catch(HibernateException e)
    			{
    				transaction.rollback();
    				FacesContext.getCurrentInstance().addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_WARN,
    						"Delete Fails!",
    						application.getApplicationID() + " not deleted!"
    					)
    				);

    			}
    		}
    		
    		session.close();

    	}     
    }
}
                    