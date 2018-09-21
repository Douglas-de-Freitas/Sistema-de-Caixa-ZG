package com.zg.sistemadecaixa.model;

public class Promocao {

	private String identificador;
	private int quantidade;
	private int precoEspecial;
	private int qtdsPagar;

	public Promocao() {
		this.identificador = null;
		this.quantidade = 0;
		this.precoEspecial = 0;
	}

	public Promocao(String identificador, int quantidade, int precoEspecial) {
		this.identificador = identificador;
		this.quantidade = quantidade;
		this.precoEspecial = precoEspecial;
	}

	public Promocao(String identificador) {
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getPrecoEspecial() {
		return precoEspecial;
	}

	public void setPrecoEspecial(int precoEspecial) {
		this.precoEspecial = precoEspecial;
	}
	
	public int getQtdsPagar() {
		return qtdsPagar;
	}

	public void setQtdsPagar(int qtdsPagar) {
		this.qtdsPagar = qtdsPagar;
	}

	@Override
	public String toString() {
		return "[identificador=" + identificador + ", quantidade=" + quantidade + ", precoEspecial=" + precoEspecial
				+ "]";
	};

}
