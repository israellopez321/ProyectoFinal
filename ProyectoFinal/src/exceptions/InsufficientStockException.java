package exceptions;

/**
 * Thrown when attempting to remove or consume more items than available in inventory.
 */
public class InsufficientStockException extends Exception {

	public InsufficientStockException() {
		super();
	}

	public InsufficientStockException(String message) {
		super(message);
	}

}
