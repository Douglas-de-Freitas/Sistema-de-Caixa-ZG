package com.zg.sistemadecaixa.model;

import java.util.List;

import com.zg.sistemadecaixa.control.CalculaDesconto;
import com.zg.sistemadecaixa.dao.PromocaoDao;

public class Promocao implements CalculaDesconto {

	private int id;
	private String descricao;
	private String obs;
	private int qtdeAtivacao;
	private int precoFinal;
	private int qtdPaga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getQtdeAtivacao() {
		return qtdeAtivacao;
	}

	public void setQtdeAtivacao(int qtdeAtivacao) {
		this.qtdeAtivacao = qtdeAtivacao;
	}

	public int getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(int precoFinal) {
		this.precoFinal = precoFinal;
	}

	public int getQtdPaga() {
		return qtdPaga;
	}

	public void setQtdPaga(int qtdPaga) {
		this.qtdPaga = qtdPaga;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Promocao other = (Promocao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Promocao [id=" + id + ", descricao=" + descricao + ", obs=" + obs + ", qtdeAtivacao=" + qtdeAtivacao
				+ ", precoFinal=" + precoFinal + ", qtdPaga=" + qtdPaga + "]";
	}

	@Override
	public Double calculoDeDesconto(List<Item> lista) {

		Double total = 0.0;

		PromocaoDao dao = new PromocaoDao();

		for (int i = 0; i < lista.size(); i++) {

			Promocao promocao = dao.getPromocaoId(lista.get(i).getProduto().getPromocao());
			Double precoProduto = lista.get(i).getProduto().getValor();
			int quantidadeAtivacao = promocao.getQtdeAtivacao();
			int quantidadeDeUmProduto = lista.get(i).getQuantidade();

			if (promocao.getQtdeAtivacao() > 0 && promocao.getPrecoFinal() > 0) {

				Double precoFinal = (double) promocao.getPrecoFinal();

				int divisao = (quantidadeDeUmProduto / quantidadeAtivacao);
				int resto = (quantidadeDeUmProduto % quantidadeAtivacao);

				if (divisao > 0) {
					total += precoFinal * divisao;
				}

				if (resto > 0) {
					total = total + (precoProduto * resto);
				}

			} else if (promocao.getQtdeAtivacao() > 0 && promocao.getQtdPaga() > 0) {

				int quantidadesAPagar = promocao.getQtdPaga();

				int divisao = (quantidadeDeUmProduto / quantidadeAtivacao);
				int resto = (quantidadeDeUmProduto % quantidadeAtivacao);

				if (divisao > 0) {
					total += quantidadesAPagar * precoProduto * divisao;
				}

				if (resto > 0) {
					total = total + (precoProduto * resto);
				}

			} else {
				total = total + (precoProduto * quantidadeDeUmProduto);
			}
		}

		return total;
	}

}
