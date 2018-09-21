package com.zg.sistemadecaixa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	
	private static String url = "jdbc:postgresql://localhost:5432/zg_solucoes";
	private static String user = "postgres";
	private static String password = "123456";
	
	public static Connection getConexao() throws SQLException{
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection(url, user, password);
			
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		
	}

}
