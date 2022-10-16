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
import modelos.Reserva;
import ui.ReservaTableModel;

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
				Huesped huesped = crearHuesped(resultSet);
				huesped.setReserva(new DatosReserva().buscarReservaId(resultSet.getInt("IdReserva")));
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
			preparedStatement = connection.prepareStatement("DELETE FROM hotel.huespedes WHERE Id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean guardarHuesped(Huesped huesped) {
		Connection connection = new ConexionMysql().conexion();
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO hotel.huespedes(Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, IdReserva)"
					+ "VALUES(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, huesped.getNombre());
			preparedStatement.setString(2, huesped.getApellido());
			preparedStatement.setString(3, formatter.format(huesped.getFechaNacimiento()));
			preparedStatement.setString(4, huesped.getNacionalidad());
			preparedStatement.setInt(5, huesped.getTelefono());
			preparedStatement.setInt(6, huesped.getReserva().getId());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editarHuesped(Huesped huesped) {
		this.conexion = new ConexionMysql().conexion();
		try {
			preparedStatement = this.conexion.prepareStatement("UPDATE hotel.huespedes SET Nombre=?, Apellido=?, FechaNacimiento=?, Nacionalidad=?, Telefono=? "
					+ " WHERE Id=?");
			preparedStatement.setString(1, huesped.getNombre());
			preparedStatement.setString(2, huesped.getApellido());
			preparedStatement.setString(3, formatter.format(huesped.getFechaNacimiento()));
			preparedStatement.setString(4, huesped.getNacionalidad());
			preparedStatement.setInt(5, huesped.getTelefono());
			preparedStatement.setInt(6,huesped.getId());
			preparedStatement.executeUpdate();
			return true;
			
		}catch (Exception e) {
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
			e.printStackTrace();
		}
		return null;
	}
	public List<Huesped> filtroHuesped(String filtro){
		this.conexion = new ConexionMysql().conexion();
		List<Huesped> listaHuespedes = new ArrayList<Huesped>();
		try {
			preparedStatement = this.conexion.prepareStatement("SELECT x.* FROM hotel.huespedes x where x.Id like '"+filtro+"%'");
			ResultSet hu = preparedStatement.executeQuery();
			while (hu.next()) {
				listaHuespedes.add(crearHuesped(hu));
			}
			return listaHuespedes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
