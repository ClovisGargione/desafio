package com.xpto.dto;

public class DadosColuna {
	
	private String coluna;
	
	private Long quantidadeDeRegistros;

	public DadosColuna() {
	}

	public DadosColuna(String coluna, Long quantidadeDeRegistros) {
		super();
		this.coluna = coluna;
		this.quantidadeDeRegistros = quantidadeDeRegistros;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public Long getQuantidadeDeRegistros() {
		return quantidadeDeRegistros;
	}

	public void setQuantidadeDeRegistros(Long quantidadeDeRegistros) {
		this.quantidadeDeRegistros = quantidadeDeRegistros;
	}
	

}
