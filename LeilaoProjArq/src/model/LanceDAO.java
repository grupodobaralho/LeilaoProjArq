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

public class LanceDAO {
	
	private String cmd;
	private PreparedStatement ps;
    private Connection connection;
    //TODO: encontrar produto na classe controller.
    private ProdutoDAO prodDAO = new ProdutoDAO(); 
    
    //TODO: receber o LANCE, nao as informacoes id e valor -> HERCILIO BUNDAO
    public boolean inserir(int idProduto, double valor) {
    	boolean sucesso = false;
    	int index = sizeLance() + 1;
    	
    	cmd = "INSERT INTO leilao.Lance(id_lance, id_produto, valor) values("+ index + ", " +idProduto + ", " + valor + ");";

    	try {
    		connection = new ConnectionFactory().getConnection();    		    		
    		connection.setAutoCommit(false);

            ps = connection.prepareStatement(cmd);
            ps.execute();
            
            System.out.println("Conex�o aberta > iniserirLance > insers�o completa. ");
            
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
    
    public ArrayList<Lance> getLances(int id) {
    	ArrayList<Lance> listLances = null;
    	cmd = "SELECT * FROM leilao.Lance INNER JOIN leilao.Produto ON leilao.Produto.id_produto = leilao.Lance.id_produto WHERE leilao.Lance.id_produto = " + id + ";";	
    	Produto prod = prodDAO.getProdutoEspecifico(id);

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
            
            System.out.println("Conex�o aberta > getLance > capturando lances.");
            
            ps.close();
            connection.close();
            

        } catch (SQLException ex) {
            try {
                System.out.println("N�o foi poss�vel listar os lances");
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
    
    public int sizeLance() {

    	cmd = "SELECT COUNT(*) FROM leilao.Lance";
    	
    	int cont = 0;
    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	cont= rs.getInt("COUNT(*)");
            }

            System.out.println("Conex�o aberta > sizeLance > Cont: " + cont);
            
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            try {
                System.out.println("N�o foi possivel contar os dados");
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
