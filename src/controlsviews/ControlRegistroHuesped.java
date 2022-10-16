package controlsviews;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import datos.DatosHuesped;
import modelos.Huesped;
import modelos.Reserva;
import views.Exito;
import views.RegistroHuesped;

public class ControlRegistroHuesped extends RegistroHuesped {

	private Reserva reserva;
	private Huesped huesped;
	private DatosHuesped datosHuesped;
	private boolean guardar = false;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

	public ControlRegistroHuesped(Reserva reserva) {
		super();
		init();
		this.datosHuesped = new DatosHuesped();
		this.reserva = reserva;
		super.txtNreserva.setText(String.valueOf(reserva.getId()));
		guardar = true;
	}

	public ControlRegistroHuesped(Huesped huesped) {
		super();
		init();
		this.datosHuesped = new DatosHuesped();
		this.huesped = huesped;
		this.labelGuardar.setText("Editar");
		mostrarHuespedEditar();
		guardar = false;
	}

	private void init() {
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validarFormularioHuesped()) {
					if (!guardar) {
						obtenerHuespedEditar();
						if(datosHuesped.editarHuesped(huesped)) {
							limpiarFormulario();
							dispose();
							new Exito().setVisible(true);
						}
					} else {
						Huesped huesped = convertirDatosHuesped();
						if (datosHuesped.guardarHuesped(huesped)) {
							limpiarFormulario();
							dispose();
							new Exito().setVisible(true);
						}
					}
				}
			}
		});
	}

	private boolean validarFormularioHuesped() {
		if (txtNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre del huesped");
			txtNombre.requestFocus();
			return false;
		}
		if (txtApellido.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese el apellido del huesped");
			txtApellido.requestFocus();
			return false;
		}
		if (txtFechaN.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Por favor escoja la fecha de nacimiento del huesped");
			txtFechaN.requestFocus();
			return false;
		}
		if (txtTelefono.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese el telefono del huesped");
			txtTelefono.requestFocus();
			return false;
		}
		return true;
	}

	private Huesped convertirDatosHuesped() {
		LocalDate fechaNacimiento = txtFechaN.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new Huesped(txtNombre.getText(), txtApellido.getText(), fechaNacimiento,
				txtNacionalidad.getSelectedItem().toString(), Integer.parseInt(txtTelefono.getText()), this.reserva);
	}

	private void limpiarFormulario() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtFechaN.setDate(null);
		txtNacionalidad.setSelectedIndex(0);
		txtTelefono.setText("");
		txtNreserva.setText("");
	}
	
	private void obtenerHuespedEditar() {
		this.huesped.setNombre(txtNombre.getText());
		this.huesped.setApellido( txtApellido.getText());
		this.huesped.setFechaNacimiento(txtFechaN.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		this.huesped.setNacionalidad(txtNacionalidad.getSelectedItem().toString());
		this.huesped.setTelefono(Integer.parseInt(txtTelefono.getText()));
	}

	private void mostrarHuespedEditar() {
		txtNombre.setText(this.huesped.getNombre());
		txtApellido.setText(this.huesped.getApellido());
		txtFechaN.setDate(
				Date.from(huesped.getFechaNacimiento().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		txtNacionalidad.setSelectedItem(huesped.getNacionalidad());
		txtTelefono.setText(String.valueOf(huesped.getTelefono()));
		txtNreserva.setText(String.valueOf(huesped.getReserva().getId()));
	}

}
