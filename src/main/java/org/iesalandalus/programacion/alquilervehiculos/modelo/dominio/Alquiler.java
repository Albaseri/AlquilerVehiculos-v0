package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;

	private Cliente cliente;
	private Turismo turismo;

	//constructor con parámetros
	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {
		setCliente(cliente);
		setTurismo(turismo);
		setFechaAlquiler(fechaAlquiler);
	}

	// constructor copia
	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		// nuevas instancias llamando a sus constructores copia.
		this.cliente = new Cliente(alquiler.getCliente());
		this.turismo = new Turismo(alquiler.getTurismo());
		setFechaAlquiler(alquiler.getFechaAlquiler());
		setFechaDevolucion(alquiler.getFechaDevolucion());
	}
	//getters and setters
	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}

	public Turismo getTurismo() {
		return turismo;
	}

	private void setTurismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		}
		this.turismo = turismo;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if (fechaAlquiler.isAfter(LocalDate.now())) { // now para obtener fecha actual
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isBefore(getFechaAlquiler()) || fechaDevolucion.isEqual(getFechaAlquiler())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now())) { // now para obtener fecha actual
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}

	public int getPrecio() {
		int precio = 0;
		if (this.fechaDevolucion != null) {
			int numDias = (int) ChronoUnit.DAYS.between(getFechaAlquiler(), getFechaDevolucion()); // between devuelve diferencia en días. (int) para convertir cadena long a int.																			
			int factorCilindrada = turismo.getCilindrada() / 10;
			precio = (PRECIO_DIA + factorCilindrada) * numDias;
		} else {
			precio = 0;
		}
		return precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, turismo, fechaAlquiler);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(turismo, other.turismo)
				&& Objects.equals(fechaAlquiler, other.fechaAlquiler);
	}

	@Override
	public String toString() {
		String cadena = null;
		if (this.fechaDevolucion == null) {
			cadena = String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo,
					getFechaAlquiler().format(FORMATO_FECHA), "Aún no devuelto", getPrecio());
		} else {
			cadena = String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo, fechaAlquiler.format(FORMATO_FECHA),
					fechaDevolucion.format(FORMATO_FECHA), getPrecio());
		}
		return cadena;
	}
}
