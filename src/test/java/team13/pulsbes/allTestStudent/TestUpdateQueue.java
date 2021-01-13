package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.NotificationServiceImpl;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestUpdateQueue {

	StudentServiceImpl studentService;
	NotificationServiceImpl notificationService;
	StudentRepository sr;
	LectureRepository lr;
	String bookingSuccess = "The lecture was correctly booked";    
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
	void testUpdateQueue() throws InvalidStudentException, InvalidLectureException {
		Lecture l = new Lecture();
		Teacher t = new Teacher();
		List<Lecture> bookedL = new ArrayList<>();
		l.setBookable(true);
		l.setAvailableSeat(40);
		Student s = new Student();
		s.setBookedLectures(bookedL);
		s.setEmail("test@gmail.com");
		l.setSubjectName("test");
		l.addStartTime(2021, 1, 1, 1, 0);
		l.setRoomName("aulatest");
		l.setTeacher(t);
		t.setName("test");
		t.setSurname("testsur");
		s.setId("1");
		l.getQueue().put(s.getId(),1);
		Optional<Lecture> ol = Optional.of(l);
		when(lr.findById(any())).thenReturn(ol);
		when(lr.save(any())).thenReturn(l);
		Optional<Student> os = Optional.of(s);
		when(sr.findById(any())).thenReturn(os);
		when(sr.getOne(any())).thenReturn(s);
		assertTrue(studentService.updatequeue(1));
		doNothing().when(notificationService).sendMessage(any(), any(), any());
	}
	
	@Test
	void testUpdateQueue2() {
		assertThrows(InvalidStudentException.class,()->studentService.delayedbookLecture(1, "-1"));
	}
	@Test
	void testUpdateQueue3() {
		Optional<Lecture> os= Optional.empty();
		when(lr.findById(any())).thenReturn(os);
		assertThrows(InvalidLectureException.class,()->studentService.delayedbookLecture(1, "2"));
	}
	@Test
	void testUpdateQueue4() throws InvalidLectureException, InvalidStudentException {
		Lecture l = new Lecture();
		Optional<Lecture> os= Optional.of(l);
		l.setBookable(false);
		when(lr.findById(any())).thenReturn(os);
		assertEquals("Lecture was canceled", studentService.delayedbookLecture(1, "2"));
	}
}
