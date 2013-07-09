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
import com.blve.hibernate.pojo.Parameter;


public class LazyParameterDataModel extends LazyDataModel<Parameter> 
{
	private static final long serialVersionUID = 1L;
	private List<Parameter> datasource;
    
    public LazyParameterDataModel(List<Parameter> datasource) 
    {
        this.datasource = datasource;
    }
    
    @Override
    public Parameter getRowData(String rowKey) 
    {
        for(Parameter parameter : datasource) 
        {
            if(parameter.getParameterID() == Integer.parseInt(rowKey))
            {
                return parameter;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Parameter parameter) 
    {
        return parameter.getParameterID();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Parameter> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) 
    {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Object obj = session.createCriteria(Parameter.class).setProjection(Projections.rowCount()).uniqueResult();
		int rowCount = obj == null ? 0 : Integer.parseInt(obj.toString());
		
		setPageSize(pageSize);
		setRowCount(rowCount);
		
		Criteria criteria = session.createCriteria(Parameter.class);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		datasource = criteria.list();
		transaction.commit();
		session.close();
		
		return datasource;
		
    }
}
                    
