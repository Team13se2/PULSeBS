package team13.pulsbes.allTestLogin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.entities.BookingManager;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.SupportOfficer;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.repositories.BookingManagerRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.SupportOfficerRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.LoginService;

class TestLogin {

	TeacherRepository teacherRepository;	
	StudentRepository studentRepository;
	BookingManagerRepository bookingRepository;
	SupportOfficerRepository supportRepository;
	LoginService loginService;
	
	@BeforeEach
	void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		studentRepository= mock(StudentRepository.class);
		bookingRepository = mock(BookingManagerRepository.class);
		supportRepository = mock(SupportOfficerRepository.class);
		loginService = new LoginService();
		loginService.addTeacherRepo(teacherRepository);
		loginService.addStudentRepo(studentRepository);
		loginService.addManagerRepo(bookingRepository);
		loginService.addSupportRepo(supportRepository);
	}
	
	@Test
	void testLogin1() {
		IdPw cred = new IdPw("email@wrong.it","psw","student");
		Student s = new Student("1","nope","nope");
		List<Student> students = new ArrayList<>();
		s.setEmail("wrong");
		students.add(s);
		when(studentRepository.findAll()).thenReturn(students);
		assertThrows(WrongCredentialsException.class,()->loginService.login(cred));
	}
	@Test
	void testLogin2() {
		IdPw cred = new IdPw("email@wrong.it","psw","teacher");
		Teacher t = new Teacher();
		List<Teacher> teachers = new ArrayList<>();
		t.setEmail("wrong");
		teachers.add(t);
		when(teacherRepository.findAll()).thenReturn(teachers);
		assertThrows(WrongCredentialsException.class,()->loginService.login(cred));
	}
	@Test
	void testLogin3() throws WrongCredentialsException {
		IdPw cred = new IdPw("teacher@politu.it","psw","teacher");
		LoginDTO lDto = new LoginDTO();
		Teacher t = new Teacher();
		List<Teacher> teachers = new ArrayList<>();
		t.setEmail("teacher@politu.it"); 
		t.setNumber("1");
		t.setName("name");
		t.setSurname("surname");
		t.setPsw("psw");
		teachers.add(t);
		when(teacherRepository.findAll()).thenReturn(teachers);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("teacher@politu.it",lDto.getEmail());
		assertEquals("teacher",lDto.getRole());
		assertEquals("token",lDto.getToken());
	}
	@Test
	void testLogin4() throws WrongCredentialsException {
		IdPw cred = new IdPw("student@students.politu.it","psw","student");
		LoginDTO lDto = new LoginDTO();
		Student s = new Student("1","name","surname");
		List<Student> students = new ArrayList<>();
		s.setEmail("student@students.politu.it");
		s.setPsw("psw");
		s.setId("1");
		students.add(s);
		when(studentRepository.findAll()).thenReturn(students);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("student@students.politu.it",lDto.getEmail());
		assertEquals("student",lDto.getRole());
		assertEquals("token",lDto.getToken());
	}
	
	@Test
	void testLogin5() throws WrongCredentialsException {
		IdPw cred = new IdPw("manager@booking.politu.it","psw","student");
		LoginDTO lDto = new LoginDTO();
		BookingManager b = new BookingManager();
		List<BookingManager> bookings = new ArrayList<>();
		b.setEmail("manager@booking.politu.it");
		b.setPsw("psw");
		b.setId("1");
		b.setName("name");
		b.setSurname("surname");
		bookings.add(b);
		when(bookingRepository.findAll()).thenReturn(bookings);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("manager@booking.politu.it",lDto.getEmail());
		assertEquals("booking_manager",lDto.getRole());
		assertEquals("token",lDto.getToken());
	}
	@Test
	void testLogin6() throws WrongCredentialsException{
		IdPw cred = new IdPw("support@officer.politu.it","psw","student");
		LoginDTO lDto = new LoginDTO();
		SupportOfficer so = new SupportOfficer();
		List<SupportOfficer> supports = new ArrayList<>();
		so.setEmail("support@officer.politu.it");
		so.setPsw("psw");
		so.setId("1");
		so.setName("name");
		so.setSurname("surname");
		supports.add(so);
		when(supportRepository.findAll()).thenReturn(supports);
		lDto = loginService.login(cred);
		assertEquals("name",lDto.getName());
		assertEquals("surname",lDto.getSurname());
		assertEquals("1",lDto.getId());
		assertEquals("support@officer.politu.it",lDto.getEmail());
		assertEquals("support_officer",lDto.getRole());
		assertEquals("token",lDto.getToken());
	}
}
