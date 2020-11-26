package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestGetStudentList {
	
	LectureRepository lectureRepository;
	TeacherServiceImpl teacherService;
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		modelMapper = mock (ModelMapper.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
		teacherService.addMM(modelMapper);
	}
	
	@Test
	void testGetStudentList() {
		assertThrows(InvalidLectureException.class , () -> teacherService.getStudentList("-1"));
	}
	@Test
	void testGetStudentList1() throws InvalidStudentException, InvalidLectureException {
		Lecture l = new Lecture();
		Student s = new Student("1","test","testsur");
		l.addStudentAttending(s);
		StudentDTO sDto = new StudentDTO();
		sDto.setId("1"); sDto.setName("test"); sDto.setSurname("testsur");
		when(lectureRepository.getOne(any())).thenReturn(l);
		when(modelMapper.map(any(),any())).thenReturn(sDto);
		assertEquals(s.getId(),sDto.getId());
		assertEquals(s.getId(),teacherService.getStudentList("1").get(0).getId());
	}
}
