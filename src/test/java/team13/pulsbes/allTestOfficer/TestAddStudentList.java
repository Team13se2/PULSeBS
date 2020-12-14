package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Student;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestAddStudentList {

	OfficerService officerService;
	StudentRepository studentRepository;
	
	@BeforeEach
	void setUp() {
		studentRepository = mock(StudentRepository.class);
		officerService = new OfficerService();
		officerService.addStudentRepository(studentRepository);
	}
	
	@Test
	void testAddStudentList() {
		File f = new File("src\\test\\java\\team13\\pulsbes\\allTestOfficer/Students.csv");
		Student s = new Student();
		when(studentRepository.save(any())).thenReturn(s);
		officerService.addStudentList(f);
	}

}
