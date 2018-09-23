package com.zg.sistemadecaixa.model;

public class Produto {

	private int identificador;
	private String descricao;
	private Double valor;
	private int promocao;

	public Produto() {
		this.identificador = 0;
		this.descricao = null;
		this.valor = 0.0;
		this.promocao = 0;

	}

	public Produto(int identificador) {
		this.identificador = identificador;
		this.descricao = null;
		this.valor = 0.0;
		this.promocao = 0;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getPromocao() {
		return promocao;
	}

	public void setPromocao(int promocao) {
		this.promocao = promocao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identificador;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (identificador != other.identificador)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [identificador=" + identificador + ", descricao=" + descricao + ", valor=" + valor
				+ ", promocao=" + promocao + "]";
	}

}
