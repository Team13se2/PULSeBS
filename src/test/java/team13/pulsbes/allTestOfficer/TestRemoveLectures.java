package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.OfficerService;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
class TestRemoveLectures {

	OfficerService officerService;
	LectureRepository lectureRepository;
	CourseRepository courseRepository;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		courseRepository = mock(CourseRepository.class);
		officerService = new OfficerService();
		officerService.addCourseRepository(courseRepository);
		officerService.addLectureRepository(lectureRepository);
	}

	@Test
	void testRemoveLectures() {
		Lecture l = new Lecture();
		l.setCode("test");
		Course c = new Course();
		c.setYear("2020");
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(l);
		l.addStartTime(2020, 6, 1, 0, 0);
		l.addEndTime(2020, 6, 1, 30, 0);
		Optional<Course> oc = Optional.of(c);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(lectureRepository.save(any())).thenReturn(l);
		doNothing().when(lectureRepository).flush();
		officerService.removeLectures("2020","2020-01-01 00:00", "2020-12-01 00:00");
	}
}
