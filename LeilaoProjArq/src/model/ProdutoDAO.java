package model;

import dbConnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {
	
	private String cmd;
	private PreparedStatement ps;
    private Connection connection;
    
    
    public boolean inserir(Produto prod) {
    	boolean sucesso = false;

    	cmd = "INSERT INTO produto(id_produto,nome,valor_inicial,maiorLance,descricao) values(?,?,?,?,?);";

    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		System.out.println("Conexão aberta!");
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ps.setInt(1, prod.getId());
            ps.setString(2, prod.getNome());
            ps.setDouble(3, prod.getValorInicial());
            ps.setDouble(4, prod.getMaiorLance());
            ps.setString(5, prod.getDescricao());

            ps.execute();
//            if (ps.executeUpdate() > 0) {
//                System.out.println("Cadastrado com sucesso");
//                sucesso = true;
//            }
            
            connection.commit();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            try {
                System.out.print("Os dados nao foram gravados");
                connection.rollback();
            } catch (Exception ex2) {
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception ex3) {
            }
        }
        return sucesso;
    }

    public boolean excluir(Produto prod) {
    	return false;
    }

    public boolean alterar(Produto prod) {
    	return false;
    }

    public Object buscarId(int id) {
    	return null;
    }

    public Object buscarTodos() {
    	return null;
    }
    

}
