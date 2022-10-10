package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionMysql;
import modelos.Huesped;


public class DatosHuesped {
	
	//almacena la conexion
	private Connection conexion ;
	private PreparedStatement preparedStatement;
	
	public List<Huesped> listarHuespedes() {
		
		List<Huesped> listaHuespeds = new ArrayList<Huesped>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		
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
				
				Huesped huesped = new Huesped(resultSet.getInt("id"), 
									   resultSet.getString("Nombre"), 
									 resultSet.getString("Apellido"), 
						      LocalDate.parse(resultSet.getString("FechaNacimiento"), formatter) , 
						         resultSet.getString("Nacionalidad"), 
						             resultSet.getInt("Telefono"));
				
				listaHuespeds.add(huesped);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHuespeds;
		
	}
	public static void main(String[] args) {
		new DatosHuesped().listarHuespedes();
		
	}

}
