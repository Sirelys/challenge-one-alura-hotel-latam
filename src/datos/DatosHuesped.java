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

	// almacena la conexion
	private Connection conexion;
	private PreparedStatement preparedStatement;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

	public List<Huesped> listarHuespedes() {

		List<Huesped> listaHuespeds = new ArrayList<Huesped>();

		ConexionMysql conexionMsql = new ConexionMysql();
		conexion = conexionMsql.conexion();

		try {

			preparedStatement = conexion.prepareStatement("select * from huespedes");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("******");
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("Nombre"));
				System.out.println(resultSet.getString("Apellido"));
				System.out.println(resultSet.getString("FechaNacimiento"));
				System.out.println(resultSet.getString("Nacionalidad"));
				System.out.println(resultSet.getString("Telefono"));

				Huesped huesped = crearHuesped(resultSet);

				listaHuespeds.add(huesped);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHuespeds;

	}

	public Huesped buscarHuespedId(int id) {
		Connection connection = new ConexionMysql().conexion();
		Huesped huesped = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT x.* FROM hotel.huespedes x where x.Id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				huesped = crearHuesped(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return huesped;
	}
	
	/**
	 * Elimina el huesped por el id recibido
	 * @param id
	 * @return
	 */
	public boolean eliminarHuespedId(int id) {
		Connection connection = new ConexionMysql().conexion();
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM hotel.reservas WHERE Id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	private Huesped crearHuesped(ResultSet resultSet) {
		try {
			return new Huesped(resultSet.getInt("id"), resultSet.getString("Nombre"), resultSet.getString("Apellido"),
					LocalDate.parse(resultSet.getString("FechaNacimiento"), formatter),
					resultSet.getString("Nacionalidad"), resultSet.getInt("Telefono"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
