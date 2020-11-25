package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

import java.util.Optional;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;
class TestDeleteLecture {

	@Autowired
	StudentServiceImpl studentService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	LectureRepository lectureRepository;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		studentRepository = mock(StudentRepository.class);
		studentService = new StudentServiceImpl();
		studentService.addLectureRepo(lectureRepository);
		studentService.addStudentRepo(studentRepository);
	}
	
	@Test
	void testDeleteLecture() {
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class, ()->studentService.deleteLecture("1", "1"));
	}
	
	@Test
	void testDeleteLecture2() {
		when(studentRepository.existsById(any())).thenReturn(true);
		when(lectureRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidLectureException.class, ()->studentService.deleteLecture("1", "1"));
	}
	@Test
	void testDeleteLecture3() throws InvalidLectureException, InvalidStudentException {
		Student st = new Student("1","name" , "surname");
		Optional<Student> s = Optional.of(st);
		
		Lecture l = new Lecture();
		Lecture lWrong = new Lecture();
		s.get().addBookLecture(lWrong);
		when(studentRepository.existsById(any())).thenReturn(true);
		when(lectureRepository.existsById(any())).thenReturn(true);
		when(studentRepository.findById(any())).thenReturn(s);
		when(lectureRepository.getOne(any())).thenReturn(l);
		assertEquals("Student doesn't have this lecture",studentService.deleteLecture("1", "1"));		
	}
	@Test
	void testDeleteLecture4() throws InvalidLectureException, InvalidStudentException {
		Student st = new Student("1","name" , "surname");
		Optional<Student> s = Optional.of(st);		
		Lecture l = new Lecture();
		Lecture lWrong = new Lecture();
		s.get().addBookLecture(lWrong);
		s.get().addBookLecture(l);
		when(studentRepository.existsById(any())).thenReturn(true);
		when(lectureRepository.existsById(any())).thenReturn(true);
		when(studentRepository.findById(any())).thenReturn(s);
		when(lectureRepository.getOne(any())).thenReturn(l);
		assertEquals("Lecture deleted",studentService.deleteLecture("1", "1"));
		
	}
}
