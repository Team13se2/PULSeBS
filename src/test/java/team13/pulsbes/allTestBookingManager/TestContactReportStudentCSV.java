package team13.pulsbes.allTestBookingManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.BookingManagerServiceImpl;

class TestContactReportStudentCSV {

	BookingManagerServiceImpl bookingService;
	StudentRepository studentRepository;
	LectureRepository lectureRepository;
	ModelMapper modelMapper;
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		studentRepository = mock(StudentRepository.class);
		modelMapper = mock(ModelMapper.class);
		bookingService = new BookingManagerServiceImpl();
		bookingService.addStudentRepository(studentRepository);
		bookingService.addLectureRepository(lectureRepository);
		bookingService.addModelMapper(modelMapper);
	}
	
	@Test
	void testContatReportStudentCSV1() {
		assertThrows(InvalidStudentException.class, ()-> bookingService.getContactReportStudentCSV("-1") );
	}
	
	@Test
	void testContatReportStudentCSV2() {
		when(studentRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidStudentException.class, ()-> bookingService.getContactReportStudentCSV("2"));
	}
	@Test
	void testContatReportStudentCSV3() throws InvalidCourseException, InvalidStudentException, IOException {
		Student s = new Student();
		Course c = new Course();
		c.setCode("1");
		Lecture l = new Lecture();
		l.setCode("1");
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DATE, -1);
		l.addStartTime(start.get(Calendar.DATE), start.get(Calendar.MONTH), start.get(Calendar.DATE),start.get(Calendar.HOUR), start.get(Calendar.MINUTE));
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		s.addCourse(c);
		s.addLecturePresence(l);
		when(studentRepository.existsById(any())).thenReturn(true);
		when(studentRepository.getOne(any())).thenReturn(s);
		when(lectureRepository.findAll()).thenReturn(lectures);
		bookingService.getContactReportStudentCSV("2");
		assertNotNull(bookingService.getContactReportStudentCSV("2"));
	}
}
