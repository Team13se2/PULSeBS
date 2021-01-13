package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestEnrollStudent {

	OfficerService officerService;
	CourseRepository courseRepository;
	StudentRepository studentRepository;
	@BeforeEach
	void setUp() {
		courseRepository = mock(CourseRepository.class);
		studentRepository= mock(StudentRepository.class);
		officerService = new OfficerService();
		officerService.addCourseRepository(courseRepository);
		officerService.addStudentRepository(studentRepository);
	}
	
	@Test
	void testAddStudentList() throws InvalidCourseException, InvalidStudentException {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Enrollment.csv");
		Course c = new Course();
		Student s = new Student();
		when(courseRepository.getOne(any())).thenReturn(c);
		when(studentRepository.getOne(any())).thenReturn(s);
		when(studentRepository.save(any())).thenReturn(s);
		assertTrue(officerService.enrollStudent(f));
	}

}
