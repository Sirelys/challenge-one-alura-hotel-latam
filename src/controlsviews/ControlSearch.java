package controlsviews;

import java.util.List;

import datos.DatosHuesped;
import datos.DatosReserva;
import modelos.Huesped;
import modelos.Reserva;
import ui.HuespedTableModel;
import ui.ReservaTableModel;
import views.Busqueda;

public class ControlSearch extends Busqueda {
	
	private DatosHuesped datosHuesped = new DatosHuesped();
	private List<Huesped> huespeds;
	private DatosReserva datosReserva = new DatosReserva();
	private List<Reserva> reservas;
	
	
	public ControlSearch () {
		super();
		this.agregarDatosTablaHuesped();
		this.agregarDatosTablaReserva();

		
	}
	
	private void agregarDatosTablaHuesped() {
		this.huespeds = datosHuesped.listarHuespedes();
		
		HuespedTableModel model = (HuespedTableModel) tbHuespedes.getModel();
		model.setListaHuespeds(huespeds);
		tbHuespedes.setModel(model);
		
	}
	private void agregarDatosTablaReserva() {
		this.reservas = datosReserva.listarReservas();
		
		ReservaTableModel model = (ReservaTableModel) tbReservas.getModel();
		model.setListaReservas(reservas);
		tbReservas.setModel(model);
		
	}

	public static void main(String[] args) {
		new ControlSearch().setVisible(true);
	}
}
