package com.zg.sistemadecaixa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zg.sistemadecaixa.model.Produto;

public class ProdutoDao {

	private static final String INSERIR_PRODUTO = "INSERT INTO PRODUTO(IDENTIFICADOR, DESCRICAO, VALOR, PROMOCAO) VALUES(?, ?, ?, ?)";
	private static final String CONSULTAR_IDENTIFICADOR = "SELECT IDENTIFICADOR FROM PRODUTO WHERE IDENTIFICADOR = ?";
	private static final String GET_PRODUTO_ID = "SELECT * FROM PRODUTO WHERE IDENTIFICADOR = ?";
	private static final String DELETAR_PRODUTOS = "DELETE FROM PRODUTO";
	private static final String CRIA_BANCO = "CREATE TABLE IF NOT EXISTS PRODUTO(IDENTIFICADOR INT UNIQUE NOT NULL, DESCRICAO VARCHAR NOT NULL, VALOR DECIMAL NOT NULL, PROMOCAO INT REFERENCES PROMOCAO(ID) NOT NULL)";

	public void criaBanco() {

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CRIA_BANCO)) {

			banco.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void inserirProduto(Produto produto) {

		try (Connection con = FabricaConexao.getConexao();
				PreparedStatement banco = con.prepareStatement(INSERIR_PRODUTO)) {

			banco.setInt(1, produto.getIdentificador());
			banco.setString(2, produto.getDescricao());
			banco.setDouble(3, produto.getValor());
			banco.setInt(4, produto.getPromocao());

			banco.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean consultarIdentificador(int identificador) {

		boolean status = false;

		try (Connection con = FabricaConexao.getConexao();
				PreparedStatement banco = con.prepareStatement(CONSULTAR_IDENTIFICADOR)) {

			banco.setInt(1, identificador);

			ResultSet resultado = banco.executeQuery();

			if (resultado.next()) {
				status = true;
			}

			banco.execute();

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;

	}

	public Produto getProduto(int identificador) {

		Produto produto = null;

		try (Connection con = FabricaConexao.getConexao();
				PreparedStatement banco = con.prepareStatement(GET_PRODUTO_ID)) {

			banco.setInt(1, identificador);

			ResultSet resultado = banco.executeQuery();

			if (resultado.next()) {
				produto = new Produto();
				produto.setIdentificador(identificador);
				produto.setDescricao(resultado.getString("descricao"));
				produto.setValor(resultado.getDouble("valor"));
				produto.setPromocao(resultado.getInt("promocao"));
			}

			banco.execute();

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;

	}

	public void deletarProdutos() {
		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(DELETAR_PRODUTOS)) {

			banco.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
