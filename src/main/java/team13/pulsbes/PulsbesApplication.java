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
				Lecture l = new Lecture("3", 10, 100);
				Teacher teacher = new Teacher();
				teacher.setId("3");
				teacher.setEmail("lanarig@gmail.com");
				officerService.addTeacher(teacher);
				Date startTime = new Date();
				//startTime.set(2020, 11, 17, 10, 33, 0);
				startTime.setYear(120);
				startTime.setMonth(10);
				startTime.setDate(17);
				startTime.setHours(11);
				startTime.setMinutes(44);
				startTime.setSeconds(0);
				System.out.println(startTime.toString());
				l.setStartTime(startTime);
				l.setTeacher(teacher);
				
				teacherService.getAllLectures("1");
				
				//officerService.addLecture(l);
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
