package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestGetWaitingLectures {
	StudentServiceImpl studentService;
	StudentRepository studentRepository;
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		studentRepository = mock(StudentRepository.class);
		modelMapper = mock(ModelMapper.class);
		studentService = new StudentServiceImpl();
		studentService.addStudentRepo(studentRepository);
		studentService.addModelMapper(modelMapper);
	}
	
	@Test
	void testGetWaitingLectures() {
		assertThrows(InvalidStudentException.class,()->studentService.getWaitingLectures("-1"));

	}
	@Test
	void testGetWaitingLectures2() {
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class,()->studentService.getWaitingLectures("2"));
	}
	@Test 
	void testGetWaitingLectures3() throws InvalidStudentException{
		Student s = new Student();
		Lecture l = new Lecture();
		LectureDTO lDto = new LectureDTO();
		l.setBookable(true);
		l.addStartTime(2022, 1, 1, 12, 0);
		s.getWaitingLectures().add(l);
		when(modelMapper.map(any(), any())).thenReturn(lDto);
		when(studentRepository.existsById(any())).thenReturn(true);
		when(studentRepository.getOne(any())).thenReturn(s);
		assertEquals(lDto,studentService.getWaitingLectures("1").get(0));
	}
}
