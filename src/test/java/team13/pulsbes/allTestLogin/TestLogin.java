package team13.pulsbes.allTestLogin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.LoginService;

class TestLogin {

	TeacherRepository teacherRepository;	
	StudentRepository studentRepository;
	LoginService loginService;
	
	@BeforeEach
	void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		studentRepository= mock(StudentRepository.class);
		loginService = new LoginService();
		loginService.addTeacherRepo(teacherRepository);
		loginService.addStudentRepo(studentRepository);
	}
	
	@Test
	void testLogin1() {
		IdPw cred = new IdPw("email","psw",false);
		Student s = new Student("1","nope","nope");
		List<Student> students = new ArrayList<>();
		s.setEmail("wrong");
		students.add(s);
		when(studentRepository.findAll()).thenReturn(students);
		assertThrows(WrongCredentialsException.class,()->loginService.login(cred));
	}
	@Test
	void testLogin2() {
		IdPw cred = new IdPw("email","psw",true);
		Teacher t = new Teacher();
		List<Teacher> teachers = new ArrayList<>();
		t.setEmail("wrong");
		teachers.add(t);
		when(teacherRepository.findAll()).thenReturn(teachers);
		assertThrows(WrongCredentialsException.class,()->loginService.login(cred));
	}
	@Test
	void testLogin3() throws WrongCredentialsException {
		IdPw cred = new IdPw("email","psw",true);
		LoginDTO lDto = new LoginDTO();
		Teacher t = new Teacher();
		List<Teacher> teachers = new ArrayList<>();
		t.setEmail("email"); 
		t.setId("1");
		t.setName("name");
		t.setSurname("surname");
		t.setPsw("psw");
		teachers.add(t);
		when(teacherRepository.findAll()).thenReturn(teachers);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("email",lDto.getEmail());
		assertEquals(true,lDto.getTeacher());
		assertEquals("token",lDto.getToken());
	}
	@Test
	void testLogin4() throws WrongCredentialsException {
		IdPw cred = new IdPw("email","psw",false);
		LoginDTO lDto = new LoginDTO();
		Student s = new Student("1","name","surname");
		List<Student> students = new ArrayList<>();
		s.setEmail("email");
		s.setPsw("psw");
		s.setId("1");
		students.add(s);
		when(studentRepository.findAll()).thenReturn(students);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("email",lDto.getEmail());
		assertEquals(false,lDto.getTeacher());
		assertEquals("token",lDto.getToken());
	}
}
