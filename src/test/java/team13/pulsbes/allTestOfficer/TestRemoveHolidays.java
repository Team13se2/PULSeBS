package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Holiday;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.HolidayRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.OfficerService;

class TestRemoveHolidays {
	OfficerService officerService;
	LectureRepository lectureRepository;
	CourseRepository courseRepository;
	HolidayRepository holidayRepository;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		courseRepository = mock(CourseRepository.class);
		holidayRepository = mock(HolidayRepository.class);
		officerService = new OfficerService();
		officerService.addCourseRepository(courseRepository);
		officerService.addLectureRepository(lectureRepository);
		officerService.addHolidayRepository(holidayRepository);
	}

	@Test
	void testRemoveHolidays() throws InvalidCourseException, ParseException {
		Lecture l = new Lecture();
		Holiday h = new Holiday();
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
		when(holidayRepository.save(any())).thenReturn(h);
	
		officerService.removeHolidays("2020-01-01 00:00", "2020-12-01 00:00");
	}
}
