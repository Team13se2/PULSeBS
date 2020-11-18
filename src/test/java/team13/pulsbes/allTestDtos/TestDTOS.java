package team13.pulsbes.allTestDtos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.CourseDTO;
import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.TeacherDTO;

class TestDTOS {

	@Test
	void testLectureDTO() {
		LectureDTO l = new LectureDTO();
		l.setId("1");
		l.setStartTime(null);
		l.setEndTime(null);
		l.setSubjectName("test");
		l.setLectureType("lab");
		l.setSurnameString("test");
		l.setAvailableSeat(10);
		l.setTotalSeat(100);
		l.setRoomName("aula");
		assertEquals(l.getId(),"1");
		assertNull(l.getStartTime());
		assertNull(l.getEndTime());
		assertEquals(l.getLectureType(),"lab");
		assertEquals(l.getSurnameString(),"test");
		assertEquals(l.getAvailableSeat(),10);
		assertEquals(l.getTotalSeat(),100);
		assertEquals(l.getRoomName(),"aula");
		}
		
		@Test
		void testCourseDTO() {
			CourseDTO c = new CourseDTO();
			c.setId("1");
			c.setName("analisi");
			assertEquals("1",c.getId());
			assertEquals("analisi",c.getName());
		}
		
		@Test
		void testIdPw() {
			IdPw i = new IdPw("email","psw",true);
			assertEquals(i.getEmail(), "email");
			assertEquals(i.getPsw(), "psw");
			assertTrue(i.getTeacher());
			i.setEmail("1");
			i.setPsw("2");
			i.setTeacher(false);
			assertEquals(i.getEmail(), "1");
			assertEquals(i.getPsw(), "2");
			assertTrue(!i.getTeacher());
		}
		 
		@Test
		void testLoginDTO() {
			LoginDTO l = new LoginDTO();
			l.setEmail("email");					
			l.setId("1");
			l.setName("test");
			l.setSurname("testsur");
			l.setTeacher(true);
			l.setToken("asd");
			assertEquals(l.getEmail(), "email");
			assertEquals(l.getId(), "1");
			assertEquals(l.getName(), "test");
			assertEquals(l.getSurname(), "testsur");
			assertEquals(l.getToken(), "asd");
			assertTrue(l.getTeacher());			
		}
		
		@Test 
		void testStudentDTO() {
			StudentDTO s = new StudentDTO();
			s.setId("1");
			s.setName("test");
			s.setSurname("testsur");
			s.setEmail("email");
			assertEquals("1", s.getId());
			assertEquals("test", s.getName());
			assertEquals("testsur", s.getSurname());
			assertEquals("email", s.getEmail());
		}
		
		@Test
		void testTeacherDTO() {
			TeacherDTO t = new TeacherDTO();
			t.setId("1");
			t.setName("test");
			t.setSurname("testsur");
			t.setEmail("email");
			assertEquals("1", 		t.getId());
			assertEquals("test", 	t.getName());
			assertEquals("testsur", t.getSurname());
			assertEquals("email", 	t.getEmail());
		}
}
