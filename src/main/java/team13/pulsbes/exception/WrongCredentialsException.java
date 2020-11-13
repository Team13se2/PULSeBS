package team13.pulsbes.exception;

public class WrongCredentialsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5820525090636683777L;

	public WrongCredentialsException(String error) {
		super(error);
	}
}
