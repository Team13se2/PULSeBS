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
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.NotificationService;
import team13.pulsbes.services.StudentService;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

			@Override
			public void run(String... args) throws Exception {


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
