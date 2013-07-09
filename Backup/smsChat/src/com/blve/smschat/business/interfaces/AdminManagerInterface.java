package com.blve.smschat.business.interfaces;

import java.util.Hashtable;

import com.blve.smschat.domain.OperationResult;
import com.pranical.exceptions.LogicException;
import com.blve.persistence.ResultList;

/**
 * Interface que define las operaciones relacionadas con las funcionalidades administrativas
 * de la aplicacion: agregar, modificar, borrar y consultar elementos.
 * @author Mary
 */
public interface AdminManagerInterface<E> extends ManagerInterface<E>{

	/**
	 * Retorna una coleccion con las listas requeridas para llenar los combos
	 * en las vistas Agregar y Modificar
	 * @return
	 * @throws LogicException
	 */
	public Hashtable<String, Object> loadLists() throws LogicException;
	/**
	 * Gestiona la agregación de un nuevo elemento.
	 * @param e Nuevo elemento
	 */
	public OperationResult add(E e);
	/**
	 * Gestiona la modificación de un elemento existente.
	 * @param e Elemento a modificar
	 */
	public OperationResult update(E e);
	/**
	 * Gestiona la eliminacion de un elemento en el sistema.
	 * @param id del elementos a eliminar
	 */
	public OperationResult delete(String id);
	/**
	 * Retorna una nueva instancia
	 */
	public E getNewInstance() throws LogicException;


}