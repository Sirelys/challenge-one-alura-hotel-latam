package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import conexion.ConexionMysql;


public class DatosReserva {
	
	//almacena la conexion
	private Connection conexion ;
	private PreparedStatement preparedStatement;
	
	/**
	 * accede a la base de datos y devuelve una lista de reservas
	 * @return
	 */
	public List listarReservas() {
		//obtiene la conexion a base de datos
		ConexionMysql conexionMysql = new ConexionMysql();
		//ejecuta el metodo conectar y almacena el objeto que devuelve el metodo conexion
		conexion = conexionMysql.conexion();
		
		try {
			//prepara la consulta sql y obtenemos un objeto con los datos
			preparedStatement = conexion.prepareStatement("select * from reservas");
			//ejecuta la consulta y devuelve el resultado
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("********");
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("FechaEntrada"));
				System.out.println(resultSet.getString("FechaSalida"));
				System.out.println(resultSet.getString("Valor"));
				System.out.println(resultSet.getString("FormaPago"));
				System.out.println("****");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public static void main(String[] args) {
		new DatosReserva().listarReservas();
	}

}
