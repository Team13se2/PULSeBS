package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

class TestGetAllLectures {

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
	void testGetAllLectures() {
		assertThrows(InvalidStudentException.class,()->studentService.getAllLectures("-1"));
	}
	@Test
	void testGetAllLectures2() throws InvalidStudentException, InvalidCourseException {
		Student s = new Student("1","name","surname");
		Course c = new Course();
		Lecture l = new Lecture();
		l.setId("1");
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		c.setLectures(lectures);
		s.addCourse(c);
		when(studentRepository.getOne(any())).thenReturn(s);
		List<LectureDTO> ret = new ArrayList<>();
		LectureDTO lDto = new LectureDTO();
		lDto.setId("1");
		ret.add(lDto);
		studentService.getAllLectures("1");
		when(modelMapper.map(any(), any())).thenReturn(lDto);
		assertEquals(ret.get(0).getId(), studentService.getAllLectures("1").get(0).getId());
	}
}
