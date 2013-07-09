package com.blve.smschat.business;


import java.util.Hashtable;


import org.apache.log4j.Logger;

import com.blve.smschat.business.interfaces.AdminManagerInterface;
import com.blve.smschat.controller.SearchController;
import com.blve.smschat.domain.OperationResult;
import com.blve.smschat.domain.Search;
import com.blve.smschat.domain.SubscribeaTbl;
import com.blve.smschat.persistence.api.SuscribeDAO;
import com.pranical.exceptions.LogicException;
import com.blve.persistence.ResultList;
import com.blve.persistence.exceptions.PersistenceException;

public class SubscribeManager implements AdminManagerInterface<SubscribeaTbl> {

	private SuscribeDAO suscribeDao;
	
	private static Logger logger = Logger.getLogger(SearchController.class);
	
	@Override
	public ResultList<SubscribeaTbl> getResultList(Search s)
			throws LogicException {
		// TODO Auto-generated method stub
		ResultList<SubscribeaTbl> subscribeaTblList;
		
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.getTimeZone();
//		Date d=calendar.getTime();
		
		/*
		SubscribeaTbl SubscribeaTbl = new SubscribeaTbl();
		int num = (int)(Math.round(Math.random() * (99999999-1))) + 1;
		SubscribeaTbl.setMsisdn(""+num);
		SubscribeaTbl.setEstado("ACTIVO");
		SubscribeaTbl.setFechacobro(d);
		SubscribeaTbl.setFechaultimaactividad(d);
		SubscribeaTbl.setAlias("Luis");
		
		try {
			suscribeDao.add(SubscribeaTbl);
			
		} catch (PersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 *
		 */
		try{
			
			subscribeaTblList =  suscribeDao.find(s, ((s.getPage()-1)*s.getNumRows()));
		
		}catch(PersistenceException e){
			logger.error("Ocurrio un error al intentar realizar la busqueda de las cobranzas", e);
			throw new LogicException(e.getMessage(), e);
		}catch(Exception e){
			logger.error("Ocurrio un error al intentar realizar la busqueda de las cobranzas", e);
			throw new LogicException(e.getMessage(), e);
		}
			
		
		return subscribeaTblList;
	}

	@Override
	public SubscribeaTbl getElement(String id) throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Hashtable<String, Object> loadLists() throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationResult add(SubscribeaTbl e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationResult update(SubscribeaTbl e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationResult delete(String id) {
		return null;
	}

	@Override
	public SubscribeaTbl getNewInstance() throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public SuscribeDAO getSuscribeDao() {
		return suscribeDao;
	}

	public void setSuscribeDao(SuscribeDAO suscribeDao) {
		this.suscribeDao = suscribeDao;
	}

	@Override
	public OperationResult deleteNumberA(String id) {
		// TODO Auto-generated method stub
		OperationResult operationResult = new OperationResult();
		int result; 
		
		
		try {
			result = suscribeDao.deleteNumberA(id);
			operationResult.setResultCode(""+result);	
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			operationResult.setResultCode("0");	
		}
		
		return operationResult;
	}

	@Override
	public OperationResult deleteNumberB(String id) {
		// TODO Auto-generated method stub
		OperationResult operationResult = new OperationResult();
		int result; 
		
		
		try {
			result = suscribeDao.deleteNumberB(id);
			operationResult.setResultCode(""+result);	
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			operationResult.setResultCode("0");	
		}
		
		return operationResult;
	}


	
	
	

	
	
	
}
