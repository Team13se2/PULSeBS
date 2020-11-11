package team13.pulsbes.exception;

public class InvalidLectureException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4602870083679331434L;

	public InvalidLectureException(String error) {
		super(error);
	}
}