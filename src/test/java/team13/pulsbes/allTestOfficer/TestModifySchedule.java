package team13.pulsbes.allTestOfficer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Holiday;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Schedule;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.HolidayRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.ScheduleRepository;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.services.NotificationService;

class TestModifySchedule {

	OfficerService officerService;
	LectureRepository lectureRepository;
	NotificationService notificationService;
	CourseRepository courseRepository;
	HolidayRepository holidayRepository;
	ScheduleRepository scheduleRepository;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		notificationService = mock(NotificationService.class);
		courseRepository = mock(CourseRepository.class);
		holidayRepository = mock(HolidayRepository.class);
		scheduleRepository = mock(ScheduleRepository.class);
		officerService = new OfficerService();
		officerService.addLectureRepository(lectureRepository);
		officerService.addNotificationService(notificationService);
		officerService.addCourseRepository(courseRepository);
		officerService.addHolidayRepository(holidayRepository);
		officerService.addScheduleRepository(scheduleRepository);
	}

	@Test
	void testModifySchedule() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("1");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Mon");
	}
	@Test
	void testModifySchedule2() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("1");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Tue");
	}
	@Test
	void testModifySchedule3() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("1");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Wed");
	}
	@Test
	void testModifySchedule4() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("1");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Thu");
	}
	@Test
	void testModifySchedule5() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("1");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Fri");
	} 
	@Test
	void testModifySchedule6() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("2");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Mon");
	}
	@Test
	void testModifySchedule7() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("2");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Tue");
	}
	@Test
	void testModifySchedule8() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("2");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Wed");
	}
	@Test
	void testModifySchedule9() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("2");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Thu");
	}
	@Test
	void testModifySchedule10() throws InvalidStudentException, ParseException, InvalidCourseException {
		Lecture l = new Lecture();
		Student s = new Student();
		Course c1 = new Course(); c1.setSemester("2");
		Teacher t = new Teacher();
		Holiday h = new Holiday();
		Schedule schedule = new Schedule(); schedule.setId(1);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.toString());
		h.setDate(calendar.getTime().toString());
		List<Holiday> holidays = new ArrayList<>();
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(schedule);
		holidays.add(h);
		c1.setTeacher(t);
		Optional<Course> oc = Optional.of(c1);
		s.setName("test"); s.setSurname("test");
		l.setIdschedule(1); l.addStudentAttending(s); l.setSubjectName("test");
		List<Lecture> lectures = new ArrayList<>();
		l.setStartTime("2000-01-01 01:01");
 		lectures.add(l);		
		when(lectureRepository.findAll()).thenReturn(lectures);
		doNothing().when(lectureRepository).delete(any());
		doNothing().when(lectureRepository).flush();
		doNothing().when(notificationService).sendMessage(any(), any(), any());
		when(holidayRepository.findAll()).thenReturn(holidays);
		when(courseRepository.findById(any())).thenReturn(oc);
		when(scheduleRepository.findAll()).thenReturn(schedules);
		officerService.modifySchedule(1, "1", "2021-10-10 08:30", "8:30", "10:00", 50,"Aula1" , "Fri");
	} 
}
