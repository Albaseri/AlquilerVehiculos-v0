package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Consola {

	private static final String PATRON_FECHA = "dd/mm/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {
	}

	public void mostrarCabecera(String mensaje) {
		StringBuilder cabecera = new StringBuilder();
		cabecera.append("-");
		System.out.println(mensaje);
		for (int i = 0; i < mensaje.length(); i++) {
			System.out.print(cabecera);
		}
	}

	public void mostraMenu() {
		mostrarCabecera("Plataforma para alquileres de coches");
	}

	private String leerCadena(String mensaje) {
		return mensaje;

	}

	private Integer leerEntero(String mensaje) {
		return null;

	}

	private LocalDate leerFecha(String mensaje) {
		return null;
	}

	public Opcion elegirOpcion() {
		return null;
	}

	public Cliente leerCliente() {
		return null;

	}

	public Cliente leerClienteDni() {
		return null;

	}

	public String leerNombre() {
		return null;

	}

	public String leerTelefono() {
		return null;

	}

	public String leerTurismo() {
		return null;

	}

	public Turismo leerTurismoMatricula() {
		return null;

	}

	public Alquiler leerAlquiler() {
		return null;

	}

	public LocalDate leerFechaDevolucion() {
		return null;

	}

}
