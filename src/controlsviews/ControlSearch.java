package controlsviews;

import ui.HuespedTableModel;
import views.Busqueda;

public class ControlSearch extends Busqueda{
	
	public ControlSearch () {
		super();
		this.agregarDatosTablaHuesped();
	}
	
	private void agregarDatosTablaHuesped() {
		HuespedTableModel model = (HuespedTableModel) tbHuespedes.getModel();
		tbHuespedes.setModel(model);
		
	}
	

	public static void main(String[] args) {
		new ControlSearch().setVisible(true);
	}
}
