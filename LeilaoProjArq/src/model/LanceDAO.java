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
    private ProdutoDAO prodDAO = new ProdutoDAO(); 
    
    
    public boolean inserir(int idProduto, double valor) {
    	boolean sucesso = false;
    	int index = sizeLance() + 1;
    	
    	lanceEhMaior(idProduto, valor);

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
    
    public ArrayList<Lance> getLances(int id) {
    	ArrayList<Lance> listLances = null;
    	cmd = "SELECT * FROM Lance INNER JOIN Produto ON Produto.id_produto = Lance.id_produto WHERE Lance.id_produto = " + id + ";";	
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
    
    public void lanceEhMaior(int idProduto, double valor) {
    	ProdutoDAO pDao = new ProdutoDAO();
    	Produto prod = pDao.getProdutoEspecifico(idProduto);
    	
    	if(prod.getMaiorLance() >= valor)
    		return;
    	else 
    		pDao.atualizaMaiorLance(idProduto, valor);
    	
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
