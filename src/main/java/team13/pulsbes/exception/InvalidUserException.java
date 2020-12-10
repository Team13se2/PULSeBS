package team13.pulsbes.exception;

public class InvalidUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2105846567528367240L;
	public InvalidUserException(String error) {
		super(error);
	}
}
