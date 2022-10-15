package controlsviews;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import views.MenuUsuario;
import views.ReservasView;

public class ControlMenuUsuario extends MenuUsuario {

	private static final long serialVersionUID = 1L;

	public ControlMenuUsuario() {
		super();
		init();
	}

	private void init() {
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ControlReserva reservas = new ControlReserva();
				reservas.setVisible(true);
				dispose();
			}
		});
	}

}
