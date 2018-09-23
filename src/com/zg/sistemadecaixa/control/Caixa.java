package com.zg.sistemadecaixa.control;

import java.util.ArrayList;
import java.util.List;

import com.zg.sistemadecaixa.dao.ProdutoDao;
import com.zg.sistemadecaixa.model.Item;
import com.zg.sistemadecaixa.model.Produto;
import com.zg.sistemadecaixa.model.Promocao;
import com.zg.sistemadecaixa.util.Bootstrap;

public class Caixa {

	List<Item> listaDeItens;
	Double precoTotal;

	public Caixa() {
		listaDeItens = new ArrayList<>();
		precoTotal = 0.0;
		Bootstrap.lancarDadosNoBanco();
	}

	public void add(String identificador) { // metodo para adicionar um produto

		try {

			ProdutoDao dao = new ProdutoDao();
			Produto produto = dao.getProduto(Integer.parseInt(identificador));

			if (produto != null) {

				boolean itemExiste = false;

				for (int i = 0; i < listaDeItens.size(); i++) {

					if (listaDeItens.get(i).getProduto().getIdentificador() == Integer.parseInt(identificador)) {
						int quantidade = listaDeItens.get(i).getQuantidade();
						listaDeItens.get(i).setQuantidade(quantidade + 1);
						itemExiste = true;
						break;
					}

				}

				if (!itemExiste) {
					Item item = new Item();
					item.setProduto(produto);
					item.setQuantidade(1);
					listaDeItens.add(item);
				}

				// Adicionaria também ao banco de dados

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void remove(String identificador) { // metodo para remove um produto

		try {

			for (int i = 0; i < listaDeItens.size(); i++) {
				if (listaDeItens.get(i).getProduto().getIdentificador() == Integer.parseInt(identificador)) {

					int quantidade = listaDeItens.get(i).getQuantidade();
					if (quantidade > 1) {
						listaDeItens.get(i).setQuantidade(quantidade);
					} else {
						listaDeItens.remove(i);
					}

					break;
				}

			}

			// Removeria também do banco de dados

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Double getTotalPrice() {
		
		Double total = 0.0;
		
		try {

			Promocao promo = new Promocao();
	
			total += promo.calculoDeDesconto(listaDeItens); // conforme fred pediu implementei a interface em outra classe
	
			precoTotal = total;
	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;

	}

	public Double getTotalDiscount() {

		Double total = 0.0;
		
		try {

			for (Item item : listaDeItens) {
				total += item.getProduto().getValor() * item.getQuantidade();
			}
	
			total = total - precoTotal;
	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;

	}
}
