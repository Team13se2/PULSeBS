package team13.pulsbes.allTestBookingManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.BookingManagerServiceImpl;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class TestGetContactReport {

	LectureRepository lectureRepository;
	StudentRepository studentRepository;
	ModelMapper modelMapper;
	BookingManagerServiceImpl bookingService;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		studentRepository = mock(StudentRepository.class);
		modelMapper = mock(ModelMapper.class);
		bookingService = new BookingManagerServiceImpl();
		bookingService.addLectureRepository(lectureRepository);
		bookingService.addModelMapper(modelMapper);
		bookingService.addStudentRepository(studentRepository);
	}	
	@Test
	void testGetContactReport() throws InvalidStudentException{
		assertThrows(InvalidStudentException.class, ()->bookingService.getContactReport("-1"));
	}	
	@Test
	void testGetContactReport2() throws InvalidStudentException{
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class, ()->bookingService.getContactReport("-1"));
	}
	@Test
	void testGetContactReport3() throws InvalidStudentException, InvalidCourseException{
		Student s = new Student();
		Course c = new Course();
		Lecture l = new Lecture();
		Lecture l1 = new Lecture();
		List<Student> presents = new ArrayList<>();
		StudentDTO sDto = new StudentDTO();
		List<Lecture> lectures = new ArrayList<>();
		c.setCode("test");
		l.setCode("test");
		l1.setCode("test1");
		l.addStartTime(2019, 1, 1, 1, 0);
		l.setStudentsPresent(presents);
		presents.add(s);
		c.setName("test");
		lectures.add(l);
		s.addCourse(c);
		s.addLecturePresence(l);
		when(studentRepository.existsById(any())).thenReturn(true);
		when(studentRepository.getOne(any())).thenReturn(s);
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(modelMapper.map(any(), any())).thenReturn(sDto);
		assertEquals(sDto,bookingService.getContactReport("1").get(0));
	}
}
