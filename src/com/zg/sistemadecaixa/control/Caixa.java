package com.zg.sistemadecaixa.control;

import java.util.HashMap;
import java.util.Map;

import com.zg.sistemadecaixa.dao.ProdutoDao;
import com.zg.sistemadecaixa.dao.PromocaoDao;
import com.zg.sistemadecaixa.model.Produto;
import com.zg.sistemadecaixa.model.Promocao;

public class Caixa implements AplicaPromocao {

	private Map<Produto, Integer> itens = new HashMap<>();
	private int totalPreco;

	public void add(String identificador) { // metodo para adicionar um novo produto

		Produto produto = new Produto(identificador); // crio novo produto com o idenrificador e valor 0

		if (itens.containsKey(produto)) {
			int quantidade = itens.get(produto);
			itens.put(produto, (quantidade + 1));
		} else {
			itens.put(produto, 1);
		}

	}

	public void remove(String identificador) { // metodo para remove um produto

		Produto produto = new Produto(identificador); // crio novo produto com o idenrificador e valor 0

		if (itens.containsKey(produto)) {

			int quantidade = itens.get(produto);

			if (quantidade == 1) {
				itens.remove(produto);
			} else {
				itens.put(produto, quantidade - 1);
			}

		}

	}

	public void getTotalPrice() {

		int total = 0;

		for (Produto produto : itens.keySet()) {
			total += aplicarPromocao(produto);
		}

		totalPreco = total;
//		System.out.println(total);

	}

	public void getTotalDiscount() {

		Double total = 0.0;

		for (Produto produto : itens.keySet()) {
			total = total + (itens.get(produto) * produto.getValor());
		}

		total = total - totalPreco;

//		System.out.println(total);

	}

	@Override
	public Double aplicarPromocao(Produto produto) { // pode aplicar ou não uma promoção , depende do bd

		Double total = 0.0;
		PromocaoDao promocaoDao = new PromocaoDao();
		ProdutoDao produtoDao = new ProdutoDao();

		Promocao promocaoEspecifica = promocaoDao.consultarPromocaoEspecifica(produto.getIdentificador());
		Double precoProduto = produtoDao.consultarPrecoProduto(produto).getValor(); // preço do produto no banco de dados

		int quantidadeParaPromocao = promocaoEspecifica.getQuantidade();

		if (quantidadeParaPromocao == 0)
			return precoProduto;

		int divisao = itens.get(produto) / quantidadeParaPromocao;
		int resto = itens.get(produto) % quantidadeParaPromocao;

		if (divisao > 0) {
			total = (double) promocaoEspecifica.getPrecoEspecial() * divisao;
		}

		if (resto > 0) {
			total = total + (precoProduto * resto);
		}

		return total;

	}

}
