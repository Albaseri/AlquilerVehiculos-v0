package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Turismos {
	public List<Turismo> coleccionTurismos;

	// constructor por defecto
	public Turismos() {
		coleccionTurismos = new ArrayList<>();
	}

//método get
	public List<Turismo> get() {
		return coleccionTurismos;
	}

//método getCantidad
	public int getCantidad() {
		int cantidadTurismos = 0;
		for (Turismo coleccionTurismo : coleccionTurismos) {
			cantidadTurismos++;
		}
		return cantidadTurismos;
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
		}
		if (!coleccionTurismos.contains(turismo)) {
			coleccionTurismos.add(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula."); 
		}
	}

	public Turismo buscar(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
		}
		if (coleccionTurismos.contains(turismo)) {
		} else {
			turismo = null;
		}
		return turismo;
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
		}
		if (coleccionTurismos.contains(turismo)) {
			coleccionTurismos.remove(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula."); 
		}
	}
}