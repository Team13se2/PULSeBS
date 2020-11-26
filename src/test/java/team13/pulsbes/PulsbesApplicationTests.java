package team13.pulsbes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import team13.pulsbes.services.NotificationService;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;

@ActiveProfiles("test")
@SpringBootTest
class PulsbesApplicationTests {
	
	@Autowired
	private StudentService studentService;	
	@Autowired
	private TeacherService teacherService;	
	@Autowired
	private NotificationService notificationService;
	
//	@Autowired
//	TeacherRepository teacherRepository;
//	@Autowired
//	CourseRepository courseRepository;
//	@Autowired
//	StudentRepository studentRepository;
//	@Autowired
//	LectureRepository lectureRepository;
//	
//	/* STUDENT */
//	private String studentId = "100";
//	private String studentName = "studentname";
//	private String studentSurname = "studentsurname";
//	private String studentEmail = "student1team13@gmail.com";
//	private String studentPsw = "studentpsw";
//	
//	/* TEACHER */
//	private String teacherId = "100";
//	private String teacherName = "teachername";
//	private String teacherSurname = "teachersurname";
//	private String teacherEmail = "teacher1team13@gmail.com";
//	private String teacherPsw = "teacherpsw";
//	
//	/* LECTURE */
//	private String lectureId = "100";
//	private String subjectName = "testSubjectName";
//	private String lectureType = "testLectureType";
//	private Integer availableSeat = 100;
//	private Integer totaleSeat = 100;
//	private String roomName = "testRoomName";
//	
//	/* COURSE */
//	private String courseId = "100";
//	private String courseName = "testCourseName";
//	Student s = new Student(studentId,studentName,studentSurname);
//	Teacher t = new Teacher();
//	Lecture l = new Lecture();
//	Course c = new Course ();
//	
//	@BeforeAll
//	public static void init() throws InvalidCourseException, InvalidStudentException {
//		s.setEmail(studentEmail);
//		s.setPsw(studentPsw);
//		t.setId(teacherId);
//		t.setName(teacherName);
//		t.setSurname(teacherSurname);
//		t.setEmail(teacherEmail);
//		t.setPsw(teacherPsw);
//		l.setId(lectureId);
//		//START TIME AND END TIME
//		l.setSubjectName(subjectName);
//		l.setLectureType(lectureType);
//		l.setAvailableSeat(availableSeat);
//		l.setTotalSeat(totaleSeat);
//		l.setRoomName(roomName);
//		c.setId(courseId);
//		c.setName(courseName);
//		
//		s.addCourse(c);
//		c.newStudentEnrolled(s);
//		l.addStudentAttending(s);
//		t.getLectures().add(l);
//		t.getCourses().add(c);
//		c.getLectures().add(l);
//		l.setTeacher(t);
//		l.setCourse(c);
//		c.setTeacher(t);
//		
//		teacherRepository.save(t);
//		studentRepository.save(s);
//		courseRepository.save(c);
//		lectureRepository.save(l);
//	}
//	
//	@AfterAll
//	public static void clean() {
//		teacherRepository.delete(t);
//		studentRepository.delete(s);
//		courseRepository.delete(c);
//		lectureRepository.delete(l);
//	}
	
	@Test
	public void contextLoads() {
	}

}
