package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
	private static final String URL= "jdbc:mysql://localhost:3306/hotel";
	private static final String user ="root";
	private static final String password ="";
	
	
	public Connection conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conMsql = DriverManager.getConnection(URL, user, password);
			
			return conMsql;
		} catch (ClassNotFoundException | SQLException notFound) {
			notFound.printStackTrace();
		}
		return null;
	}
	
}
