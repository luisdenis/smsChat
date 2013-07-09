package com.blve.smschat.persistence.implement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import com.blve.smschat.domain.ResultListWs;
import com.blve.smschat.domain.Search;
import com.blve.smschat.domain.SubscribeaTbl;
import com.blve.smschat.domain.SubscribebTbl;
import com.blve.smschat.persistence.api.SuscribeDAO;
import com.blve.persistence.ResultList;
import com.blve.persistence.exceptions.PersistenceException;
import com.blve.persistence.hibernate.GenericHibernateJPADAO;

public class SuscribeDAOImpl < T, ID, S extends Serializable> 
							extends GenericHibernateJPADAO<SubscribeaTbl, Integer, Search>
							implements SuscribeDAO {

	StringBuilder hql;
	
	@Override
	public ResultList<SubscribeaTbl> find(Search search, Integer indice)
			throws PersistenceException {
		// TODO Auto-generated method stub
		
		ResultList<SubscribeaTbl> results = new ResultList<SubscribeaTbl>();
		List<SubscribeaTbl> list = new ArrayList<SubscribeaTbl>();
		Query q = null;
		em = getEntityManager(ORACLESQL);
		
		StringBuilder hqlSelect = new StringBuilder();
		StringBuilder hqlCount = new StringBuilder();
		StringBuilder hqlParameter = new StringBuilder();
		
		
		hqlSelect.append("SELECT DISTINCT (a) FROM SubscribeaTbl as a left outer join  a.subscribebTbls as b  ");
		hqlCount.append("SELECT COUNT(DISTINCT a) FROM SubscribeaTbl as a left outer join a.subscribebTbls as b  ");
			
		
		//INIT PARAMETRO DE BUSQUEDA//
		if(search.getEstado() != null && search.getEstado().trim().length() != 0 ){
			if(hqlParameter.length()!=0) hqlParameter.append(" AND ");
			hqlParameter.append(" a.estado = '"+search.getEstado()+"'");	
		}
		
		if(search.getMsisdn() != null && search.getMsisdn().trim().length() != 0 ){
			if(hqlParameter.length()!=0) hqlParameter.append(" AND ");
			hqlParameter.append(" (a.msisdn = '"+search.getMsisdn()+"' OR  b.msisdn = '"+search.getMsisdn()+"') ");
		}
		/*END*/
		if(hqlParameter.length()>0) hqlParameter.insert(0, " WHERE ");
		
		//QUERY COUNT//
		q = em.createQuery(hqlCount.append(hqlParameter).toString());
		Long c  = (Long)q.getSingleResult();

		//QUERY SELECT//
		q = em.createQuery(hqlSelect.append(hqlParameter).append(" ORDER BY a.subscribeaid ASC").toString());
		q.setFirstResult(indice);  
		q.setMaxResults(search.getNumRows());
		list = q.getResultList();
		//Si la variable msisdn esta activa, se coloca el numero que se busca que primero en la lista!!
		boolean flag = false;
		if(search.getMsisdn() != null && search.getMsisdn().trim().length() != 0 ){
			for (SubscribeaTbl subscribeaTblList : list) {
				SubscribebTbl subscribebTbl2 = null;
				for (SubscribebTbl subscribeBTbl : subscribeaTblList.getSubscribebTbls()) {
					 if(subscribeBTbl.getMsisdn().equals(search.getMsisdn())){
						 subscribebTbl2 = subscribeBTbl;
						 flag = true;
					 }
				}
				if(flag){
					subscribeaTblList.getSubscribebTbls().remove(subscribebTbl2);
					subscribeaTblList.getSubscribebTbls().add(0, subscribebTbl2);
				}
				flag = false;
			}
		}
		
		
		results.setList(list);
		results.setTotalRows(c.intValue());
		
		
		return results;
	}

	@Override
	public List<SubscribeaTbl> findAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscribeaTbl findById(Integer arg0) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteNumberA(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		int result;
	try{
			
			em = getEntityManager(ORACLESQL);
			
			String hql = "UPDATE SubscribeaTbl SET estado = :newStatus WHERE subscribeaid = :theId";
			result = em.createQuery( hql )
				                            .setParameter( "newStatus", "INACTIVO" )
				                            .setParameter( "theId", Integer.parseInt(id) )
				                            .executeUpdate();
			
			hql = "UPDATE SubscribebTbl SET estado = :newStatus WHERE subscribeaTbl.subscribeaid = :theId";
			result = em.createQuery( hql )
				                            .setParameter( "newStatus", "INACTIVO" )
				                            .setParameter( "theId", Integer.parseInt(id) )
				                            .executeUpdate();
			

		}catch(TransactionRequiredException e){
			throw new PersistenceException(e.getMessage(), e);
		}catch(Exception e){
			throw new PersistenceException(e.getMessage(), e);
		}
	
	
	return result;
	}

	@Override
	public int deleteNumberB(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		int result;
	try{
			
			em = getEntityManager(ORACLESQL);
			
			String hql = "UPDATE SubscribebTbl SET estado = :newStatus WHERE subscribebid = :theId";
			result = em.createQuery( hql )
				                            .setParameter( "newStatus", "INACTIVO" )
				                            .setParameter( "theId", Integer.parseInt(id) )
				                            .executeUpdate();
			

		}catch(TransactionRequiredException e){
			throw new PersistenceException(e.getMessage(), e);
		}catch(Exception e){
			throw new PersistenceException(e.getMessage(), e);
		}
	
	
	return result;
	}
	
	

	
	

}
