package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.Optional;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.NotificationServiceImpl;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestBookLecture {

	StudentServiceImpl studentService;
	NotificationServiceImpl notificationService;
	StudentRepository sr;
	LectureRepository lr;
	String bookingSuccess = "The lecture was corrrectly booked";    
	String bookingFailure = "The lecture has no more available seats, you will receive a mail if a spot opens up";
	@BeforeEach
	void setUp() {
		sr = mock(StudentRepository.class);
		lr = mock(LectureRepository.class);
		notificationService = mock(NotificationServiceImpl.class);
		studentService = new StudentServiceImpl();
		studentService.addLectureRepo(lr);
		studentService.addStudentRepo(sr);
		studentService.addNotificationService(notificationService);
	}
	
	@Test
	void testBookLecture() {
		when(lr.getOne(any())).thenReturn(null);
		assertThrows(InvalidLectureException.class, ()-> studentService.bookLecture("1", "2"));
	}
	@Test
	void testBookLecture2() throws InvalidLectureException,InvalidStudentException {
		Lecture l = new Lecture();
		l.setAvailableSeat(0);
		Optional<Lecture> ol = Optional.of(l);
		when(lr.findById(any())).thenReturn(ol);
		assertEquals(bookingFailure,studentService.bookLecture("1", "2"));
	}
	@Test
	void testBookLecture3() throws InvalidLectureException,InvalidStudentException {
		Lecture l = new Lecture();
		l.setAvailableSeat(1);
		Student s = new Student("1","test","test");
		s.setEmail("fake@gmail.com");
		l.setSubjectName("test");		
		Optional<Lecture> ol = Optional.of(l);
		when(lr.findById(any())).thenReturn(ol);
		when(sr.getOne(any())).thenReturn(s);
		doNothing().when(notificationService).sendMessage(isA(String.class), isA(String.class), isA(String.class));
		assertEquals(bookingSuccess,studentService.bookLecture("1", "2"));
	}
	
}
