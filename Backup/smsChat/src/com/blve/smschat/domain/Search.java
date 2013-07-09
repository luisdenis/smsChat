package com.blve.smschat.domain;

import java.io.Serializable;
import java.util.GregorianCalendar;


/**
 * Encapsula los parametros de busqueda utilizados en las consultas de la aplicacion
 * @author mary
 */
public class Search implements Serializable {

	private static final long serialVersionUID = 2759420509814938205L;

	/**
	 * Cantidad de registros a mostrar por pagina
	 */
	private int numRows = 10;
	private int page = 0;
	
	/* Parametros de uso general */
	private String msisdn;
	private String estado;
	
	private int id;
	

	public Search() {
		
	}


	public int getNumRows() {
		return numRows;
	}


	public String getMsisdn() {
		return msisdn;
	}


	public String getEstado() {
		return estado;
	}


	public int getId() {
		return id;
	}


	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}


	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}
	
	
}