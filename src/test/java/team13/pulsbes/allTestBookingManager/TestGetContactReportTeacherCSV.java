package team13.pulsbes.allTestBookingManager;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.BookingManagerServiceImpl;

class TestGetContactReportTeacherCSV {

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
	void testGetContactReportTeacherCSV() {
		assertThrows(InvalidTeacherException.class,()->bookingService.getContactReportTeacherCSV("-1"));
	}
	@Test
	void testGetContactReportTeacherCSV2() {
		when(teacherRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidTeacherException.class,()->bookingService.getContactReportTeacherCSV("-1"));
	}
	@Test
	void testGetContactReportTeacherCSV3() throws InvalidTeacherException, IOException {
		Teacher t = new Teacher();
		Course course = new Course();
		Lecture lecture = new Lecture();
		Student student = new Student();
		StudentDTO studentDTO = new StudentDTO();
		List<Student> students = new ArrayList<Student>();
		List<Course> courses = new ArrayList<>();
		List<Lecture> lectures = new ArrayList<Lecture>();
		Calendar today= Calendar.getInstance();
		studentDTO.setId("test"); studentDTO.setName("testname"); studentDTO.setSurname("testsur");
		studentDTO.setCity("testcity"); studentDTO.setBirthday("testbirth"); studentDTO.setSSN("ssn"); studentDTO.setEmail("testemail");
		today.add(Calendar.DATE,-1);
		lectures.add(lecture); students.add(student);
		lecture.setCode("1"); lecture.setStudentsPresent(students);
		lecture.addStartTime(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE), today.get(Calendar.HOUR), today.get(Calendar.MINUTE));
		course.setCode("1");
		courses.add(course);
		t.setCourses(courses);
		when(teacherRepository.existsById(any())).thenReturn(true);
		when(teacherRepository.getOne(any())).thenReturn(t);
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(modelMapper.map(any(), any())).thenReturn(studentDTO);
		assertNotNull(bookingService.getContactReportTeacherCSV("2"));
	}
	
}
