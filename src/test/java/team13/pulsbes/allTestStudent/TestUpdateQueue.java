package team13.pulsbes.allTestStudent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
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
	void testUpdateQueue() {
		Lecture l = new Lecture();
		Student s = new Student();
		s.setId("1");
		l.getQueue().put(s.getId(),1);
		Optional<Lecture> ol = Optional.of(l);
		when(lr.findById(any())).thenReturn(ol);
		
	}

}
