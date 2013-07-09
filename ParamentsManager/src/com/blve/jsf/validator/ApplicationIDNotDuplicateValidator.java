package com.blve.jsf.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.blve.hibernate.functions.HibernateUtil;
import com.blve.hibernate.pojo.Application;
 
@FacesValidator("IpValidator")
public class ApplicationIDNotDuplicateValidator implements Validator 
{
 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
    {
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	
    	Criteria criteria = session.createCriteria(Application.class);
    	criteria.add(Restrictions.eq("applicationID", value))
    		.setProjection(Projections.rowCount());
    	
    	Object obj = criteria.uniqueResult();
    	session.close();
    	
    	int num = obj == null ? 0 : Integer.parseInt(obj.toString());
    	if(num > 0)
    	{
    		throw new ValidatorException(new FacesMessage("Application ID Duplicate!"));
    	}
    	
    }
}
