package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;


public class TestGetAllLectures {
	
	TeacherServiceImpl  teacherService;
	TeacherRepository teacherRepository;
	List<Lecture> lectures = new ArrayList<>();
	Teacher t = new Teacher();
	ModelMapper modelMapper;
	
	@BeforeEach
	public void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		modelMapper = mock(ModelMapper.class);
		//Lecture l = new Lecture("analisi", 10, 50);
		Lecture l = new Lecture();
		LectureDTO lDto = new LectureDTO ();
		lectures.add(l);
		t.setLectures(lectures);
		l.setSubjectName("dio");
		l.setStartTime(null);
		l.setEndTime(null);
		l.setLectureType("cane");
		l.setSurnameString(t.getSurname());
		l.setRoomName("infame");
		t.setId("1");
		teacherService = new TeacherServiceImpl();
		teacherService.addRepo(teacherRepository);
		teacherService.addMM(modelMapper);
		//teacherRepository.save(t);
		
		when(teacherRepository.getOne(anyString())).thenReturn(t);
		when(modelMapper.map(any(),any())).thenReturn(lDto);
	}
	
	@Test
	public void testGetAllLectures() {
		assertThrows(InvalidTeacherException.class, () -> teacherService.getAllLectures("-1"));
	}
	@Test
	public void testGetAllLectures2() throws InvalidTeacherException {
		when(teacherRepository.getOne(anyString())).thenReturn(t);
		List<LectureDTO> lDtos = new ArrayList<>();
		lDtos = teacherService.getAllLectures("1");
		//assertEquals(lDtos.get(0).getId(),t.getId());
		
	}
}
