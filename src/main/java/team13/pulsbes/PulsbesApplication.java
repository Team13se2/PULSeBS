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

import team13.pulsbes.entities.BookingManager;
import team13.pulsbes.entities.SupportOfficer;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.BookingManagerRepository;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.SupportOfficerRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.services.NotificationService;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.services.TeacherService;

import javax.annotation.PostConstruct;

import java.io.File;
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
			
			@Override
			public void run(String... args) throws Exception {

				SupportOfficer so = new SupportOfficer();
				so.setId("1");
				so.setEmail("support@officer.politu.it");
				so.setName("Signor");
				so.setSurname("Officer");
				so.setPsw("psw");
				supportRepository.save(so);
				
				BookingManager b = new BookingManager();
				b.setId("1");
				b.setEmail("manager@booking.politu.it");
				b.setName("Signor");
				b.setSurname("Booking");
				b.setPsw("psw");
				bookingRepository.save(b);

//		studentRepository.deleteAll();
//		courseRepository.deleteAll();
//		lectureRepository.deleteAll();
//		teacherRepository.deleteAll();
//		String csvFileT = "../Professors.csv";
//		File ft = new File(csvFileT);
//		officerService.addTeacherList(ft);
//		String csvFileC = "../Courses.csv";
//		File fc = new File(csvFileC);
//		officerService.addCourseList(fc);
//		String csvFileS = "../Students.csv";
//		File fs = new File(csvFileS);
//		officerService.addStudentList(fs);
   //
   //
//		String csvFileE = "../Enrollment.csv";
//		File fe = new File(csvFileE);
//		officerService.enrollStudent(fe);
   //
			    /*String csvFileSS = "../Schedule.csv";
			    File fss = new File(csvFileSS);
			    officerService.addScheduleList(fss);*/
//			    officerService.addLectureList(fss);

			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PulsbesApplication.class, args);
	}
	@PostConstruct
	public void setupDbWithData() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/team13", "sa", "password");
		conn.close();
	}
}