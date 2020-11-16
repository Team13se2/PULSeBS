package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

public class TestGetAllLectures {
	
	TeacherServiceImpl  teacherService;
	TeacherRepository teacherRepository;
	
	@BeforeEach
	public void setUp() {
		teacherService = new TeacherServiceImpl();
		
		teacherRepository = mock(TeacherRepository.class);
	}
	
	@Test
	public void testGetAllLectures() {
		assertThrows(InvalidTeacherException.class, () -> teacherService.getAllLectures(null));
	}
	@Test
	public void testGetAllLectures2() throws InvalidTeacherException {
		TeacherDTO tDto = new TeacherDTO();
		Teacher t = new Teacher();
		List<Lecture> lectures = new ArrayList<>();
		Lecture l = new Lecture("analisi", 10, 50);
		lectures.add(l);
		t.setLectures(lectures);
		tDto.setLectures(lectures);
		when(teacherRepository.getOne(anyString())).thenReturn(t);
		//assertEquals(t,teacherService.getAllLectures(tDto));
	}
}
