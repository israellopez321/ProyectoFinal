package exceptions;

/**
 * Thrown when a requested character cannot be found in a collection or registry.
 */
public class CharacterNotFoundException extends Exception {

	public CharacterNotFoundException() {
		super();
	}

	public CharacterNotFoundException(String message) {
		super(message);
	}

	public CharacterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
