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
import modelos.Reserva;


public class DatosReserva {
	
	//almacena la conexion
	private Connection conexion ;
	private PreparedStatement preparedStatement;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	
	/**
	 * accede a la base de datos y devuelve una lista de reservas
	 * @return
	 */
	public List<Reserva> listarReservas() {
		
		List<Reserva> listaReservas = new ArrayList<Reserva>();
		
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
				Reserva reserva = crearReserva(resultSet);							
				listaReservas.add(reserva);
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaReservas;				
	}
	
	public Reserva buscarReservaId(int id) {
		Connection connection = new ConexionMysql().conexion();
		Reserva reserva =null;
		try {
			preparedStatement = connection.prepareStatement("SELECT x.* FROM hotel.reservas x where x.Id =?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				reserva = crearReserva(resultSet);
			}
			
		}	catch (SQLException e) {
				e.printStackTrace();
		}
		return reserva;
		
	}
	/**
	 * Elimina el huesped por el id recibido
	 * @param id
	 * @return
	 */
	public boolean eliminarReservaId(int id) {
		Connection connection = new ConexionMysql().conexion();
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM hotel.reservas WHERE Id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			return true;
		}	catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean registrarReserva(Reserva reserva) {
		this.conexion = new ConexionMysql().conexion();
		
		try {
			preparedStatement = this.conexion.prepareStatement("INSERT INTO hotel.reservas(FechaEntrada, FechaSalida, Valor, FormaPago) VALUES(?, ?, ?, ?)");
			preparedStatement.setString(1, formatter.format(reserva.getFechaEntrada()));
			preparedStatement.setString(2, formatter.format(reserva.getFechaSalida()));
			preparedStatement.setDouble(3, reserva.getValor());
			preparedStatement.setString(4, reserva.getFormaPago());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Reserva ultimaReserva() {
		this.conexion = new ConexionMysql().conexion();
		
		try {
			preparedStatement = this.conexion.prepareStatement("SELECT r.* FROM hotel.reservas r where r.Id = (select max(id) from hotel.reservas)");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				Reserva reserva = crearReserva(rs);
				return crearReserva(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 
	
	public boolean editarReserva(Reserva reserva) {
		this.conexion = new ConexionMysql().conexion();
		try {
			preparedStatement = this.conexion.prepareStatement("UPDATE hotel.reservas SET FechaEntrada=?, FechaSalida=?, Valor=?, FormaPago=? WHERE Id=?");
			preparedStatement.setString(1, formatter.format(reserva.getFechaEntrada()));
			preparedStatement.setString(2, formatter.format(reserva.getFechaSalida()));
			preparedStatement.setDouble(3,reserva.getValor());
			preparedStatement.setString(4,reserva.getFormaPago());
			preparedStatement.setInt(5, reserva.getId());
			preparedStatement.executeUpdate();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return false;		
	}
	
	public List<Reserva> filtroReserva(String filtro){
		this.conexion = new ConexionMysql().conexion();
		List<Reserva> listaReservas = new ArrayList<Reserva>();
		try {
			preparedStatement = this.conexion.prepareStatement("SELECT x.* FROM hotel.reservas x where x.Id like '"+filtro+"%'");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				listaReservas.add(crearReserva(rs));
			}
			return listaReservas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Reserva crearReserva(ResultSet resultSet) {
		try {
			return new Reserva(resultSet.getInt("id"),
					LocalDate.parse(resultSet.getString("FechaEntrada"),formatter),
					LocalDate.parse(resultSet.getString("FechaSalida"),formatter),
					resultSet.getDouble("Valor"),
					resultSet.getString("FormaPago"));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
