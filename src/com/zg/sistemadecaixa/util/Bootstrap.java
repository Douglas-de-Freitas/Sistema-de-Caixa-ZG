package com.zg.sistemadecaixa.util;

import java.util.List;

import com.zg.sistemadecaixa.dao.ProdutoDao;
import com.zg.sistemadecaixa.dao.PromocaoDao;
import com.zg.sistemadecaixa.model.Produto;
import com.zg.sistemadecaixa.model.Promocao;

public class Bootstrap {

	private static ProdutoDao produtoDao = new ProdutoDao();
	private static PromocaoDao promocaoDao = new PromocaoDao();

	public static void lancarDadosNoBanco() {

		/*
		 * eu poderia ter verificado se existe algo especifico no banco, e caso não
		 * existisse eu adicionaria só que preferi limpar as tabelas a cada execução
		 * pois para mim visivelmente o programa executou de forma mais rapida. mas se
		 * fosse fazer de outro jeito, para mim não teria nenhum problema
		 */

		criaAsTabelasSeNaoExiste();
		limparTabelas();
		trataPromocaoLida();
		trataProdutoLido();

	}

	private static void trataProdutoLido() {
		
		try {

			String nomeDoArquivo1 = LerDocumento.arquivoChekcout;
	
			List<String> id = LerDocumento.obterDadosDeTextoComRegex(Regex.getSku.regex, LerDocumento.lerTxt(nomeDoArquivo1));
	
			List<String> valor = LerDocumento.obterDadosDeTextoComRegex(Regex.getValor.regex,
					LerDocumento.lerTxt(nomeDoArquivo1));
			List<String> descricao = LerDocumento.obterDadosDeTextoComRegex(Regex.getDescricao.regex,
					LerDocumento.lerTxt(nomeDoArquivo1));
			List<String> promocoes = LerDocumento.obterDadosDeTextoComRegex(Regex.getPromocoes.regex,
					LerDocumento.lerTxt(nomeDoArquivo1));
	
			for (int i = 0; i < id.size(); i++) {
				Produto produto = new Produto();
				produto.setIdentificador(Integer.parseInt(id.get(i)));
				produto.setValor(Double.parseDouble(valor.get(i)));
				produto.setDescricao(descricao.get(i));
				produto.setPromocao(Integer.parseInt(promocoes.get(i)));
				produtoDao.inserirProduto(produto);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void trataPromocaoLida() {
		
		try {

			String nomeArquivo2 = LerDocumento.arquivoPromocoes;
	
			List<String> arquivo = LerDocumento.lerCsv(nomeArquivo2);
	
			Promocao promocao = new Promocao();
	
			for (int i = 1; i < arquivo.size(); i++) {
	
				String linha = arquivo.get(i);
				String sep[] = linha.split(",");
	
				for (int j = 0; j < sep.length; j++) {
	
					String textoSeparado = sep[j];
	
					switch (j) {
					case 0:
						promocao.setId(Integer.parseInt(textoSeparado));
						break;
					case 1:
						promocao.setDescricao(textoSeparado);
						break;
					case 2:
						promocao.setObs(textoSeparado);
						break;
					case 3:
						promocao.setQtdeAtivacao(Integer.parseInt(textoSeparado));
						break;
					case 4:
						if (textoSeparado.equals(""))
							promocao.setPrecoFinal(0);
						else
							promocao.setPrecoFinal(Integer.parseInt(textoSeparado));
						break;
					case 5:
						if (textoSeparado.equals(""))
							promocao.setQtdPaga(0);
						else
							promocao.setQtdPaga(Integer.parseInt(textoSeparado));
					}
				}
	
				if (sep.length == 5) {
					promocao.setQtdPaga(0);
				}
	
				promocaoDao.inserirPromocao(promocao);
			}
	
			Promocao nula = new Promocao();
			nula.setId(-1);
			nula.setQtdeAtivacao(0);
			nula.setPrecoFinal(0);
			nula.setQtdPaga(0);
			promocaoDao.inserirPromocao(nula);
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void limparTabelas() {
		produtoDao.deletarProdutos();
		promocaoDao.deletarPromocoes();
	}

	private static void criaAsTabelasSeNaoExiste() {
		promocaoDao.criaBanco();
		produtoDao.criaBanco();
	}

}
