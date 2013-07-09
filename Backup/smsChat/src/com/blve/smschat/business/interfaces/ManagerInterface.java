package com.blve.smschat.business.interfaces;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.blve.smschat.domain.OperationResult;
import com.blve.smschat.domain.Search;
import com.pranical.exceptions.LogicException;

import com.blve.persistence.ResultList;

/**
 * Interface que define las operaciones basicas del negocio
 * @author Mary
 */
public interface ManagerInterface<E> {

	
	/**
	 * Retorna una lista con los elementos, para la paginacion. 
	 * @param s Objeto que encapsula los parametros de búsqueda
	 * @param page Indice que indica desde donde se realizara la busqueda
	 * @return Objeto que encapsula una lista con el resultado de la consulta, e informacion
	 * asociada a la misma
	 * @throws LogicException
	 */
	public ResultList<E> getResultList(Search s) throws LogicException;

	/**
	 * Retorna un elemento dado su id
	 * @param id Elemento a consultar
	 * @return Objeto del dominio que fue consultado
	 * @throws LogicException
	 */
	public E getElement(String id) throws LogicException;
	
	/**
	 * Modifica el estado de usuario A
	 * @param id del elementos a eliminar
	 */
	public OperationResult deleteNumberA(String id);
	
	/**
	 * Modifica el estado de usuario B
	 * @param id del elementos a eliminar
	 */
	public OperationResult deleteNumberB(String id);
	
	
	
}