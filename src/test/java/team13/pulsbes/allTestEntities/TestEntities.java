package team13.pulsbes.allTestEntities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.CourseDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
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
		
	}
}
