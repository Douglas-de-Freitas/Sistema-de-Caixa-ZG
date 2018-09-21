package com.zg.sistemadecaixa.model;

public class Item {

	private Produto produto;
	private int quantidade;

	public Item() {
		this.produto = null;
		this.quantidade = 0;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
