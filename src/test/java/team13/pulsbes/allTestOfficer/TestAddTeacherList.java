package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestAddTeacherList {
	OfficerService officerService;
	TeacherRepository teacherRepository;
	
	@BeforeEach
	void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		officerService = new OfficerService();
		officerService.addTeacherRepository(teacherRepository);
	}
	
	@Test
	void testAddStudentList() {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Professors.csv");
		Teacher t = new Teacher();
		when(teacherRepository.save(any())).thenReturn(t);
		officerService.addTeacherList(f);
	}

}
