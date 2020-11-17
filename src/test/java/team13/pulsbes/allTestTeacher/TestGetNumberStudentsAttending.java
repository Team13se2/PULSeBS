package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestGetNumberStudentsAttending {
	
	TeacherServiceImpl  teacherService;

	@BeforeEach
	public void setUp() {
		teacherService = new TeacherServiceImpl();
	}
	@Test
	void testGetNumberStudentsAttending() throws InvalidTeacherException, InvalidStudentException, InvalidLectureException {
		Lecture l = new Lecture("1",10,0);
		LectureDTO lDto = new LectureDTO();
		Student s = new Student("s201101","Lorenzo","Lanari");
		l.addStudentAttending(s);
		//lDto.setStudents(l.getStudents());
		//assertEquals(1,teacherService.getNumberStudentsAttending(lDto));

	}

	@Test
	void testGetNumberStudentsAttending2() throws InvalidTeacherException, InvalidStudentException, InvalidLectureException {
		assertThrows(InvalidLectureException.class, () -> teacherService.getNumberStudentsAttending(null));
	}

}
