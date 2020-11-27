package team13.pulsbes.allTestTeacher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.*;

import java.util.Optional;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.TeacherServiceImpl;

class TestCancelLecture {

	@Autowired
	TeacherServiceImpl teacherService;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	LectureRepository lectureRepository;
	
	@BeforeEach
	public void setUp() {
		teacherRepository = mock(TeacherRepository.class);
		lectureRepository = mock(LectureRepository.class);
		teacherService = new TeacherServiceImpl();
		teacherService.addLectureRepo(lectureRepository);
		teacherService.addRepo(teacherRepository);
	}
	
	@Test
	void testCancelLecture() {
		assertThrows(InvalidLectureException.class, ()->teacherService.cancelLecture("-1", "-1") );
	}
	
	@Test
	void testCancelLecture2() {
		Teacher t = new Teacher();
		Lecture l = new Lecture();
		Optional<Teacher> teacher = Optional.of(t);
		when(teacherRepository.findById(any())).thenReturn(teacher);
		when(lectureRepository.getOne(any())).thenReturn(l);
		
	}
}
