package modelos;

import java.time.LocalDate;

public class Reserva {

	private int id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double valor;
	private String formaPago;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;

	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void SetFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

}
