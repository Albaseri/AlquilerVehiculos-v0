package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class Clientes {
	public List<Cliente> coleccionClientes;

	// constructor por defecto
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	// método get
	public List<Cliente> get() {
		return coleccionClientes;
	}

	// método getCantidad
	public int getCantidad() {
		int cantidadClientes = 0;
		for (Cliente coleccionCliente : coleccionClientes) {
			cantidadClientes++;
		}
		return cantidadClientes;  //se podría hacer directamente con return coleccionClientes.size(), así se optimiza código
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			cliente = null;
		}
		return cliente;
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		if (nombre != null && !nombre.isBlank()) {
			cliente.setNombre(nombre);
		}

		if (telefono != null && !telefono.isBlank()) {
			cliente.setTelefono(telefono);

		}
		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}
}
