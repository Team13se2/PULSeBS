package team13.pulsbes.utils;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	  }

	public static final String GET_NUMBER_STUDENTS_ATTENDING = "/getNumberStudentsAttending";
	public static final String GET_NUMBER_STUDENTS_PRESENT = "/getNumberStudentsPresent";
	public static final String GET_STUDENT_LIST = "/getStudentList";
	public static final String GET_ALL_LECTURES = "/getAllLectures";
	public static final String GET_PAST_LECTURES = "/getPastLectures";
	public static final String GET_CURRENT_LECTURES = "/getCurrentLectures";
	public static final String GET_DAILY_LECTURES = "/getLecturesOfTheDay";
	public static final String GET_BOOKED_LECTURES = "/getBookedLectures";
	public static final String GET_WAITING_LECTURES = "/getWaitingLectures";
	public static final String BOOK_LECTURE = "/bookLecture";
	public static final String CANCEL_LECTURE = "/cancelLecture";
	public static final String CANCEL_PRESENCE_LECTURE = "/cancelPresenceLecture";
	public static final String LOGIN = "/login";
	public static final String ADD_STUDENTS = "/addStudents";
	public static final String ADD_TEACHERS = "/addTeachers";
	public static final String ADD_COURSES = "/addCourses";
	public static final String ENROLL_STUDENTS = "/enrollStudents";
	public static final String ADD_LECTURES = "/addLectures";
	public static final String ADD_PRESENCE = "/addPresence";
	public static final String GET_CONTACT_REPORT_STUDENT = "/getContactReportStudent";
	public static final String GET_CONTACT_REPORT_STUDENT_CSV = "/getContactReportStudentCSV";
	public static final String GET_CONTACT_REPORT_STUDENT_PDF = "/getContactReportStudentPDF";	
	public static final String GET_CONTACT_REPORT_TEACHER_CSV = "/getContactReportTeacherCSV";
	public static final String GET_CONTACT_REPORT_TEACHER_PDF = "/getContactReportTeacherPDF";
	public static final String REMOVE_LECTURES = "/removeLectures";
	public static final String READD_LECTURES = "/readdLectures";
	public static final String STUDENT_TUTORIAL= "/studentTutorial";
	public static final String TEACHER_TUTORIAL= "/teacherTutorial";
	public static final String REMOVE_HOLIDAYS= "/removeHolidays";
	public static final String MODIFY_SCHEDULE= "/modifySchedule";
	public static final String GET_SCHEDULE= "/getSchedule";
}
