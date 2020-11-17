package allTestStudent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.StudentServiceImpl;

class TestBookLecture {

	StudentServiceImpl studentService;
	TeacherRepository tr;
	LectureRepository lr;
	
	@BeforeEach
	void setUp() {
		tr = mock(TeacherRepository.class);
		lr = mock(LectureRepository.class);
		studentService = new StudentServiceImpl();
		
	}
	
	@Test
	void test() {
		
		assertThrows(InvalidLectureException.class, ()-> studentService.bookLecture("1", "2"));
	}

}
