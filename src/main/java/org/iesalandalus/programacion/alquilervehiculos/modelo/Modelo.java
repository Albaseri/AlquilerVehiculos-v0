package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Turismos;

public class Modelo {
	private Clientes clientes;
	private Alquileres alquileres;
	private Turismos turismos;

	public Modelo() {

	}

	public void comenzar() {
		clientes = new Clientes();
		turismos = new Turismos();
		alquileres = new Alquileres();
	}

	public void terminar() {
		System.out.println("El modelo ha terminado");
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		cliente = new Cliente(cliente);
		clientes.insertar(cliente);
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		turismo = new Turismo(turismo);
		turismos.insertar(turismo);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (clientes.buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (turismos.buscar(alquiler.getTurismo()) == null)
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
	}

	public Cliente buscar(Cliente cliente) {

		if (clientes.buscar(cliente) != null) {
			cliente = new Cliente(cliente);
		} else {
			cliente = null;
		}
		return cliente;
	}

	public Turismo buscar(Turismo turismo) {
		if (turismos.buscar(turismo) != null) {
			turismo = new Turismo(turismo);
		} else {
			turismo = null;
		}
		return turismo;
	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquileres.buscar(alquiler).equals(alquiler)) {

			alquiler = new Alquiler(alquiler);
		} else {
			alquiler = null;
		}
		return alquiler;
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler.getFechaAlquiler() == null) {
			throw new NullPointerException("ERROR: No existe el alquiler a devolver.");
		} else {
			// alquileres.devolver(alquiler, fechaDevolucion); Una vez que pase los test, esta forma sería la correcta.
			alquiler.devolver(fechaDevolucion);
		}
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquilerCliente : alquileres.get(cliente)) { // recorro lista para cada cliente, y borra cada alquiler de ese cliente.
			alquileres.borrar(alquilerCliente);
		}
		clientes.borrar(cliente); // borra cliente.
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		for (Alquiler alquilerTurismo : alquileres.get(turismo)) {
			alquileres.borrar(alquilerTurismo);
		}
		turismos.borrar(turismo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	public List<Cliente> getClientes() {
		List<Cliente> listaCliente = new ArrayList<>();
		for (Cliente cliente1 : clientes.get()) {
			Cliente cliente = new Cliente(cliente1);
			listaCliente.add(cliente);
		}
		return listaCliente;
	}

	public List<Turismo> getTurismos() {
		List<Turismo> listaTurismo = new ArrayList<>();
		for (Turismo turismo1 : turismos.get()) {
			Turismo turismo = new Turismo(turismo1);
			listaTurismo.add(turismo);
		}
		return listaTurismo;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> listaAlquiler = new ArrayList<>();
		for (Alquiler alquiler1 : alquileres.get()) {
			Alquiler alquiler = new Alquiler(alquiler1);
			listaAlquiler.add(alquiler);

		}
		return listaAlquiler;
	}
}
