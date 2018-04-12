package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbConnection.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String cmd = "INSERT INTO produto(id_produto,nome,valor_inicial,maiorLance,descricao) values(3214466,pentezao,1234.0,0.0,fdvadvadfasdfasdf);";
//
//		PreparedStatement ps = connection.prepareStatement(cmd);
//		if (ps.executeUpdate() > 0) {
//			System.out.println("Cadastrado com sucesso");
//		}
//		connection.commit();
//		ps.close();
//		connection.close();
	}

}
