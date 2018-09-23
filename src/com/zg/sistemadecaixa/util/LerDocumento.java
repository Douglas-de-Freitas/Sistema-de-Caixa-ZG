package com.zg.sistemadecaixa.util;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerDocumento {

	// caminho dos arquivos e nome dos arquivos a serem lidos
	private static final String LOCAL_ARQUIVO = "C:\\Users\\siddo\\Desktop\\";
	public static String arquivoChekcout = "Arquivo_dados_checkout.txt";
	public static String arquivoPromocoes = "promoções.csv";

	public static List<String> obterDadosDeTextoComRegex(String regex, String text) {
		
		List<String> listaDeResultados = null;
		
		try {
			
			listaDeResultados = new ArrayList<>();

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
	
			while (matcher.find()) {
				String resultadoPego = matcher.group();
				listaDeResultados.add(resultadoPego);
	
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}

		return listaDeResultados;

	}

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

	public static List<String> lerCsv(String nomeArquivo) { // fred pediu para procurar outra forma de ler csv na internet
		try {

			return Files.readAllLines(Paths.get(LOCAL_ARQUIVO + nomeArquivo), Charset.forName("UTF-8"));

		} catch (Exception e) {
			return null;
		}
	}

}
