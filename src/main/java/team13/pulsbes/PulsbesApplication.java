package team13.pulsbes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.services.NotificationService;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.services.TeacherService;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@SpringBootApplication
public class PulsbesApplication {

	 @Bean
	 ModelMapper modelMapper() {
	 	return new ModelMapper();
	 }



	 @Primary
	 @Bean
	 JavaMailSender javaMailSender() {
		 JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		 mailSender.setHost("smtp.gmail.com");
		 mailSender.setPort(587);

		 mailSender.setUsername("team13se2@gmail.com");
		 mailSender.setPassword("luyxcxvvvpzpmwrd");

		 Properties props = mailSender.getJavaMailProperties();
		 props.put("mail.transport.protocol", "smtp");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.debug", "true");

		 return mailSender;
	 }

	@Bean
	CommandLineRunner runner (NotificationService notificationService, StudentService studentService) {
		return new CommandLineRunner() {
			@Autowired
			StudentRepository studentRepository;

 			@Autowired
			LectureRepository lectureRepository;
 			
 			@Autowired
 			OfficerService officerService;
 			@Autowired
 			TeacherService teacherService;
 			@Autowired
 			CourseRepository courseRepository;
 			@Autowired
 			TeacherRepository teacherRepository;
			@Override
			public void run(String... args) throws Exception {
//				 Student s = studentRepository.getOne("1");
				 //s.addCourse(courseRepository.getOne("1"));
//				 s.setEmail("student1team13@gmail.com");
//				 studentRepository.save(s);
				 Course c = courseRepository.getOne("1");
				 c.setTeacher(teacherRepository.getOne("1"));
				 courseRepository.save(c);

				Lecture l1 = new Lecture();
				 Teacher teacher = teacherRepository.getOne("1");
				 teacher.setEmail("teacher1team13@gmail.com");
				 officerService.addTeacher(teacher);
				l1.setId("1");
				l1.addStartTime(2020, 10, 20, 15, 0);
				l1.addEndTime(2020, 10, 20, 16, 30);
				l1.setSubjectName(c.getName());
				l1.setAvailableSeat(50);
				l1.setTotalSeat(50);
				l1.setLectureType("Lab");
				l1.setCourse(c);
				l1.setSurnameString("Torchiano");
				l1.setRoomName("Aula 1");
				l1.setTeacher(teacher);
				officerService.addLecture(l1);

				Lecture l = new Lecture();
				l.setId("2"); 
				l.addStartTime(2020, 10, 25, 8,30 );
				l.addEndTime(2020, 10, 25, 10,0);
				l.setSubjectName(c.getName());
				l.setAvailableSeat(100); 
				l.setTotalSeat(100);
				l.setLectureType("Teoria");
				l.setCourse(c);
				l.setSurnameString("Vetrò");
				l.setRoomName("Aula 1");
				l.setTeacher(teacher);
				officerService.addLecture(l);
				
				Lecture l3 = new Lecture();
				l3.setId("3"); 
				l3.addStartTime(2020, 10, 27, 8,30 );
				l3.addEndTime(2020, 10, 27, 10,0);
				l3.setSubjectName(c.getName());
				l3.setAvailableSeat(100); 
				l3.setTotalSeat(100);
				l3.setLectureType("Teoria");
				l3.setCourse(c);
				l3.setSurnameString("Vetrò");
				l3.setRoomName("Aula 1");
				l3.setTeacher(teacher);
				officerService.addLecture(l3);

				// System.out.print(teacherRepository.getOne("1").getLectures());
				
				//  Lecture l3 = new Lecture();
				//  l3.setId("4"); 
				//  l3.addStartTime(2020, 10, 25, 18, 40);
				//  l3.addEndTime(2020, 10, 27, 10,0);
				//  l3.setSubjectName(c.getName());
				//  l3.setAvailableSeat(100); 
				//  l3.setTotalSeat(100);
				//  l3.setLectureType("Teoria");
				//  l3.setCourse(c);
				//  l3.setSurnameString("Vetrò");
				//  l3.setRoomName("TEST");
				//  l3.setTeacher(teacher);
				//  officerService.addLecture(l3);
			
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PulsbesApplication.class, args);
	}
	@PostConstruct
	public void setupDbWithData() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/seats", "sa", "password");
		conn.close();
	}
}
