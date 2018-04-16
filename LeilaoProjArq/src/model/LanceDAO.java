package model;

import dbConnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LanceDAO implements DAO{
	
	private String cmd;
	private PreparedStatement ps;
    private Connection connection;
    /*Variaveis de entrada*/
    private Lance lance;
    private int pkProduto;
    
    public LanceDAO(Lance lance) {
    	this.lance = lance;
    }
    
    public LanceDAO(int pkProduto) {
    	this.pkProduto = pkProduto;
    }
    
	@Override
	public boolean insert() {
		boolean sucesso = false;
    	
    	cmd = "INSERT INTO leilao.Lance(id_lance, id_produto, valor) values(?,?,?);";

    	try {
    		connection = new ConnectionFactory().getConnection();    		    		
    		connection.setAutoCommit(false);

            ps = connection.prepareStatement(cmd);
            
            ps.setInt(1, lance.getId());
            ps.setInt(2, lance.getIdProduto());
            ps.setDouble(3, lance.getValor());
            
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

	@Override
	public ArrayList<Object> selectAll() {

		ArrayList<Object> listLances = null;
    	cmd = "SELECT * FROM leilao.Lance INNER JOIN leilao.Produto ON leilao.Produto.id_produto = " 
    			+ "leilao.Lance.id_produto WHERE leilao.Lance.id_produto = " + pkProduto + ";";	

    	try {
    		connection = new ConnectionFactory().getConnection();    		
    		connection.setAutoCommit(false);
    		
            ps = connection.prepareStatement(cmd);
            ResultSet rs = ps.executeQuery();
    
            
            listLances = new ArrayList<>();
            
            while(rs.next()) {  
      
            	int idLance = rs.getInt("id_lance");
            	double valor = rs.getDouble("valor");  
          
            	Lance lance = new Lance();
            	
            	lance.setId(idLance);
            	lance.setIdProduto(pkProduto);
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

	@Override
	public Object selectEspecificData() {
		// TODO Auto-generated method stub
		return null;
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
    

}
