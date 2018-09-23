package com.zg.sistemadecaixa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zg.sistemadecaixa.model.Promocao;

public class PromocaoDao {

	private static final String INSERIR_PROMOCAO = "INSERT INTO PROMOCAO(ID, DESCRICAO, OBS, QTDE_ATIVACAO, PRECO_FINAL, QTD_PAGA) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_PROMOCAO_ID = "SELECT * FROM PROMOCAO WHERE ID = ?";
	private static final String DELETAR_PROMOCOES = "DELETE FROM PROMOCAO";
	private static final String CRIA_BANCO = "CREATE TABLE IF NOT EXISTS PROMOCAO(ID INT PRIMARY KEY UNIQUE NOT NULL, DESCRICAO VARCHAR, OBS VARCHAR, QTDE_ATIVACAO INT, PRECO_FINAL DECIMAL, QTD_PAGA INT)";

	public void criaBanco() {

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CRIA_BANCO)) {

			banco.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirPromocao(Promocao promocao) {

		try (Connection con = FabricaConexao.getConexao();
				PreparedStatement banco = con.prepareStatement(INSERIR_PROMOCAO)) {

			banco.setInt(1, promocao.getId());
			banco.setString(2, promocao.getDescricao());
			banco.setString(3, promocao.getObs());
			banco.setInt(4, promocao.getQtdeAtivacao());
			banco.setInt(5, promocao.getPrecoFinal());
			banco.setInt(6, promocao.getQtdPaga());

			banco.execute();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Não foi possível inserir");
		}

	}

	public Promocao getPromocaoId(int idPromocao) {

		Promocao promocao = null;

		try (Connection con = FabricaConexao.getConexao();
				PreparedStatement banco = con.prepareStatement(GET_PROMOCAO_ID)) {

			banco.setInt(1, idPromocao);

			ResultSet resultado = banco.executeQuery();

			if (resultado.next()) {
				promocao = new Promocao();
				promocao.setId(resultado.getInt("id"));
				promocao.setDescricao(resultado.getString("descricao"));
				promocao.setObs(resultado.getString("obs"));
				promocao.setQtdeAtivacao(resultado.getInt("qtde_ativacao"));
				promocao.setPrecoFinal(resultado.getInt("preco_final"));
				promocao.setQtdPaga(resultado.getInt("qtd_paga"));
			}

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return promocao;

	}

	public void deletarPromocoes() {
		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(DELETAR_PROMOCOES)) {

			banco.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
