package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Opcion {
	SALIR("Salir"), 
	INSERTAR_CLIENTE("1.Insertar cliente"), 
	INSERTAR_TURISMO("2.Insertar turismo"),
	INSERTAR_ALQUILER("3.Insertar alquiler"), 
	BUSCAR_CLIENTE("4.Buscar cliente"), 
	BUSCAR_TURISMO("5.Buscar turismo"),
	BUSCAR_ALQUILER("6.Buscar alquiler"), 
	MODIFICAR_CLIENTE("7.Modificar cliente"), 
	DEVOLVER_ALQUILER("8.Devolver alquiler"),
	BORRAR_CLIENTE("9.Borrar cliente"), 
	BORRAR_TURISMO("10.Borrar turismo"), 
	BORRAR_ALQUILER("11.Borrar alquiler"),
	LISTAR_CLIENTES("12.Listar clientes"), 
	LISTAR_TURISMOS("13.Listar turismos"), 
	LISTAR_ALQUILERES("14.Listar alquileres"),
	LISTAR_ALQUILERES_CLIENTE("15.Listar alquileres de un cliente"),
	LISTAR_ALQUILERES_TURISMO("16.Listar alquileres de un turismo");

	private String texto; 

	private Opcion(String texto) {
		this.texto = texto;
	}

	private boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal < values().length; // con values length obtengo la cantidad de elementos de Opcion
	}

	public Opcion get(int ordinal) {
		if (esOrdinalValido(ordinal)) {
			return get(ordinal);
		} else {
			throw new IllegalArgumentException("El ordinal no es válido");
		}
	}
	@Override 
	public String toString() {
		return String.format("%s",texto);
	}
}
