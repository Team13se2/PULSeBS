package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Schedule;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.ScheduleRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestAddScheduleList {

	OfficerService officerService;
	TeacherRepository teacherRepository;
	CourseRepository courseRepository;
	LectureRepository lectureRepository;
	ScheduleRepository scheduleRepository;
	@BeforeEach
	void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		courseRepository = mock(CourseRepository.class);
		lectureRepository = mock(LectureRepository.class);
		scheduleRepository = mock(ScheduleRepository.class);
		officerService = new OfficerService();
		officerService.addTeacherRepository(teacherRepository);
		officerService.addCourseRepository(courseRepository);
		officerService.addLectureRepository(lectureRepository);
		officerService.addScheduleRepository(scheduleRepository);
	}
	
	@Test
	void testAddScheduleListFirstSemester() throws InvalidCourseException {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Schedule.csv");
		Schedule s = new Schedule();
		Course c = new Course();
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		c.setSemester("1");
		c.setName("name");
		c.setTeacher(t);
		Optional<Course> oc = Optional.of(c);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(courseRepository.getOne(any())).thenReturn(c);
		when(lectureRepository.save(any())).thenReturn(l);
		when(scheduleRepository.save(any())).thenReturn(s);
		doNothing().when(lectureRepository).flush();
		officerService.addScheduleList(f);
	}
	@Test
	void testAddScheduleListSecondSemester() throws InvalidCourseException {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Schedule.csv");
		Schedule s = new Schedule();
		Course c = new Course();
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		c.setSemester("2");
		c.setName("name");
		c.setTeacher(t);
		Optional<Course> oc = Optional.of(c);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(courseRepository.getOne(any())).thenReturn(c);
		when(lectureRepository.save(any())).thenReturn(l);
		when(scheduleRepository.save(any())).thenReturn(s);
		doNothing().when(lectureRepository).flush();
		officerService.addScheduleList(f);
	}
}
