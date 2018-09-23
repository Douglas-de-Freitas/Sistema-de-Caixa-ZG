package com.zg.sistemadecaixa.util;

public enum Regex {
	
	getSku("(?<=id: )\\d+"),
	getValor("(?<=valor: )\\d+\\.\\d+"),
	getDescricao("(?<=descricao: )\\w+"),
	getPromocoes("(?<=promocao: )[0-9-]+");
	
	public String regex;
	
	Regex(String rgx) {
		this.regex = rgx;
	}

}
