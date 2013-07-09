package com.blve.smschat.persistence.api;

import java.util.List;

import com.blve.smschat.domain.ResultListWs;
import com.blve.smschat.domain.Search;
import com.blve.smschat.domain.SubscribeaTbl;

import com.blve.persistence.GenericDAO;
import com.blve.persistence.exceptions.PersistenceException;

public interface SuscribeDAO extends GenericDAO<SubscribeaTbl , Integer, Search> {
	
	/**
	 * Realiza la eliminacion de un servicio, lo cual se traduce en cambiar el 
	 * estatus del mismo a INACTIVO 
	 * @param id del dispositivo
	 * @throws PersistenceException
	 */
	public int deleteNumberA(String id) throws PersistenceException;
	
	
	/**
	 * Realiza la eliminacion de un servicio, lo cual se traduce en cambiar el 
	 * estatus del mismo a INACTIVO 
	 * @param id del dispositivo
	 * @throws PersistenceException
	 */
	public int deleteNumberB(String id) throws PersistenceException;
	
	
}
