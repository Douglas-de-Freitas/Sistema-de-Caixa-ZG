package com.zg.sistemadecaixa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zg.sistemadecaixa.model.Promocao;

public class PromocaoDao {

	private static final String CONSULTAR_PROMOCOES = "SELECT * FROM PROMOCAO";
	private static final String CONSULTAR_PROMOCAO_IDENTIFICADOR = "SELECT * FROM PROMOCAO WHERE IDENTIFICADOR = ?";
	private static final String INSERIR_PROMOCAO = "INSERT INTO PROMOCAO VALUES(?, ?, ?, ?)";

	public List<Promocao> consultarPromocoes() {

		List<Promocao> listaPromocoes = new ArrayList<>();

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CONSULTAR_PROMOCOES)) {

			ResultSet resultado = banco.executeQuery();

			while (resultado.next()) {
				Promocao promocao = new Promocao();
				promocao.setIdentificador(resultado.getString("identificador"));
				promocao.setQuantidade(resultado.getInt("quantidade_ativacao"));
				promocao.setPrecoEspecial(resultado.getInt("preco_final"));
				promocao.setQtdsPagar(resultado.getInt("quantidade_paga"));
				listaPromocoes.add(promocao);
			}

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaPromocoes;

	}

	public Promocao consultarPromocaoEspecifica(String identificador) {

		Promocao promocao = null;

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(CONSULTAR_PROMOCAO_IDENTIFICADOR)) {

			banco.setString(1, identificador);

			ResultSet resultado = banco.executeQuery();

			if (resultado.next()) {
				promocao = new Promocao();
				promocao.setIdentificador(resultado.getString("identificador"));
				promocao.setQuantidade(resultado.getInt("quantidade_ativacao"));
				promocao.setPrecoEspecial(resultado.getInt("preco_final"));
				promocao.setQtdsPagar(resultado.getInt("quantidade_paga"));
			}

			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return promocao;

	}

	public boolean iserirPromocao(Promocao promocao) {

		boolean status = false;

		try (Connection connection = FabricaConexao.getConexao();
				PreparedStatement banco = connection.prepareStatement(INSERIR_PROMOCAO)) {

			banco.setString(1, promocao.getIdentificador());
			banco.setInt(2, promocao.getQuantidade());
			banco.setInt(3, promocao.getPrecoEspecial());
			banco.setInt(4, promocao.getQtdsPagar());

			banco.execute();
			status = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;

	}

}
