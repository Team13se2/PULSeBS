package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestAddCourseList {

	OfficerService officerService;
	TeacherRepository teacherRepository;
	CourseRepository courseRepository;
	@BeforeEach
	void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		courseRepository = mock(CourseRepository.class);
		officerService = new OfficerService();
		officerService.addTeacherRepository(teacherRepository);
		officerService.addCourseRepository(courseRepository);
	}
	
	@Test
	void testAddStudentList() {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Courses.csv");
		Teacher t = new Teacher();
		Course c = new Course();
		when(teacherRepository.getOne(any())).thenReturn(t);
		when(courseRepository.save(any())).thenReturn(c);
		officerService.addCourseList(f);
	}

}
