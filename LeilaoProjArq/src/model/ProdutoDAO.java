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
	
	private String cmd;
	private PreparedStatement ps;
    private Connection connection;
    
    
    public boolean inserir(Produto prod) {
    	boolean sucesso = false;

    	cmd = "INSERT INTO produto(id_produto,nome,valor_inicial,maiorLance,descricao) values(?,?,?,?,?);";

    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		System.out.println("ConexÃ£o aberta!");
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
    	cmd = "SELECT * FROM PRODUTO;";
    	
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		System.out.println("Conexão aberta!");
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
            
            ps.close();
            connection.close();
            

        } catch (SQLException ex) {
            try {
                System.out.print("Não foi possível listar os dados");
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
    	String cmd = "SELECT * FROM Produto WHERE id_produto = ?;";
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
            
            System.out.println("Conexão aberta > getProduto > capturando produto. ");
            
            ps.close();
            connection.close();
            
        } catch (SQLException ex) {
            try {
                System.out.println("Produto não encontrado!");
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
    
    public boolean inserirLance(int idProduto, double valor) {
    	boolean sucesso = false;
    	int index = sizeLance() + 1;

    	cmd = "INSERT INTO lance(id_lance, id_produto, valor) values("+ index + ", " +idProduto + ", " + valor + ");";

    	try {
    		connection = new ConnectionFactory().getConnection();    		    		
    		connection.setAutoCommit(false);

            ps = connection.prepareStatement(cmd);
            ps.execute();
            
            System.out.println("Conexão aberta > iniserirLance > insersão completa. ");
            
            connection.commit();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            try {
                System.out.println("Os dados de Lance nao foram gravados");
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
    
    public int sizeLance() {

    	cmd = "SELECT COUNT(*) FROM Lance";
    	
    	int cont = 0;
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	cont= rs.getInt("COUNT(*)");
            }

            System.out.println("Conexão aberta > sizeLance > Cont: " + cont);
            
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            try {
                System.out.println("Não foi possivel contar os dados");
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
        return cont;
    }
    
    public ArrayList<Lance> getLances(int id) {
    	ArrayList<Lance> listLances = null;
    	cmd = "SELECT * FROM Lance INNER JOIN Produto ON Produto.id_produto = Lance.id_produto WHERE Lance.id_produto = " + id + ";";	
    	Produto prod = getProdutoEspecifico(id);

    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ResultSet rs = ps.executeQuery();
    
            
            listLances = new ArrayList<>();
            
            while(rs.next()) {  
      
            	int idLance = rs.getInt("id_lance");
            	int valor = rs.getInt("valor");  
          
            	Lance lance = new Lance();
            	
            	lance.setId(idLance);
            	lance.setProduto(prod);
            	lance.setValor(valor);
            	
            	listLances.add(lance);
            }
            
            System.out.println("Conexão aberta > getLance > capturando lances.");
            
            ps.close();
            connection.close();
            

        } catch (SQLException ex) {
            try {
                System.out.println("Não foi possível listar os lances");
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
        return listLances;
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
