package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestGetNumberStudentsAttending {
	
	TeacherServiceImpl  teacherService;
	LectureRepository lectureRepository;
	Lecture l;
	
	@BeforeEach
	public void setUp() {
		lectureRepository = mock(LectureRepository.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
		//l = new Lecture("1",10,0);
		l = new Lecture();
		Student s = new Student("s201101","Lorenzo","Lanari");
		try {
			l.addStudentAttending(s);
		} catch (InvalidStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(lectureRepository.getOne(any())).thenReturn(l);
	}
	@Test
	void testGetNumberStudentsAttending() throws InvalidTeacherException, InvalidStudentException, InvalidLectureException {
		//lDto.setStudents(l.getStudents());
		when(lectureRepository.existsById(any())).thenReturn(true);
		assertEquals(1,teacherService.getNumberStudentsAttending(1));

	}

	@Test
	void testGetNumberStudentsAttending2() throws InvalidTeacherException, InvalidStudentException, InvalidLectureException {
		assertThrows(InvalidLectureException.class, () -> teacherService.getNumberStudentsAttending(-1));
	}
	
	@Test
	void testGetNumberStudentsAttending3() throws InvalidTeacherException, InvalidStudentException, InvalidLectureException {
		when(lectureRepository.existsById(any())).thenReturn(false);
		assertThrows(InvalidLectureException.class, () -> teacherService.getNumberStudentsAttending(1));
	}
}
