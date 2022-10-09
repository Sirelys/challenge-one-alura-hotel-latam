package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import conexion.ConexionMysql;


public class DatosHuesped {
	
	//almacena la conexion
	private Connection conexion ;
	private PreparedStatement preparedStatement;
	
	public List listarHuespedes() {
		
		ConexionMysql conexionMsql = new ConexionMysql();
		conexion = conexionMsql.conexion();
		
		try {
			
			preparedStatement = conexion.prepareStatement("select * from huespedes");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("******");
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("Nombre"));
				System.out.println(resultSet.getString("Apellido"));
				System.out.println(resultSet.getString("FechaNacimiento"));
				System.out.println(resultSet.getString("Nacionalidad"));
				System.out.println(resultSet.getString("Telefono"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static void main(String[] args) {
		new DatosHuesped().listarHuespedes();
		
	}

}
