package controlsviews;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

	public ControlSearch() {
		super();
		this.agregarDatosTablaHuesped();
		this.agregarDatosTablaReserva();
		this.init();
	}

	private void init() {
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (panel.getSelectedIndex() == 1) {
						eliminarHuesped();
					} else if (panel.getSelectedIndex() == 0) {
						eliminarReserva();
					}
				}
			}
		});
	}

	private void agregarDatosTablaHuesped() {
		this.huespeds = datosHuesped.listarHuespedes();

		HuespedTableModel model = (HuespedTableModel) tbHuespedes.getModel();
		model.setListaHuespeds(huespeds);
		tbHuespedes.setModel(model);
		
	}	

	private void limpiarTablaHuesped() {
		HuespedTableModel model = (HuespedTableModel) tbHuespedes.getModel();
		model.getListaHuespeds().clear();
		tbHuespedes.setModel(model);
		tbHuespedes.removeAll();
	}

	private void eliminarHuesped() {
		int row = tbHuespedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione el huesped a eliminar");
			return;
		}
		int id = Integer.parseInt(tbHuespedes.getValueAt(row, 0).toString());
		int opcion = JOptionPane.showConfirmDialog(null,
				"¿Deseas eliminar el huesped seleccionado?\nSe eliminará el huesped y su reserva");
		System.out.println(opcion);
		if (opcion == 0) {
			if (datosHuesped.eliminarHuespedId(id)) {
				JOptionPane.showMessageDialog(null, "Huesped eliminado correctamente");
				limpiarTablaHuesped();
				agregarDatosTablaHuesped();
				

			} else {
				JOptionPane.showMessageDialog(null, "Error al eliminar huesped");
			}
		}
	}

	public static void main(String[] args) {
		new ControlSearch().setVisible(true);
	}

	private void limpiarTablaReserva() {
		ReservaTableModel model = (ReservaTableModel) tbReservas.getModel();
		model.getListaReservas().clear();
		tbReservas.setModel(model);
		tbReservas.removeAll();
		tbReservas.repaint();
	}

	private void agregarDatosTablaReserva() {
		this.reservas = datosReserva.listarReservas();

		ReservaTableModel model = (ReservaTableModel) tbReservas.getModel();
		model.setListaReservas(reservas);
		tbReservas.setModel(model);

	}

	private void eliminarReserva() {
		int row = tbReservas.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione la reserva a eliminar");
			return;
		}
		int id = Integer.parseInt(tbReservas.getValueAt(row, 0).toString());
		int opcion = JOptionPane.showConfirmDialog(null,
				"¿Deseas eliminar la reserva seleccionada?\nSe eliminará la reserva");
		System.out.println(opcion);
		if (opcion == 0) {
			if (datosReserva.eliminarReservaId(id)) {
				JOptionPane.showMessageDialog(null, "Reserva eliminado correctamente");
				agregarDatosTablaReserva();
				limpiarTablaReserva();

			} else {
				JOptionPane.showMessageDialog(null, "Error al eliminar la reserva");
			}
		}
	}

}
