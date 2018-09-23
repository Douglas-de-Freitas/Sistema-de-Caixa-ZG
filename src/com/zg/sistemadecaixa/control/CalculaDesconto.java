package com.zg.sistemadecaixa.control;

import java.util.List;

import com.zg.sistemadecaixa.model.Item;

public interface CalculaDesconto {

	public Double calculoDeDesconto(List<Item> listaItens);

}
