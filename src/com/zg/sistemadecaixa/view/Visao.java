package com.zg.sistemadecaixa.view;

import com.zg.sistemadecaixa.control.Caixa;

public class Visao {

	public static void main(String[] args) {

		Caixa checkout = new Caixa();

		checkout.add("1");
		System.out.println(checkout.getTotalPrice());
		System.out.println(checkout.getTotalDiscount());

		checkout.add("1");
		System.out.println(checkout.getTotalPrice());
		System.out.println(checkout.getTotalDiscount());

		checkout.add("1");
		System.out.println(checkout.getTotalPrice());
		System.out.println(checkout.getTotalDiscount());

	}

}
