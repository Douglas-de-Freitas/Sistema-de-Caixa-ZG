package com.zg.sistemadecaixa.model;

public class Produto {

	private String identificador;
	private Double valor;
	private String descricao;
	private int promocao;

	public Produto() {
		this.valor = 0.0;
		this.identificador = null;
	}

	public Produto(String identificador) {
		this.identificador = identificador;
		this.valor = 0.0;
	}

	public Produto(String identificador, Double valor) {
		this.identificador = identificador;
		this.valor = valor;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
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
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [identificador=" + identificador + ", valor=" + valor + "]";
	}

}
