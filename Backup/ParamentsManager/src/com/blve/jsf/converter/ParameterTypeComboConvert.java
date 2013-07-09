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
import com.blve.hibernate.pojo.ParameterType;

public class ParameterTypeComboConvert  implements Converter
{
	
	
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
    	ParameterType parameterType = null;
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	Criteria criteria = session.createCriteria(ParameterType.class);
    	criteria.add(Restrictions.eq("parameterTypeID", Integer.valueOf(value)));
    	
    	@SuppressWarnings("unchecked")
		List<ParameterType> list = criteria.list();
    	transaction.commit();
    	session.close();
    	
    	if(list.size() > 0)
    	{
    		parameterType = list.get(0);
    	}
    	
    	return parameterType;
    }

	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
        ParameterType parameterType = (ParameterType) value;
        return String.valueOf(parameterType.getParameterTypeID());
    }
}
