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
 			
			@Override
			public void run(String... args) throws Exception {
//				Lecture l = new Lecture();
//				l.setId("3"); l.setAvailableSeat(10); l.setTotalSeat(100);
//				Teacher teacher = new Teacher();
//				teacher.setId("3");
//				teacher.setEmail("lanarig@gmail.com");
//				officerService.addTeacher(teacher);
//				Date startTime = new Date();
//				startTime.setYear(120);
//				startTime.setMonth(10);
//				startTime.setDay(17);
//				startTime.setHour(13);
//				startTime.setMinutes(10);
//				l.setStartTime(startTime);
//				System.out.println(startTime.toString());
//				l.addStartTime(120, 10, 19, 11,	13 );
//				l.setTeacher(teacher);
//				
//				
//				
//				officerService.addLecture(l);

//				Lecture l1 = new Lecture();
//				Teacher teacher = new Teacher();
//				teacher.setId("1");
//				teacher.setEmail("email@gmail.com");
//				officerService.addTeacher(teacher);
//				l1.setId("1");
//				Date startTime = new Date();				
//				startTime.setYear(120);
//				startTime.setMonth(10);
//				startTime.setDay(20);
//				startTime.setHour(13);
//				startTime.setMinutes(10);
//				l1.setStartTime(startTime);
//				Course c = new Course();
//				c.setId("1");
//				c.setName("Analisi 1");
//				l1.setCourse(c);
//				l1.setSurnameString("Torchiano");
//				l1.setRoomName("Aula 1");
//				l1.setTeacher(teacher);
//				officerService.addLecture(l1);
//				
//				Lecture l2 = new Lecture();
//				Teacher teacher1 = new Teacher();
//				teacher1.setId("2");
//				teacher1.setEmail("email@gmail.com");
//				officerService.addTeacher(teacher1);
//				l2.setId("2");
//				Date startTime1 = new Date();				
//				startTime1.setYear(120);
//				startTime1.setMonth(10);
//				startTime1.setDay(23);
//				startTime1.setHour(13);
//				startTime1.setMinutes(10);
//				l2.setStartTime(startTime1);
//				Course c1 = new Course();
//				c1.setId("1");
//				c1.setName("Analisi 1");
//				l2.setCourse(c1);
//				l2.setSurnameString("Torchiano");
//				l2.setRoomName("Aula 1");
//				l2.setTeacher(teacher1);
//				officerService.addLecture(l2);
//				
//				Lecture l3 = new Lecture();
//				Lecture l4 = new Lecture();
				
				//studentService.bookLecture("0","s123");					
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
