package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestAddPresence {

	LectureRepository lectureRepository;
	TeacherServiceImpl teacherService;
	StudentRepository studentRepository;
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		studentRepository = mock(StudentRepository.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
		teacherService.addStudentRepository(studentRepository);
	}
	
	@Test
	void testAddPresence1() {
		Optional<Lecture> ol = Optional.empty();
		when(lectureRepository.findById(any())).thenReturn(ol);
		assertThrows(InvalidLectureException.class, ()->teacherService.addPresence(1, "1"));
	}

	
	@Test
	void testAddPresence2() {
		Lecture l = new Lecture();
		Optional<Lecture> ol = Optional.of(l);
		Optional<Student> os = Optional.empty();
		when(lectureRepository.findById(any())).thenReturn(ol);
		when(studentRepository.findById(any())).thenReturn(os);
		assertThrows(InvalidStudentException.class, ()->teacherService.addPresence(1, "1"));
		
	}
	@Test
	void testAddPresence3() throws InvalidLectureException, InvalidStudentException {
		Lecture l = new Lecture();
		Student s = new Student();
		List<Student> students = new ArrayList<>();
		l.setStudentsPresent(students);
		students.add(s);
		Optional<Lecture> ol = Optional.of(l);
		Optional<Student> os = Optional.of(s);
		when(lectureRepository.findById(any())).thenReturn(ol);
		when(studentRepository.findById(any())).thenReturn(os);
		assertEquals("Presence added",teacherService.addPresence(1, "1"));
	}
}
