/**
 * 
 */
package com.xpto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author clovis
 *
 */
@Entity
public class Cidades {
	
	@Id
	private Long ibgeId;
	
	private String uf;
	
	private String nome;
	
	private boolean capital;
	
	private Double longitude;
	
	private Double latitude;
	
	private String semAcentos;
	
	private String nomesAlternativos;
	
	private String microregiao;
	
	private String mesoregiao;

	/**
	 * 
	 */
	public Cidades() {
		
	}

	

	public Cidades(Long ibgeId, String uf, String nome, boolean capital, Double longitude, Double latitude,
			String semAcentos, String nomesAlternativos, String microregiao, String mesoregiao) {
		super();
		this.ibgeId = ibgeId;
		this.uf = uf;
		this.nome = nome;
		this.capital = capital;
		this.longitude = longitude;
		this.latitude = latitude;
		this.semAcentos = semAcentos;
		this.nomesAlternativos = nomesAlternativos;
		this.microregiao = microregiao;
		this.mesoregiao = mesoregiao;
	}



	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getNomesAlternativos() {
		return nomesAlternativos;
	}

	public void setNomesAlternativos(String nomesAlternativos) {
		this.nomesAlternativos = nomesAlternativos;
	}

	public String getMicroregiao() {
		return microregiao;
	}

	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}

	public String getMesoregiao() {
		return mesoregiao;
	}

	public void setMesoregiao(String mesoregiao) {
		this.mesoregiao = mesoregiao;
	}

	public String getSemAcentos() {
		return semAcentos;
	}

	public void setSemAcentos(String semAcentos) {
		this.semAcentos = semAcentos;
	}
	
}
