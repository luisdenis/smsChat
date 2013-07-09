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
import com.blve.hibernate.pojo.ParameterType;


public class LazyParameterTypeDataModel extends LazyDataModel<ParameterType> 
{
	private static final long serialVersionUID = 1L;
	private List<ParameterType> datasource;
    
    public LazyParameterTypeDataModel(List<ParameterType> datasource) 
    {
        this.datasource = datasource;
    }
    
    @Override
    public ParameterType getRowData(String rowKey) 
    {
        for(ParameterType parameterType : datasource) 
        {
            if(parameterType.getParameterTypeID() == Integer.parseInt(rowKey))
            {
                return parameterType;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(ParameterType parameterType) 
    {
        return parameterType.getParameterTypeID();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<ParameterType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) 
    {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Object obj = session.createCriteria(ParameterType.class).setProjection(Projections.rowCount()).uniqueResult();
		int rowCount = obj == null ? 0 : Integer.parseInt(obj.toString());
		
		setPageSize(pageSize);
		setRowCount(rowCount);
		
		Criteria criteria = session.createCriteria(ParameterType.class);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		datasource = criteria.list();
		transaction.commit();
		session.close();
		
	
		return datasource;
		
    }
}
                    
