package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestGetPastLectures {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	TeacherServiceImpl teacherService;
	@Autowired
	ModelMapper modelMapper;
	
	
	@BeforeEach
	void setUp() {
		modelMapper = mock(ModelMapper.class);
		teacherRepository = mock(TeacherRepository.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addRepo(teacherRepository);
		teacherService.addMM(modelMapper);
	}
	
	@Test
	void testGetPastLectures() {
		assertThrows(InvalidTeacherException.class,()->teacherService.getPastLectures("-1"));
	}
	
	@Test
	void testGetPastLectures2() throws InvalidTeacherException {
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		LectureDTO lDto = new LectureDTO();
		lDto.setSubjectName("test");
		l.setBookable(true);
		l.setSubjectName("test");
		l.addEndTime(2019, 10, 1, 10, 0);
		t.getLectures().add(l);
		when(teacherRepository.getOne(any())).thenReturn(t);
		when(modelMapper.map(any(),any())).thenReturn(lDto);
		when(teacherRepository.existsById(any())).thenReturn(true);
		assertEquals(l.getSubjectName(),teacherService.getPastLectures("1").get(0).getSubjectName());
	}
}
