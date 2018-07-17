package com.xpto.dto;

public class Estados {

	private String nome;
	
	private int quantidadeDeCidades;

	public Estados() {
		super();
	}

	public Estados(String nome, int quantidadeDeCidades) {
		super();
		this.nome = nome;
		this.quantidadeDeCidades = quantidadeDeCidades;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadeDeCidades() {
		return quantidadeDeCidades;
	}

	public void setQuantidadeDeCidades(int quantidadeDeCidades) {
		this.quantidadeDeCidades = quantidadeDeCidades;
	}

}
