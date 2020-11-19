package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;
import team13.pulsbes.services.StudentService;

class TestGetAllLectures {

	StudentService studentService;
	StudentRepository studentRepository;
	
	@BeforeEach
	void setUp() {
		studentRepository = mock(StudentRepository.class);
		studentService = new StudentServiceImpl();
		
	}
	
	@Test
	void testGetAllLectures() {
		assertThrows(InvalidStudentException.class,()->studentService.getAllLectures("-1"));
	}
	@Test
	void testGetAllLectures2() {
		Student s = new Student("1","name","surname");
		List<Course> courses = new ArrayList<>();
		Course c = new Course();
		Lecture l = new Lecture();
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		c.setLectures(lectures);
		courses.add(c);
		s.setCourses(courses);
		when(studentRepository.getOne(any())).thenReturn(s);
		
	}
}
