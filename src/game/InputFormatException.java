package game;

// Thrown when an illegal row/column is provided or when input is wrong length
public class InputFormatException extends Exception {
	public InputFormatException(String message) {
		super(message);
	}
}
