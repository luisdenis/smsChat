package com.blve.jsf.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;

import com.blve.hibernate.functions.HibernateUtil;
import com.blve.hibernate.pojo.Application;
import com.blve.hibernate.pojo.Parameter;
import com.blve.hibernate.pojo.ParameterType;
import com.blve.jsf.data.LazyParameterDataModel;


@ManagedBean(name="ParameterControllerBean")
@SessionScoped
public class ParameterController  {
	
	private LazyDataModel<Parameter> lazyModel;
    private Parameter selectedParameter;
    private Parameter[] selectedParameters;
    private Parameter newParameter;
    private List<Parameter> parameters;
    //private List<Application> selectApplications;

	public  ParameterController() 
	{
		// TODO Auto-generated constructor stub
		parameters = new ArrayList<Parameter>();
        lazyModel = new LazyParameterDataModel(parameters);
	}

	public Parameter getNewParameter() 
	{
		if(newParameter == null)
		{
			newParameter = new Parameter();
			newParameter.setParameterID(0);
		}
		
		return newParameter;
	}
	
	public void setNewParameter(Parameter newParameter) 
	{
		this.newParameter = newParameter;
	}
	
    public Parameter getSelectedParameter() {
		return selectedParameter;
	}

	public void setSelectedParameter(Parameter selectedParameter) 
	{
		this.selectedParameter = selectedParameter;
	}
	
	public Parameter[] getSelectedParameters() 
	{
		return selectedParameters;
	}
	
	public void setSelectedParameters(Parameter[] selectedParameters) 
	{
		this.selectedParameters = selectedParameters;
	}
	
	public LazyDataModel<Parameter> getLazyModel() 
	{
		return lazyModel;
	}
	

    public void doCreate() 
    {
    	if(newParameter != null)
    	{
       		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
	 		
    		try 
    		{
    			session.save(newParameter);
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
    	if(selectedParameter != null)
    	{ 
    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction = session.beginTransaction();
    		
    		try 
    		{
    			session.update(selectedParameter);
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
    	if(selectedParameters != null && selectedParameters.length > 0)
    	{

    		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		Session session = sessionFactory.openSession();
    		Transaction transaction; 
    		Parameter parameter;
    		
    		int max = selectedParameters.length -1;
    		for(int i = max; i >= 0 ; i--)
    		{
    			parameter = selectedParameters[i];
    			
    			transaction = session.beginTransaction();
    			try 
    	    	{
    				
    				session.delete(parameter);
    				transaction.commit();
    	    	}
    			catch(HibernateException e)
    			{
    				transaction.rollback();
    				FacesContext.getCurrentInstance().addMessage(
    						null,
    						new FacesMessage(FacesMessage.SEVERITY_WARN,
    							"Delete Fails!",
    							parameter.getParameterName() + " not deleted!"
    						)
    					);

    			}
    		}
    		
    		session.close();

    	}     
    }

    @SuppressWarnings("unchecked")
	public Map<String, Application> getSelectApplications() 
    {
    	Map<String, Application> map = new HashMap<String, Application>();
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	Criteria criteria = session.createCriteria(Application.class);
    	criteria.addOrder(Order.asc("applicationName"));
    	List<Application> applications = (List<Application>)criteria.list();
    	transaction.commit();
    	session.close();
    	
    	for(Application application : applications)
    	{
    		map.put(application.getApplicationName(), application);
    	}
    	
    	return map;
	}
    
    @SuppressWarnings("unchecked")
	public Map<String, ParameterType> getSelectParametersTypes() 
    {
    	Map<String, ParameterType> map = new HashMap<String, ParameterType>();
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	Criteria criteria = session.createCriteria(ParameterType.class);
    	criteria.addOrder(Order.asc("parameterType"));

    	List<ParameterType> parameterTypes = (List<ParameterType>)criteria.list();
    	transaction.commit();
    	session.close();
    	
    	for(ParameterType parameterType : parameterTypes)
    	{
    		map.put(parameterType.getParameterType(), parameterType);
    	}

    	
    	return map;
	}
    

    
}
                    