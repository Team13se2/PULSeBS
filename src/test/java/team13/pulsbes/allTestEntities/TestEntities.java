package team13.pulsbes.allTestEntities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.CourseDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;

class TestEntities {

	@Test
	void testCourse() throws InvalidStudentException {
		Course c = new Course();
		Teacher t = new Teacher();
		List<Student> students = new ArrayList<>();
		Student s =  new Student("id", "name", "surname");
		c.setId("1");
		c.setName("analisi");
		c.setTeacher(t);
		assertEquals("1",c.getId());
		assertEquals("analisi",c.getName());
		assertEquals(t,c.getTeacher());
		c.newStudentEnrolled(s);
		assertEquals(1, c.getStudents().size());
		c.studentRemove(s);
		assertEquals(0, c.getStudents().size());
	}
	@Test
	void testCourseExceptions() {
		Course c = new Course();
		assertThrows(InvalidStudentException.class,() -> c.newStudentEnrolled(null));
		assertThrows(InvalidStudentException.class,() -> c.studentRemove(null));
	}
	@Test
	void testLecture() {
		Lecture l = new Lecture();
		List<Student> students = new ArrayList<>(); 
		Course c = new Course();
		Teacher t = new Teacher();
		l.setId("1");
		l.setStartTime(null);
		l.setEndTime(null);
		l.setSubjectName("test");
		l.setLectureType("lab");
		l.setSurnameString("test");
		l.setAvailableSeat(10);
		l.setTotalSeat(100);
		l.setRoomName("aula");
		l.setStudents(students);
		l.setCourse(c);
		l.setTeacher(t);
		assertEquals(l.getId(),"1");
		assertNull(l.getStartTime());
		assertNull(l.getEndTime());
		assertEquals(l.getLectureType(),"lab");
		assertEquals(l.getSurnameString(),"test");
		assertEquals(l.getAvailableSeat(),10);
		assertEquals(l.getTotalSeat(),100);
		assertEquals(l.getRoomName(),"aula");
		assertEquals(l.getStudents(),students);
		assertEquals(l.getCourse(), c);
		assertEquals(l.getTeacher(), t);
	}
	@Test
	void testLectureExceptions() {
		Lecture l = new Lecture();
		assertThrows(InvalidStudentException.class, () -> l.addStudentAttending(null));
		assertThrows(InvalidStudentException.class, () -> l.removeStudentAttending(null));
	}
	@Test
	void testStudent() {
		Student s = new Student("1","test","testsur");
		List<Course> courses = new ArrayList<>(); 
		s.setEmail("email");
		s.setCourses(courses);
		assertEquals("1", s.getId());
		assertEquals("test", s.getName());
		assertEquals("testsur", s.getSurname());
		assertEquals("email", s.getEmail());
		assertEquals(s.getCourses(), courses);
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
		t.setId("1");
		t.setName("test");
		t.setSurname("testsur");
		t.setEmail("email");
		t.setCourses(courses);
		t.setLectures(lectures);
		assertEquals("1", 		t.getId());
		assertEquals("test", 	t.getName());
		assertEquals("testsur", t.getSurname());
		assertEquals("email", 	t.getEmail());
		assertEquals(courses, 	t.getCourses());
		assertEquals(lectures, 	t.getLectures());
	}
	
}
