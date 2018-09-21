package com.zg.sistemadecaixa.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LerDocumento {

	private static final String LOCAL_ARQUIVO = "C:\\Users\\siddo\\Desktop\\"; // defina aqui o local do arquivo
//	private static String arquivoChekcout = "Arquivo_dados_checkout.txt";
//	private static String arquivoPromocoes = "promoções.csv";

	public static String lerTxt(String nomeArquivo) { // método que faz a leitura do aquivo utilizando NIO

		List<String> listaLinhas;
		
		try {

			String texto = "";
			listaLinhas = Files.readAllLines(Paths.get(LOCAL_ARQUIVO + nomeArquivo), Charset.forName("UTF-8"));

			for (String linha : listaLinhas) {
				texto += (linha + " ");
			}

			return texto.trim();

		} catch (Exception e) {
			return null;
		}

	}
	
	public static void lerCsv(String nomeArquivo){ // fred pediu para procurar outra forma de ler csv na internet
		
		// METODO AINDA DEVE SER IMPLEMENTADO, POR NÃO ESTÁ PRONTO, OS DADOS ESTÃO SENDO JOGADOS
		// DIRETO NO BANCO DE DADOS
		
		
		
	}
	
}
