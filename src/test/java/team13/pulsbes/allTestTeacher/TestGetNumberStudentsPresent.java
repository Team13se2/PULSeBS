package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestGetNumberStudentsPresent {

	TeacherServiceImpl  teacherService;
	LectureRepository lectureRepository;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
	}
	
	@Test
	void testGetNumberStudentsPresent() throws InvalidLectureException{
		assertThrows(InvalidLectureException.class, ()->teacherService.getNumberStudentsPresent(-1));
	}
	@Test
	void testGetNumberStudentsPresent2() throws InvalidLectureException{
		when(lectureRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidLectureException.class, ()->teacherService.getNumberStudentsPresent(1));
	}
	@Test
	void testGetNumberStudentsPresent3() throws InvalidLectureException{
		Lecture l = new Lecture();
		l.setNrStudentsPresent(3);
		when(lectureRepository.existsById(any())).thenReturn(true);
		when(lectureRepository.getOne(any())).thenReturn(l);
		assertEquals(3, teacherService.getNumberStudentsPresent(1));
	}
}
