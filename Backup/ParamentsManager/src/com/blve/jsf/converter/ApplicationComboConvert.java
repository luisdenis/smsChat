package com.blve.jsf.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.blve.hibernate.functions.HibernateUtil;
import com.blve.hibernate.pojo.Application;

public class ApplicationComboConvert implements Converter
{
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
    	Application application = null;
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	Criteria criteria = session.createCriteria(Application.class);
    	criteria.add(Restrictions.eq("applicationID", value));
    	
    	@SuppressWarnings("unchecked")
		List<Application> list = criteria.list();
    	transaction.commit();
    	session.close();
     	
    	if(list.size() > 0)
    	{
    		
    		application = list.get(0);
    	}
    	
    	return application;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
    	Application application = (Application) value;
    	
    	return String.valueOf(application.getApplicationID());
    }
}
