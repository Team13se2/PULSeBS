package team13.pulsbes.utils;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	  }

	public static final String GET_NUMBER_STUDENTS_ATTENDING = "/getNumberStudentsAttending";
	public static final String GET_STUDENT_LIST = "/getStudentList";
	public static final String GET_ALL_LECTURES = "/getAllLectures";
	public static final String GET_PAST_LECTURES = "/getPastLectures";
	public static final String GET_BOOKED_LECTURES = "/getBookedLectures";
	public static final String BOOK_LECTURE = "/bookLecture";
	public static final String CANCEL_LECTURE = "/cancelLecture";
	public static final String CANCEL_PRESENCE_LECTURE = "/cancelPresenceLecture";
	public static final String LOGIN = "/login";
	public static final String ADD_STUDENTS = "/addStudents";
	public static final String ADD_TEACHERS = "/addTeachers";
	public static final String ADD_COURSES = "/addCourses";
	public static final String ENROLL_STUDENTS = "/enrollStudents";
	public static final String ADD_PRESENCE = "/addPresence";
}
