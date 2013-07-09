package com.blve.jsf.data;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.blve.hibernate.functions.HibernateUtil;
import com.blve.hibernate.pojo.Application;


public class LazyApplicationDataModel extends LazyDataModel<Application> 
{
	private static final long serialVersionUID = 1L;
	private List<Application> datasource;
    
    public LazyApplicationDataModel(List<Application> datasource) 
    {
        this.datasource = datasource;
    }
    
    @Override
    public Application getRowData(String rowKey) 
    {
        for(Application application : datasource) 
        {
            if(application.getApplicationID().equals(rowKey))
            {
                return application;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Application application) 
    {
        return application.getApplicationID();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Application> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) 
    {
       
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Object obj = session.createCriteria(Application.class).setProjection(Projections.rowCount()).uniqueResult();
		int rowCount = obj == null ? 0 : Integer.parseInt(obj.toString());
		
		setPageSize(pageSize);
		setRowCount(rowCount);
		
		Criteria criteria = session.createCriteria(Application.class);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		datasource = criteria.list();
		transaction.commit();
		session.close();
		
		return datasource;
    }
}
                    
