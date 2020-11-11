package team13.pulsbes.exception;

public class InvalidStudentException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137934791900137363L;

	public InvalidStudentException(String error) {
		super(error);
	}
}