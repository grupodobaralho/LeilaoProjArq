package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbConnection.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Testando conexão...");
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão estabelecida!");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Descomente para testar diretamente a classe DAO
//		System.out.println("AAAAAAAAAAA");
//		ProdutoDAO pdao = new ProdutoDAO();
//		pdao.inserir(new Produto(777777, "vai porfavor", "uma descricao muito louca", 555, 0));
//		System.out.println("Foi");
		
	}

}
