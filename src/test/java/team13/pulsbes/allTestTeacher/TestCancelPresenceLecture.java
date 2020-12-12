package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.NotificationServiceImpl;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestCancelPresenceLecture {

	@Autowired
	TeacherServiceImpl teacherService;
	@Autowired
	NotificationServiceImpl notificationService;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	LectureRepository lectureRepository;
	
	@BeforeEach
	public void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		lectureRepository = mock(LectureRepository.class);
		notificationService = mock(NotificationServiceImpl.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
		teacherService.addRepo(teacherRepository);
		teacherService.addNotificationService(notificationService);
	}
	
	@Test
	void testCancelPresenceLecture() {
		assertThrows(InvalidLectureException.class, ()->teacherService.cancelPresenceLecture(-1, "-1") );
	}
	
	@Test
	void testCancelPresenceLecture2() throws InvalidStudentException, InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException{
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		Student s = new Student("1","test","testsur");
		Course c = new Course();
		//c.getLectures().add(l);
		//l.setCourse(c);
		s.setEmail("student1team13@gmail.com");
		l.addStudentAttending(s);
		l.setSubjectName("testsub");
		l.setTeacher(t);
		l.addStartTime(2019, 11, 31, 24, 0);
		t.getLectures().add(l);
		s.addBookLecture(l);
		Optional<Teacher> teacher = Optional.of(t);
		when(teacherRepository.findById(any())).thenReturn(teacher);
		when(lectureRepository.getOne(any())).thenReturn(l);
		doNothing().when(lectureRepository).delete(any());
		when(teacherRepository.save(any())).thenReturn(t);
		doNothing().when(teacherRepository).flush();
		doNothing().when(notificationService).sendMessage(isA(String.class), isA(String.class), isA(String.class));
		assertEquals("Lecture was changd from in presence to online",teacherService.cancelPresenceLecture(1, "1"));
	}
	@Test
	void testCancelPresenceLecture3() throws InvalidStudentException, InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException{
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		Student s = new Student("1","test","testsur");
		Course c = new Course();
		//c.getLectures().add(l);
		//l.setCourse(c);
		s.setEmail("student1team13@gmail.com");
		l.addStudentAttending(s);
		l.setSubjectName("testsub");
		l.setTeacher(t);
		l.addStartTime(2021, 11, 30, 24, 0);
		t.getLectures().add(l);
		s.addBookLecture(l);
		Optional<Teacher> teacher = Optional.of(t);
		when(teacherRepository.findById(any())).thenReturn(teacher);
		when(lectureRepository.getOne(any())).thenReturn(l);
		assertEquals("Lecture is too late to be changed",teacherService.cancelPresenceLecture(1, "1"));
	}
}
