package team13.pulsbes.allTestBookingManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.BookingManagerServiceImpl;

class TestGetContactReportTeacher {

	BookingManagerServiceImpl bookingService;
	TeacherRepository teacherRepository;
	LectureRepository lectureRepository;
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		teacherRepository = mock(TeacherRepository.class);
		modelMapper = mock(ModelMapper.class);
		bookingService = new BookingManagerServiceImpl();
		bookingService.addTeacherRepository(teacherRepository);
		bookingService.addLectureRepository(lectureRepository);
		bookingService.addModelMapper(modelMapper);
	}
	
	@Test
	void testGetContactReportTeacher() {
		assertThrows(InvalidTeacherException.class,()->bookingService.getContactReportTeacher("-1"));
	}

	@Test
	void testGetContactReportTeacher2() {
		when(teacherRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidTeacherException.class, ()->bookingService.getContactReportTeacher("a"));
	}
	
	@Test
	void testGetContactReportTeacher3() throws InvalidStudentException, InvalidTeacherException {
		Teacher teacher = new Teacher();
		Course course = new Course();
		Lecture l = new Lecture();
		Student student = new Student();
		StudentDTO sDto = new StudentDTO();
		List<Student> presents = new ArrayList<Student>();
		List<Lecture> lectures = new ArrayList<>();
		Calendar today= Calendar.getInstance();
		today.add(Calendar.DATE,-1);
		l.setCode("1"); l.addStartTime(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE), today.get(Calendar.HOUR), today.get(Calendar.MINUTE));
		course.setCode("1");
		lectures.add(l);
		presents.add(student);
		l.setStudentsPresent(presents);
		teacher.getCourses().add(course);
		when(teacherRepository.getOne(any())).thenReturn(teacher);
		when(teacherRepository.existsById(any())).thenReturn(true);
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(modelMapper.map(any(), any())).thenReturn(sDto);
		assertNotNull(bookingService.getContactReportTeacher("2"));	
	}
}
