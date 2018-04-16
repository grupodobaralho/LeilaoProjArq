package model;

import dbConnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;

import model.Produto;

public class ProdutoDAO {
	
	//TODO: criar interface para as duas DAOS
	//TODO: criar dois contrutores (um com produto e outro com
	
	private String cmd;
	private PreparedStatement ps;
    private Connection connection;
    
    
    public boolean inserir(Produto prod) {
    	boolean sucesso = false;

    	cmd = "INSERT INTO leilao.Produto(id_produto,nome,valor_inicial,maiorLance,descricao) values(?,?,?,?,?);";

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
    
    public ArrayList<Produto> getProdutos() {
    	ArrayList<Produto> listProduto = null;
    	cmd = "SELECT * FROM leilao.Produto;";
    	
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ResultSet rs = ps.executeQuery();
    
            
            listProduto = new ArrayList<>();
            
            while(rs.next()) {  
      
            	int id = rs.getInt("id_produto");
                String nome = rs.getString("nome");
                int valorInicial = rs.getInt("valor_inicial");
                int maiorLance = rs.getInt("maiorLance");
                String descricao = rs.getString("descricao");
       
                Produto prod = new Produto();
                prod.setId(id);
                prod.setNome(nome);  
                prod.setValorInicial(valorInicial);
                prod.setMaiorLance(maiorLance);
                prod.setDescricao(descricao);
               
                listProduto.add(prod);  
            }
            
            System.out.println("Conexao aberta > getProdutos > produtos listados com sucesso");
            
            ps.close();
            connection.close();
            

        } catch (SQLException ex) {
            try {
                System.out.print("N�o foi poss�vel listar os dados");
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
        return listProduto;
    }
    
    public Produto getProdutoEspecifico(int id) {
    	String cmd = "SELECT * FROM leilao.Produto WHERE id_produto = ?;";
    	Produto prod = new Produto();
    	
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
           
            if(rs.next()) {
	        	int idProduto= rs.getInt("id_produto");
	            String nome = rs.getString("nome");
	            int valorInicial = rs.getInt("valor_inicial");
	            int maiorLance = rs.getInt("maiorLance");
	            String descricao = rs.getString("descricao");
	            
	            prod.setId(idProduto);
	            prod.setNome(nome);  
	            prod.setValorInicial(valorInicial);
	            prod.setMaiorLance(maiorLance);
	            prod.setDescricao(descricao);
            }
            
            System.out.println("Conex�o aberta > getProduto > capturando produto. ");
            
            ps.close();
            connection.close();
            
        } catch (SQLException ex) {
            try {
                System.out.println("Produto n�o encontrado!");
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
    	
    	return prod;
    }
    
    public boolean atualizaMaiorLance(Lance lance) {
    	boolean sucesso = false;
    	cmd = "UPDATE leilao.Produto SET maiorLance = ? WHERE id_Produto = ?;";
    	
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            
            ps.setDouble(1, lance.getValor());
            ps.setInt(2, lance.getIdProduto());
            
            ps.execute();
            
            connection.commit();
            ps.close();
            connection.close();
            
            System.out.println("Conexao aberta > atualizaMaiorLance > atualizacao feita com sucesso.");

        } catch (SQLException ex) {
            try {
                System.out.print("Ocorreu um erro na atualizacao do Maior Lance!");
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
