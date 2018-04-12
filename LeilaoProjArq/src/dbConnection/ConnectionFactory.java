package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private Connection con = null;

	public Connection getConnection() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
		
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/leilao", 
					"root", 
					null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
