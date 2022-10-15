package controlsviews;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import datos.DatosReserva;
import modelos.Reserva;
import views.RegistroHuesped;
import views.ReservasView;

public class ControlReserva extends ReservasView {
	
	private Reserva reserva;
	private DatosReserva datosReserva;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	
	public ControlReserva() {
		super();
		init();
		this.datosReserva = new DatosReserva();
	}
	
	private void init() {
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validarDatosReserva()) {	
					Reserva reserva = obtenerDatosReserva();
					if(datosReserva.registrarReserva(reserva)) {
						reserva = datosReserva.ultimaReserva();
						ControlRegistroHuesped registro = new ControlRegistroHuesped(reserva);
						registro.setVisible(true);
					}
					
				}
			}						
		});
	}
	
	private boolean validarDatosReserva() {
		if(txtFechaE.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Por favor selecciones la fecha de entrada");
			txtFechaE.requestFocus();
			return false;
		}
		if(txtFechaS.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Por favor selecciones la fecha de salida");
			txtFechaS.requestFocus();
			return false;
		}
		if(txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese el valor de la reserva");
			txtValor.requestFocus();
			return false;
		}
		
		return true;
	}
	
	private Reserva obtenerDatosReserva() {
		LocalDate fechaEntrada = txtFechaE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaSalida = txtFechaS.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new Reserva(fechaEntrada, fechaSalida, Double.parseDouble(txtValor.getText()), txtFormaPago.getSelectedItem().toString());
	}
	

}
