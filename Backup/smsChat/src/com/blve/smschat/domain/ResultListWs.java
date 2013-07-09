package com.blve.smschat.domain;

import java.util.List;

/**
 * Encapsula las listas de los elementos modificados y eliminados 
 * para la sincronizacion de los tablas al movil.
 * Utilizada por el Servicio Web de sincronizacion
 * @author Mary
 */
public class ResultListWs {

		private int range;
		private String maxDate;
		private int numberRows;
		
		private double totalAmount=0.0;
		private int NumberPage;
	
		
		/**
		 * Lista de elementos resultado
		 */
		@SuppressWarnings("unchecked")
		private List updateList = null;
		/**
		 * Lista de elementos resultado
		 */
		@SuppressWarnings("unchecked")
		private List deletelist = null;
		
		public ResultListWs() {
			super();
		}
		/**
		 * @param list
		 * @param totalRows
		 */
		@SuppressWarnings("unchecked")
		public ResultListWs(List list, int totalRows) {
			super();
		
		}

		public String getMaxDate() {
			return maxDate;
		}
		public void setMaxDate(String maxDate) {
			this.maxDate = maxDate;
		}
		@SuppressWarnings("unchecked")
		public List getUpdateList() {
			return updateList;
		}
		@SuppressWarnings("unchecked")
		public void setUpdateList(List updateList, int page ) {
			
			int numberList = updateList.size();
			int pageTotal = numberList/range;
			int pageResto = numberList%range;
			int subMax,subMin;
			if(pageResto > 0){pageTotal++; }
			subMax = page * range;
			subMin = subMax - range;
			if(subMax > numberList ) subMax= numberList;
			if( page <= pageTotal) updateList = updateList.subList(subMin, subMax);
			this.NumberPage =  pageTotal;
			this.updateList = updateList;
		}
		@SuppressWarnings("unchecked")
		public List getDeletelist() {
			return deletelist;
		}
		@SuppressWarnings("unchecked")
		public void setDeletelist(List deletelist) {
			this.deletelist = deletelist;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public int getNumberPage() {
			return NumberPage;
		}
		public void setNumberPage(int numberPage) {
			this.NumberPage = numberPage;
		}
		
		public int getRange() {
			return range;
		}
		public void setRange(int range) {
			this.range = range;
		}
		
		public int getNumberRows() {
			return numberRows;
		}
		public void setNumberRows(int numberRows) {
			this.numberRows = numberRows;
		}
}