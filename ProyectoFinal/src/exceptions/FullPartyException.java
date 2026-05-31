package exceptions;

/**
 * Thrown when attempting to add a character to a party that is already full.
 */
public class FullPartyException extends Exception {

	public FullPartyException() {
		super();
	}

	public FullPartyException(String message) {
		super(message);
	}

}
