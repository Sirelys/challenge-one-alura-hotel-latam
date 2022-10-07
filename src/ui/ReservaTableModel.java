package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelos.Reserva;

public class ReservaTableModel extends AbstractTableModel {

	private String[]HEADER = {"ID", "FECHAENTRADA","FECHASALIDA", "VALOR","FORMAPAGO"};
	private Class<?>[] TYPE= {String.class,String.class,String.class,String.class,String.class};
	private List<Reserva> listaReservas= new ArrayList<>();
	
	@Override
	public int getRowCount() {
		return listaReservas.size();
	
	}

	@Override
	public int getColumnCount() {
		return HEADER.length;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return TYPE [columnIndex];
	}
	@Override
	public String getColumnName(int column) {
		return HEADER [column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

}
