package com.zg.sistemadecaixa.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zg.sistemadecaixa.dao.ProdutoDao;
import com.zg.sistemadecaixa.model.Produto;

public class Bootstrap {

	private static final String getSku = "(?<=id: )\\d+";
	private static final String getValor = "(?<=valor: )\\d+\\.\\d+";
	private static final String getDescricao = "(?<=descricao: )\\w+";
	private static final String getPromocoes = "(?<=promocao: )[0-9-]+";

	private static List<String> getResult(String regex, String text) { // trabalha o regex de um texto qualquer e retorna uma lista

		List<String> participantList = new ArrayList<>();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String participantName = matcher.group();
			participantList.add(participantName);

		}

		return participantList;

	}

	public static void lancarDadosNoBanco() { // lança os dados lidos no txt para o banco de dados caso eles não existam

		String arquivo1 = "Arquivo_dados_checkout.txt";
		String arquivo2 = "promoções.csv";
		
		List<String> id = getResult(getSku, LerDocumento.lerTxt(arquivo1));
		List<String> valor = getResult(getValor, LerDocumento.lerTxt(arquivo1));
		List<String> descricao = getResult(getDescricao, LerDocumento.lerTxt(arquivo1));
		List<String> promocoes = getResult(getPromocoes, LerDocumento.lerTxt(arquivo1));

		ProdutoDao dao = new ProdutoDao();

		for (int i = 0; i < id.size(); i++) {
			if (dao.consultarIdentificador(id.get(i)) == null) {
				Produto produto = new Produto();
				produto.setIdentificador(id.get(i));
				produto.setValor(Double.parseDouble(valor.get(i)));
				produto.setDescricao(descricao.get(i));
				produto.setPromocao(Integer.parseInt(promocoes.get(i)));
				dao.inserirProduto(produto);
			}
		}
		

	}

}
