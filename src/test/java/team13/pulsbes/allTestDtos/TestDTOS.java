package team13.pulsbes.allTestDtos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.BookingManagerDTO;
import team13.pulsbes.dtos.CourseDTO;
import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.LectureIdStudentId;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.StudentPresence;
import team13.pulsbes.dtos.SupportOfficerDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.BookingManager;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.SupportOfficer;

class TestDTOS {

	@Test
	void testLectureDTO() {
		LectureDTO l = new LectureDTO();
		l.setCode("1");
		l.setStartTime(null);
		l.setEndTime(null);
		l.setSubjectName("test");
		l.setAvailableSeat(10);
		l.setTotalSeat(100);
		l.setRoomName("aula");
		l.setBookable(true);
		l.setNrStudentsBooked(10);
		l.setNrStudentsPresent(10);
		l.setBookable(true);
		assertEquals("1",l.getCode());
		assertNull(l.getStartTime());
		assertNull(l.getEndTime());
		assertEquals(10,l.getAvailableSeat());
		assertEquals(100,l.getTotalSeat());
		assertEquals("aula",l.getRoomName());
		assertEquals(true, l.getBookable());
		assertEquals(l.getNrStudentsBooked(), 10);
		assertEquals(l.getNrStudentsPresent(), 10);
		assertEquals(true, l.isBookable());
		}
		
		@Test
		void testLectureIdStudentId() {
			LectureIdStudentId ls = new LectureIdStudentId("10", 10);
			ls.setLectureId(1);
			ls.setStudentId("1");
			assertEquals("1", ls.getStudentId());
			assertEquals(1, ls.getLectureId());
		}
	
		@Test
		void testStudentPresence(){
			StudentDTO s = new StudentDTO();
			StudentPresence sp = new StudentPresence(s,true);
			sp.setStudent(s);
			sp.setPresence(false);
			assertEquals(false, sp.getPresence());
			assertEquals(s, sp.getStudent());
		}
		
		@Test
		void testCourseDTO() {
			CourseDTO c = new CourseDTO();
			c.setCode("1");
			c.setName("analisi");
			c.setYear(1);
			c.setSemester(2);
			assertEquals("1",c.getCode());
			assertEquals("analisi",c.getName());
			assertEquals(1, c.getYear());
			assertEquals(2, c.getSemester());
		}
		
		@Test
		void testIdPw() {
			IdPw i = new IdPw("email","psw","teacher");
			assertEquals( "email",i.getEmail());
			assertEquals("psw",i.getPsw());
			assertEquals("teacher",i.getRole());
			i.setEmail("1");
			i.setPsw("2");
			i.setRole("student");
			assertEquals("1",i.getEmail());
			assertEquals("2",i.getPsw());
			assertEquals("student",i.getRole());
		}
		 
		@Test
		void testLoginDTO() {
			LoginDTO l = new LoginDTO();
			LoginDTO lDto = new LoginDTO("email","1","test","testsur","teacher","asd");
			l.setEmail("email");					
			l.setId("1");
			l.setName("test");
			l.setSurname("testsur");
			l.setRole("teacher");
			l.setToken("asd");
			assertEquals("email",l.getEmail());
			assertEquals("1",l.getId());
			assertEquals("test",l.getName());
			assertEquals("testsur",l.getSurname());
			assertEquals("asd",l.getToken());
			assertEquals("teacher",l.getRole());			
		}
		
		@Test 
		void testStudentDTO() {
			StudentDTO s = new StudentDTO();
			s.setId("1");
			s.setName("test");
			s.setSurname("testsur");
			s.setEmail("email");
			s.setCity("testcity");
			s.setBirthday("10/10/1990");
			s.setSSN("TSTNME");
			assertEquals("1", s.getId());
			assertEquals("test", s.getName());
			assertEquals("testsur", s.getSurname());
			assertEquals("email", s.getEmail());
			assertEquals("testcity", s.getCity());
			assertEquals("10/10/1990", s.getBirthday());
			assertEquals("TSTNME", s.getSSN());
		}
		
		@Test
		void testTeacherDTO() {
			TeacherDTO t = new TeacherDTO();
			List<Lecture> lectures = new ArrayList<>();
			List<Course> courses = new ArrayList<>();
			t.setNumber("1");
			t.setName("test");
			t.setSurname("testsur");
			t.setEmail("email");
			t.setLectures(lectures);
			t.setCourses(courses);
			t.setSSN("TSTNME");
			assertEquals("1", 		t.getNumber());
			assertEquals("test", 	t.getName());
			assertEquals("testsur", t.getSurname());
			assertEquals("email", 	t.getEmail());
			assertEquals(lectures, t.getLectures());
			assertEquals(courses, t.getCourses());
			assertEquals("TSTNME", t.getSSN());
		}
		@Test
		void testBookingManagerDTO() {
			BookingManagerDTO b = new BookingManagerDTO();
			b.setId("1");
			b.setName("test");
			b.setSurname("testsur");
			b.setEmail("test@gmail.com");
			assertEquals("1", 		b.getId());
			assertEquals("test", 	b.getName());
			assertEquals("testsur", b.getSurname());
			assertEquals("test@gmail.com", 	b.getEmail());
		}
		@Test
		void testSupportOfficerDTO() {
			SupportOfficerDTO s = new SupportOfficerDTO();
			s.setId("1");
			s.setName("test");
			s.setSurname("testsur");
			s.setEmail("test@gmail.com");
			assertEquals("1", 		s.getId());
			assertEquals("test", 	s.getName());
			assertEquals("testsur", s.getSurname());
			assertEquals("test@gmail.com", 	s.getEmail());
		}
}
