package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {
	public List<Alquiler> coleccionAlquileres;

	// constructor por defecto
	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

//método get
	public List<Alquiler> get() {
		return coleccionAlquileres;
	}

	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) { // todos los alquileres que tenga el cliente se añaden a la nueva lista (listaAlq)
				listaAlquileres.add(alquiler);
			} 
		}
		return listaAlquileres;
	}

	public List<Alquiler> get(Turismo turismo) {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) { // todos los alquileres que tenga el turismo se añaden a la  nueva lista (listaAlq)
				listaAlquileres.add(alquiler);
			}
		}
		return listaAlquileres;
	}

	// método getCantidad
	public int getCantidad() {
		int cantidadAlquileres = 0;
		for (Alquiler coleccionAlquiler : coleccionAlquileres) {
			cantidadAlquileres++;
		}
		return cantidadAlquileres;
	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			// con equal compruebo si el el objeto cliente y turismo pasados son los mismos
			// que los objetors y turismos de alquiler.
			if (alquiler.getFechaDevolucion() == null) {

				if (alquiler.getCliente().equals(cliente)) {
					// compruebo si el cliente de alquiler es el mismo que le estamos pasando. El
					// alquiler sin devolver es del cliente que le hemos pasado.
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getTurismo().equals(turismo)) {
					// compruebo si el turismo de alquiler es el mismo que le estamos pasando. El
					// alquiler sin devolver es del turismo que le hemos pasado.
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}

				// alquileres sin devolver
			} else {
				// alquileres devueltos
				if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					// compruebo si el cliente de alquiler es el mismo que le estamos pasando. El
					// alquiler sin devolver es del cliente que le hemos pasado.
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getTurismo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					// compruebo si el turismo de alquiler es el mismo que le estamos pasando. El
					// alquiler sin devolver es del turismo que le hemos pasado.
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			alquiler.devolver(fechaDevolucion);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

//preguntar
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		if (coleccionAlquileres.indexOf(alquiler)==-1) { //si es diferente de -1 significa que lo contiene
			alquiler = null; // si no lo contiene, lo inicializa a null
		}else {
			coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler));
		}
		return alquiler;
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}
}
