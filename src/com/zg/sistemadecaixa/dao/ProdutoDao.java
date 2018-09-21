package com.zg.sistemadecaixa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zg.sistemadecaixa.model.Produto;

public class ProdutoDao {

	private static final String CONSULTAR_PRODUTOS = "SELECT * FROM PRODUTO";
	private static final String CONSULTAR_PRECO_PRODUTO = "SELECT valor FROM PRODUTO WHERE identificador = ?";
	private static final String INSERIR_PRODUTO = "INSERT INTO PRODUTO(IDENTIFICADOR, VALOR, DESCRICAO, PROMOCAO ) VALUES (?, ?, ?, ?)";
	private static final String CONSULTAR_IDENTIFICADOR = "SELECT IDENTIFICADOR FROM PRODUTO WHERE IDENTIFICADOR = ?";

	public Produto consultarPrecoProduto(Produto produto) {

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CONSULTAR_PRECO_PRODUTO)) {

			banco.setString(1, produto.getIdentificador());

			ResultSet resultado = banco.executeQuery();

			if (resultado.next()) {
				produto.setValor(resultado.getDouble("valor"));
			}

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;

	}

	public List<Produto> consultarProdutos() {

		List<Produto> listaProdutos = new ArrayList<>();

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CONSULTAR_PRODUTOS)) {

			ResultSet resultado = banco.executeQuery();

			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setIdentificador(resultado.getString("identificador"));
				produto.setValor(resultado.getDouble("valor"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setPromocao(resultado.getInt("promocao"));
				listaProdutos.add(produto);
			}

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;

	}

	public boolean inserirProduto(Produto produto) {

		boolean status = false;

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(INSERIR_PRODUTO)) {

			banco.setString(1, produto.getIdentificador());
			banco.setDouble(2, produto.getValor());
			banco.setString(3, produto.getDescricao());
			banco.setInt(4, produto.getPromocao());

			banco.execute();
			status = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;

	}
	
	public String consultarIdentificador(String identificador) {
		
		String resultadoIdentificador = null;
		
		try(Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CONSULTAR_IDENTIFICADOR)){
			
			banco.setString(1, identificador);
			
			ResultSet resultado = banco.executeQuery();
			
			if(resultado.next()) {
				resultadoIdentificador = resultado.getString("identificador");
			}
			
			resultado.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultadoIdentificador;
		
	}

}
