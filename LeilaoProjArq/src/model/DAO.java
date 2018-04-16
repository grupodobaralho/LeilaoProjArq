package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class DAO {
	
	protected String cmd;
	protected PreparedStatement ps;
	protected Connection connection;
	
    /*Variaveis de entrada*/
	protected Produto prod;
	protected Lance lance;
	protected int pkProduto;
	
	public abstract boolean insert();
    public abstract ArrayList<Object> selectAll();
    public abstract Object selectEspecificData();

}
