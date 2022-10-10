package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelos.Huesped;

public class HuespedTableModel extends AbstractTableModel {
	
	private String[] HEADER = {"ID","NAME","LASTNAME","DATEBORN","NACIONALITY","CELL PHONE"};
	private Class<?>[] TYPE = {String.class,String.class,String.class,String.class,String.class,String.class};
	private List<Huesped> listaHuespeds = new ArrayList<>();

	@Override
	public int getRowCount() {
		return listaHuespeds.size();
	}

	@Override
	public int getColumnCount() {
		return HEADER.length;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return TYPE[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return HEADER[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch (columnIndex) {
		case 0:
			return listaHuespeds.get(rowIndex).getId();
		case 1:
			return listaHuespeds.get(rowIndex).getNombre();
		case 2:
			return listaHuespeds.get(rowIndex).getApellido();
		case 3:
			return listaHuespeds.get(rowIndex).getFechaNacimiento();
		case 4:
			return listaHuespeds.get(rowIndex).getNacionalidad();
		case 5:
			return listaHuespeds.get(rowIndex).getTelefono();

		}
		
		return null;
	}
	
	public void setListaHuespeds(List<Huesped> listaHuespeds) {
		this.listaHuespeds = listaHuespeds;
	}

	
}

