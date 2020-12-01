package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestGetBookedLectures {
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
	void testGetBookedLectures() {
		assertThrows(InvalidStudentException.class,()->studentService.getBookedLectures("-1"));
	}
	@Test
	void testGetBookedLectures2() {
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class,()->studentService.getBookedLectures("2"));
	}

	@Test
	void testGetBookedLectures3() throws InvalidCourseException, InvalidStudentException {
		Student s = new Student("1","name","surname");		
		Lecture l = new Lecture();
		l.setId("1");
		l.addStartTime(2030, 1, 1, 0, 0);
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		when(studentRepository.getOne(any())).thenReturn(s);
		when(studentRepository.existsById(any())).thenReturn(true);
		List<LectureDTO> ret = new ArrayList<>();
		LectureDTO lDto = new LectureDTO();
		lDto.setId("1");
		ret.add(lDto);
		s.addBookLecture(l);
		studentService.getAllLectures("1");
		when(modelMapper.map(any(), any())).thenReturn(lDto);
		assertEquals(ret.get(0).getId(), studentService.getBookedLectures("1").get(0).getId());
	}
}
