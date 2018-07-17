package com.xpto.dto;

import com.xpto.entity.Cidades;

public class CidadesMaisDistantes {

	private Cidades primeiraCidade;
	
	private Cidades segundaCidade;
	
	private String distanciaEmKm;
	
	public CidadesMaisDistantes() {
	}

	public CidadesMaisDistantes(Cidades primeiraCidade, Cidades segundaCidade, String distanciaEmKm) {
		super();
		this.primeiraCidade = primeiraCidade;
		this.segundaCidade = segundaCidade;
		this.distanciaEmKm = distanciaEmKm;
	}

	public Cidades getPrimeiraCidade() {
		return primeiraCidade;
	}

	public void setPrimeiraCidade(Cidades primeiraCidade) {
		this.primeiraCidade = primeiraCidade;
	}

	public Cidades getSegundaCidade() {
		return segundaCidade;
	}

	public void setSegundaCidade(Cidades segundaCidade) {
		this.segundaCidade = segundaCidade;
	}

	public String getDistanciaEmKm() {
		return distanciaEmKm;
	}

	public void setDistanciaEmKm(String distancia) {
		this.distanciaEmKm = distancia;
	}
	
}
