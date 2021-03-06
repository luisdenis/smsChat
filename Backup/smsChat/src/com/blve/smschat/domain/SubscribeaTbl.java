package com.blve.smschat.domain;

// Generated 23-jun-2013 14:08:36 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

/**
 * SubscribeaTbl generated by hbm2java
 */
@Entity
@Table(name = "SUBSCRIBEA_TBL", schema = "BLVE")
public class SubscribeaTbl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2397846106217478805L;
	
	private Integer subscribeaid;
	private String alias;
	private String estado;
	private String msisdn;
	private Date fechacobro;
	private Date fechaultimaactividad;
	private List<SubscribebTbl> subscribebTbls = new ArrayList<SubscribebTbl>(0);

	public SubscribeaTbl() {
	}

	public SubscribeaTbl(Integer subscribeaid, String msisdn) {
		this.subscribeaid = subscribeaid;
		this.msisdn = msisdn;
	}

	public SubscribeaTbl(Integer subscribeaid, String alias, String estado,
			String msisdn, Date fechacobro,
			Date fechaultimaactividad, List<SubscribebTbl> subscribebTbls) {
		this.subscribeaid = subscribeaid;
		this.alias = alias;
		this.estado = estado;
		this.msisdn = msisdn;
		this.fechacobro = fechacobro;
		this.fechaultimaactividad = fechaultimaactividad;
		this.subscribebTbls = subscribebTbls;
	}

	@Id
	@Column(name = "SUBSCRIBEAID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="SubscritorSeqId",sequenceName="SEQ_SUBSCRIBEAID_TBL", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SubscritorSeqId")
	public Integer getSubscribeaid() {
		return this.subscribeaid;
	}

	public void setSubscribeaid(Integer subscribeaid) {
		this.subscribeaid = subscribeaid;
	}

	@Column(name = "ALIAS", length = 20)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "ESTADO", length = 15)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "MSISDN", nullable = false, length = 9)
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHACOBRO")
	public Date getFechacobro() {
		return this.fechacobro;
	}

	public void setFechacobro(Date fechacobro) {
		this.fechacobro = fechacobro;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHAULTIMAACTIVIDAD")
	public Date getFechaultimaactividad() {
		return this.fechaultimaactividad;
	}

	public void setFechaultimaactividad(Date fechaultimaactividad) {
		this.fechaultimaactividad = fechaultimaactividad;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subscribeaTbl")
	public List<SubscribebTbl> getSubscribebTbls() {
		return this.subscribebTbls;
	}

	public void setSubscribebTbls(List<SubscribebTbl> subscribebTbls) {
		this.subscribebTbls = subscribebTbls;
	}

}
