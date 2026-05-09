package exceptions;

public class CharacterNotFoundException extends Exception {
	
	public CharacterNotFoundException(String nombre) {
		super("Personaje no encontrado: " + nombre);
		this.nombre = nombre;
	}

	public String getNombreBuscado() {
		return nombreBuscado;
	}
	
}
