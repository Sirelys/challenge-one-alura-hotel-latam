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
		return null;
	}
	
	public void setListaHuespeds(List<Huesped> listaHuespeds) {
		this.listaHuespeds = listaHuespeds;
	}

}
