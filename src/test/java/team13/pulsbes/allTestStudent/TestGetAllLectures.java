package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
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
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestGetAllLectures {

	StudentServiceImpl studentService;
	StudentRepository studentRepository;
	LectureRepository lectureRepository;
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		studentRepository = mock(StudentRepository.class);
		modelMapper = mock(ModelMapper.class);
		studentService = new StudentServiceImpl();
		studentService.addStudentRepo(studentRepository);
		studentService.addLectureRepo(lectureRepository);
		studentService.addModelMapper(modelMapper);
	}
	
	@Test
	void testGetAllLectures() {
		assertThrows(InvalidStudentException.class,()->studentService.getAllLectures("-1"));
	}
	@Test
	void testGetAllLectures2() {
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class,()->studentService.getAllLectures("2"));
	}
	@Test
	void testGetAllLectures3() throws InvalidStudentException, InvalidCourseException {
		Student s = new Student("1","name","surname");
		Course c = new Course();
		c.setCode("X");
		Lecture l = new Lecture();
		l.setBookable(true);
		l.setId(1);
		Calendar tmp = Calendar.getInstance();
		tmp.add(Calendar.DAY_OF_MONTH, 1);
		l.addStartTime(tmp.get(Calendar.YEAR), tmp.get(Calendar.MONTH), tmp.get(Calendar.DATE), 0, 0);
		l.setCode("X");
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		//c.setLectures(lectures);
		s.addCourse(c);
		when(studentRepository.getOne(any())).thenReturn(s);
		when(studentRepository.existsById(any())).thenReturn(true);
		List<LectureDTO> ret = new ArrayList<>();
		LectureDTO lDto = new LectureDTO();
		lDto.setId(1);
		ret.add(lDto);
		studentService.getAllLectures("1");
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(modelMapper.map(any(), any())).thenReturn(lDto);
		assertEquals(ret.get(0).getId(), studentService.getAllLectures("1").get(0).getId());
	}
}
