package com.zg.sistemadecaixa.view;

import com.zg.sistemadecaixa.control.Caixa;
import com.zg.sistemadecaixa.util.Bootstrap;
import com.zg.sistemadecaixa.util.LerDocumento;

public class Visao {

	public static void main(String[] args) {

		Caixa checkout = new Caixa();
		Bootstrap.lancarDadosNoBanco();
		

		// ProdutoDao dao = new ProdutoDao();
		// dao.consultarPrecoProduto(new Produto(""));

		// TESTE 1

		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();

		// TESTE 2

		// checkout.add("D");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("A");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();

		// TESTE 3

		// checkout.add("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();

		// TESTE 4

		// checkout.add("C");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.add("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();
		// checkout.remove("B");
		// checkout.getTotalPrice();
		// checkout.getTotalDiscount();

	}

}
