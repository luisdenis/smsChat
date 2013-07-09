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
import com.blve.hibernate.pojo.ParameterType;
import com.blve.jsf.data.LazyParameterTypeDataModel;


@ManagedBean(name="ParameterTypeControllerBean")
@SessionScoped
public class ParameterTypeController  {
	
	private LazyDataModel<ParameterType> lazyModel;
    private ParameterType selectedParameterType;
    private ParameterType[] selectedParameterTypes;
    private ParameterType newParameterType;
    private List<ParameterType> parameterTypes;

	public  ParameterTypeController() 
	{
		// TODO Auto-generated constructor stub
		parameterTypes = new ArrayList<ParameterType>();
        lazyModel = new LazyParameterTypeDataModel(parameterTypes);
	}

	public ParameterType getNewParameterType() 
	{
		if(newParameterType == null)
		{
			newParameterType = new ParameterType();
			newParameterType.setParameterTypeID(0);
		}
		
		return newParameterType;
	}
	
	public void setNewParameterType(ParameterType newParameterType) 
	{
		this.newParameterType = newParameterType;
	}
	
    public ParameterType getSelectedParameterType() {
		return selectedParameterType;
	}

	public void setSelectedParameterType(ParameterType selectedParameterType) 
	{
		this.selectedParameterType = selectedParameterType;
	}
	
	public ParameterType[] getSelectedParameterTypes() 
	{
		return selectedParameterTypes;
	}
	
	public void setSelectedParameterTypes(ParameterType[] selectedParameterTypes) 
	{
		this.selectedParameterTypes = selectedParameterTypes;
	}
	
	public LazyDataModel<ParameterType> getLazyModel() 
	{
		return lazyModel;
	}
	

    public void doCreate() 
    {
    	if(newParameterType != null)
    	{
       		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
	 		
    		try 
    		{
    			session.save(newParameterType);
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
    	if(selectedParameterType != null)
    	{ 
    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
    		
    		try 
    		{
    			session.update(selectedParameterType);
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
    	if(selectedParameterTypes != null && selectedParameterTypes.length > 0)
    	{
    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction;
    		ParameterType parameterType;
    		
    		int max = selectedParameterTypes.length -1;
    		for(int i = max; i >= 0 ; i--)
    		{
    			parameterType = selectedParameterTypes[i];
    			
    			transaction = session.beginTransaction();
    			try 
    	    	{
    				session.delete(parameterType);
    				transaction.commit();
    	    	}
    			catch(ConstraintViolationException e)
    			{
    				transaction.rollback();
    				FacesContext.getCurrentInstance().addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_WARN,
    						"Delete Fails!",
    						parameterType.getParameterType() + " not deleted!"
    					)
    				);

    			}
    		}
    		session.close();

    	}     
    }
}
                    