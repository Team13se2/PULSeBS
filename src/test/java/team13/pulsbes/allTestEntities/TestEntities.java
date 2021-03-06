package team13.pulsbes.allTestEntities;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.*;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;

class TestEntities {

	@Test
	void testCourse() throws InvalidStudentException {
		Course c = new Course();
		Teacher t = new Teacher();
		List<Student> students = new ArrayList<>();
		Student s =  new Student("id", "name", "surname");
		c.setCode("1");
		c.setName("analisi");
		c.setTeacher(t);
		assertEquals("1",c.getCode());
		assertEquals("analisi",c.getName());
		assertEquals(t,c.getTeacher());
		c.newStudentEnrolled(s);
		assertEquals(1, c.getStudents().size());
		c.studentRemove(s);
		assertEquals(0, c.getStudents().size());
		c.setStudents(students);
		assertEquals(students, c.getStudents());
	}
	@Test
	void testCourseExceptions() {
		Course c = new Course();
		assertThrows(InvalidStudentException.class,() -> c.newStudentEnrolled(null));
		assertThrows(InvalidStudentException.class,() -> c.studentRemove(null));
	}
	@Test
	void testLecture() throws ParseException, InvalidStudentException {
		Lecture l = new Lecture();
		List<Student> students = new ArrayList<>(); 
		Course c = new Course();
		Teacher t = new Teacher();
		Student s = new Student("1",	"name","surname");
		
		l.setCode("1");
		l.setId(1);
		l.setStartTime(null);
		l.setEndTime(null);
		l.setSubjectName("test");
		l.setAvailableSeat(10);
		l.setTotalSeat(100);
		l.setRoomName("aula");
		l.setStudents(students);
		//l.setCourse(c);
		l.setTeacher(t);
		l.addStartTime(120, 10, 10, 10, 10);
		l.getStartTime2();
		l.addEndTime(120, 1, 10 , 10, 12);
		l.setStudentsPresent(students);
		l.addStudentPresent(s);
		l.setBookable(true);
		l.setDay("Mon");
		l.setIdschedule(1);
		
		assertEquals("1",l.getCode());
		assertEquals(10,l.getAvailableSeat());
		assertEquals(100,l.getTotalSeat());
		assertEquals("aula",l.getRoomName());
		assertEquals(l.getStudents(),students);
		//assertEquals(l.getCourse(), c);
		assertEquals(l.getTeacher(), t);
		assertEquals(l.getStartTime(),l.getStartTime());
		assertEquals(l.getEndTime(),l.getEndTime());
		assertEquals("test", l.getSubjectName());
		assertEquals(1, l.getStudentsPresent().size());
		assertEquals(0, l.getNrStudentsPresent());
		assertEquals(1, l.getId());
		assertEquals(true, l.getBookable());
		assertEquals("Mon", l.getDay());
		assertEquals(1, l.getIdschedule());
		assertEquals(1, l.getStudentsPresent().size());
		
		l.addStudentAttending(s);
		l.removeStudentAttending(s);
		assertEquals(1, l.getStudents().size());
	}
	@Test
	void testLectureExceptions() {
		Lecture l = new Lecture();
		assertThrows(InvalidStudentException.class, () -> l.addStudentAttending(null));
		assertThrows(InvalidStudentException.class, () -> l.removeStudentAttending(null));
	}
	@Test
	void testStudent() throws InvalidCourseException {
		Student s1 = new Student();
		Student s = new Student("1","test","testsur");
		Lecture lecture = new Lecture();
		lecture.setAvailableSeat(10);
		Lecture lecture2 = new Lecture();
		Lecture lecture3 = new Lecture();
		List<Course> courses = new ArrayList<>(); 
		List<Lecture>lectures = new ArrayList<Lecture>();
		lectures.add(lecture);
		s.setEmail("email");
		s.setCourses(courses);
		s.addBookLecture(lecture); s.addBookLecture(lecture2);
		s.removeBookedLecture(lecture);
		s.addLecturePresence(lecture3);
		s.addLecturePresence(lecture2);
		s.setId("1");
		s.setName("test");
		s.setSurname("testsur");
		s.setPsw("psw");
		s.setCity("testcity");
		s.setSSN("ssn");
		s.setBirthday("birth");
		s.setWaitingLectures(lectures);
		assertEquals("1", s.getId());
		assertEquals("test", s.getName());
		assertEquals("testsur", s.getSurname());
		assertEquals("email", s.getEmail());
		assertEquals("psw", s.getPsw());
		assertEquals(s.getCourses(), courses);
		assertEquals(1, s.getBookedLectures().size());
		assertEquals(2, s.getAttendedLectures().size());
		assertEquals("testcity", s.getCity());
		assertEquals("ssn", s.getSSN());
		assertEquals("birth", s.getBirthday());
		assertEquals(lectures, s.getWaitingLectures());
		Course c = new Course();
		s.addCourse(c);
		s.removeCourse(c);
		assertEquals(0,s.getCourses().size());
	}
	@Test
	void testStudentExceptions() {
		Student s = new Student("1","test","testsur");
		assertThrows(InvalidCourseException.class, () -> s.addCourse(null));
		assertThrows(InvalidCourseException.class, () -> s.removeCourse(null));
	}
	@Test
	void testTeacher() {
		Teacher t = new Teacher();
		List<Lecture> lectures = new ArrayList<>();
		List<Course> courses = new ArrayList<>(); 
		t.setNumber("1");
		t.setName("test");
		t.setSurname("testsur");
		t.setEmail("email");
		t.setCourses(courses);
		t.setLectures(lectures);
		assertEquals("1", t.getNumber());
		assertEquals("test", 	t.getName());
		assertEquals("testsur", t.getSurname());
		assertEquals("email", 	t.getEmail());
		assertEquals(courses, 	t.getCourses());
		assertEquals(lectures, 	t.getLectures());
	}
	@Test
	void testBookingManager() {
		BookingManager b = new BookingManager();
		b.setId("1");
		b.setName("test");
		b.setSurname("testsur");
		b.setEmail("test@gmail.com");
		b.setPsw("psw");
		assertEquals("1", 		b.getId());
		assertEquals("test", 	b.getName());
		assertEquals("testsur", b.getSurname());
		assertEquals("test@gmail.com", 	b.getEmail());
		assertEquals("psw",		b.getPsw());
	}
	@Test
	void testSupportOfficer() {
		SupportOfficer s = new SupportOfficer();
		s.setId("1");
		s.setName("test");
		s.setSurname("testsur");
		s.setEmail("test@gmail.com");
		s.setPsw("psw");
		assertEquals("1", 		s.getId());
		assertEquals("test", 	s.getName());
		assertEquals("testsur", s.getSurname());
		assertEquals("test@gmail.com", 	s.getEmail());
		assertEquals("psw",		s.getPsw());
	}
}
