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


import team13.pulsbes.repositories.*;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.services.NotificationService;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.services.TeacherService;


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
		mailSender.setPassword("tczczjpgxbcauilb");

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
			@Autowired
			BookingManagerRepository bookingRepository;
			@Autowired
			SupportOfficerRepository supportRepository;
			@Autowired
			ScheduleRepository scheduleRepository;
			
			@Override
			public void run(String... args) throws Exception {

		 	

		


			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PulsbesApplication.class, args);
	}	
	
}