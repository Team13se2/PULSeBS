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
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
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
//
				BookingManager b = new BookingManager();
				b.setEmail("booking1team13@gmail.com");
				b.setPsw("psw");
				b.setId("1");
				b.setName("Signor");
				b.setSurname("Booking");
				bookingRepository.save(b);
				
				SupportOfficer so = new SupportOfficer();
				so.setEmail("officer1team13@gmail.com");
				so.setPsw("psw");
				so.setId("1");
				so.setName("Signor");
				so.setSurname("Officer");
				supportRepository.save(so);
	/*			Student s = studentRepository.getOne("1");
				//s.addCourse(courseRepository.getOne("1"));
				s.setEmail("student1team13@gmail.com");
				studentRepository.save(s);
			
				Student s2 = studentRepository.getOne("2");
				s2.setEmail("student2team13@gmail.com");
				studentRepository.save(s2);
				Student s3 = studentRepository.getOne("3");
				s3.setEmail("student3team13@gmail.com");
				studentRepository.save(s3);

				Student s4 = studentRepository.getOne("4");
				s4.setEmail("student4team13@gmail.com");
				studentRepository.save(s4);

				Student s5 = studentRepository.getOne("5");
				s5.setEmail("student5team13@gmail.com");
				studentRepository.save(s5);

				
				//analisi 1
				Course c = courseRepository.getOne("1");
				c.setTeacher(teacherRepository.getOne("1"));
				courseRepository.save(c);
	
				/*
				//Elettrotecnica
				Course c2 = courseRepository.getOne("2");
				c2.setTeacher(teacherRepository.getOne("2"));
				courseRepository.save(c2);

				//App web
				Course c3 = courseRepository.getOne("3");
				c3.setTeacher(teacherRepository.getOne("3"));
				courseRepository.save(c3);

				//fisica 1
				Course c4 = courseRepository.getOne("4");
				c4.setTeacher(teacherRepository.getOne("1"));
				courseRepository.save(c4);

				//Teoria dei segnali
				Course c5 = courseRepository.getOne("5");
				c5.setTeacher(teacherRepository.getOne("2"));
				courseRepository.save(c5);

				//Basi di dati
				Course c6 = courseRepository.getOne("6");
				c6.setTeacher(teacherRepository.getOne("3"));
				courseRepository.save(c6);

				//teacher 1
				Teacher teacher = teacherRepository.getOne("1");
				teacher.setEmail("teacher1team13@gmail.com");
				officerService.addTeacher(teacher);

				//Guccini
				Teacher teacher2 = teacherRepository.getOne("2");
				teacher2.setEmail("teacher2team13@gmail.com");
				officerService.addTeacher(teacher2);

				//Paoli
				Teacher teacher3 = teacherRepository.getOne("3");
				teacher3.setEmail("teacher3team13@gmail.com");
				officerService.addTeacher(teacher3);
				
				Lecture l1 = new Lecture();

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
				l1.addStudentAttending(s);
				l1.addStudentAttending(s2);
				l1.addStudentAttending(s3);
				l1.addStudentAttending(s4);
				l1.addStudentAttending(s5);
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

				Lecture l4 = new Lecture();
				l4.setId("4");
				l4.addStartTime(2020, 11, 27, 8,30 );
				l4.addEndTime(2020, 11, 27, 10,0);
				l4.setSubjectName(c.getName());
				l4.setAvailableSeat(100);
				l4.setTotalSeat(100);
				l4.setLectureType("Laboratorio");
				l4.setCourse(c);
				l4.setSurnameString("Torchiano");
				l4.setRoomName("Aula 4");
				l4.setTeacher(teacher);
				officerService.addLecture(l4);

				Lecture l5 = new Lecture();
				l5.setId("5");
				l5.addStartTime(2020, 11, 25, 8,30 );
				l5.addEndTime(2020, 11, 25, 10,0);
				l5.setSubjectName(c.getName());
				l5.setAvailableSeat(100);
				l5.setTotalSeat(100);
				l5.setLectureType("Laboratorio");
				l5.setCourse(c);
				l5.setSurnameString("Torchiano");
				l5.setRoomName("Aula 4");
				l5.setTeacher(teacher);
				officerService.addLecture(l5);


				Lecture l6 = new Lecture();
				l6.setId("6");
				l6.addStartTime(2020, 11, 8, 8,30 );
				l6.addEndTime(2020, 11, 8, 10,0);
				l6.setSubjectName("Elettrotecnica");
				l6.setAvailableSeat(45);
				l6.setTotalSeat(45);
				l6.setLectureType("Teoria");
				l6.setCourse(c2);
				l6.setSurnameString("Guccini");
				l6.setRoomName("Aula 3");
				l6.setTeacher(teacher2);
				officerService.addLecture(l6);

				Lecture l7 = new Lecture();
				l7.setId("7");
				l7.addStartTime(2020, 11, 1, 8,30 );
				l7.addEndTime(2020, 11, 1, 10,0);
				l7.setSubjectName("Elettrotecnica");
				l7.setAvailableSeat(45);
				l7.setTotalSeat(45);
				l7.setLectureType("Teoria");
				l7.setCourse(c2);
				l7.setSurnameString("Guccini");
				l7.setRoomName("Aula 3");
				l7.setTeacher(teacher2);
				officerService.addLecture(l7);

				Lecture l8 = new Lecture();
				l8.setId("8");
				l8.addStartTime(2020, 11, 15, 8,30 );
				l8.addEndTime(2020, 11, 15, 10,0);
				l8.setSubjectName("Elettrotecnica");
				l8.setAvailableSeat(45);
				l8.setTotalSeat(45);
				l8.setLectureType("Teoria");
				l8.setCourse(c2);
				l8.setSurnameString("Guccini");
				l8.setRoomName("Aula 3");
				l8.setTeacher(teacher2);
				officerService.addLecture(l8);

				Lecture l9 = new Lecture();
				l9.setId("9");
				l9.addStartTime(2020, 11, 22, 8,30 );
				l9.addEndTime(2020, 11, 22, 10,0);
				l9.setSubjectName("Elettrotecnica");
				l9.setAvailableSeat(45);
				l9.setTotalSeat(45);
				l9.setLectureType("Teoria");
				l9.setCourse(c2);
				l9.setSurnameString("Guccini");
				l9.setRoomName("Aula 3");
				l9.setTeacher(teacher2);
				officerService.addLecture(l9);

				Lecture l10 = new Lecture();
				l10.setId("10");
				l10.addStartTime(2020, 11, 2, 8,30 );
				l10.addEndTime(2020, 11, 2, 10,0);
				l10.setSubjectName("Applicazioni Web");
				l10.setAvailableSeat(45);
				l10.setTotalSeat(45);
				l10.setLectureType("Teoria");
				l10.setCourse(c3);
				l10.setSurnameString("Paoli");
				l10.setRoomName("Aula 3");
				l10.setTeacher(teacher3);
				officerService.addLecture(l10);

				Lecture l11 = new Lecture();
				l11.setId("11");
				l11.addStartTime(2020, 11, 9, 8,30 );
				l11.addEndTime(2020, 11, 9, 10,0);
				l11.setSubjectName("Applicazioni Web");
				l11.setAvailableSeat(45);
				l11.setTotalSeat(45);
				l11.setLectureType("Teoria");
				l11.setCourse(c3);
				l11.setSurnameString("Paoli");
				l11.setRoomName("Aula 3");
				l11.setTeacher(teacher3);
				officerService.addLecture(l11);

				Lecture l12 = new Lecture();
				l12.setId("12");
				l12.addStartTime(2020, 11, 16, 8,30 );
				l12.addEndTime(2020, 11, 16, 10,0);
				l12.setSubjectName("Applicazioni Web");
				l12.setAvailableSeat(45);
				l12.setTotalSeat(45);
				l12.setLectureType("Laboratorio");
				l12.setCourse(c3);
				l12.setSurnameString("Paoli");
				l12.setRoomName("Aula 3");
				l12.setTeacher(teacher3);
				officerService.addLecture(l12);

				Lecture l13 = new Lecture();
				l13.setId("13");
				l13.addStartTime(2020, 11, 23, 8,30 );
				l13.addEndTime(2020, 11, 23, 10,0);
				l13.setSubjectName("Applicazioni Web");
				l13.setAvailableSeat(45);
				l13.setTotalSeat(45);
				l13.setLectureType("Laboratorio");
				l13.setCourse(c3);
				l13.setSurnameString("Paoli");
				l13.setRoomName("Aula 3");
				l13.setTeacher(teacher3);
				officerService.addLecture(l13);

				Lecture l14 = new Lecture();
				l14.setId("14");
				l14.addStartTime(2020, 11, 3, 14,30 );
				l14.addEndTime(2020, 11, 3, 16,0);
				l14.setSubjectName("Fisica 1");
				l14.setAvailableSeat(45);
				l14.setTotalSeat(45);
				l14.setLectureType("Laboratorio");
				l14.setCourse(c4);
				l14.setSurnameString("Torchiano");
				l14.setRoomName("Aula 6");
				l14.setTeacher(teacher);
				officerService.addLecture(l14);

				Lecture l15 = new Lecture();
				l15.setId("15");
				l15.addStartTime(2020, 11, 10, 14,30 );
				l15.addEndTime(2020, 11, 10, 16,0);
				l15.setSubjectName("Fisica 1");
				l15.setAvailableSeat(45);
				l15.setTotalSeat(45);
				l15.setLectureType("Teoria");
				l15.setCourse(c4);
				l15.setSurnameString("Torchiano");
				l15.setRoomName("Aula 6");
				l15.setTeacher(teacher);
				officerService.addLecture(l15);

				Lecture l16 = new Lecture();
				l16.setId("16");
				l16.addStartTime(2020, 11, 17, 14,30 );
				l16.addEndTime(2020, 11, 17, 16,0);
				l16.setSubjectName("Fisica 1");
				l16.setAvailableSeat(45);
				l16.setTotalSeat(45);
				l16.setLectureType("Teoria");
				l16.setCourse(c4);
				l16.setSurnameString("Torchiano");
				l16.setRoomName("Aula 6");
				l16.setTeacher(teacher);
				officerService.addLecture(l16);

				Lecture l17 = new Lecture();
				l17.setId("17");
				l17.addStartTime(2020, 11, 3, 13,0 );
				l17.addEndTime(2020, 11, 3, 14,30);
				l17.setSubjectName("Teoria dei Segnali");
				l17.setAvailableSeat(45);
				l17.setTotalSeat(45);
				l17.setLectureType("Laboratorio");
				l17.setCourse(c5);
				l17.setSurnameString("Guccini");
				l17.setRoomName("Aula 6");
				l17.setTeacher(teacher2);
				officerService.addLecture(l17);

				Lecture l18= new Lecture();
				l18.setId("18");
				l18.addStartTime(2020, 11, 10, 13,0 );
				l18.addEndTime(2020, 11, 10, 14,30);
				l18.setSubjectName("Teoria dei Segnali");
				l18.setAvailableSeat(45);
				l18.setTotalSeat(45);
				l18.setLectureType("Teoria");
				l18.setCourse(c5);
				l18.setSurnameString("Guccini");
				l18.setRoomName("Aula 6");
				l18.setTeacher(teacher2);
				officerService.addLecture(l18);

				Lecture l19 = new Lecture();
				l19.setId("19");
				l19.addStartTime(2020, 11, 17, 13,0 );
				l19.addEndTime(2020, 11, 17, 14,30);
				l19.setSubjectName("Teoria dei Segnali");
				l19.setAvailableSeat(45);
				l19.setTotalSeat(45);
				l19.setLectureType("Laboratorio");
				l19.setCourse(c5);
				l19.setSurnameString("Guccini");
				l19.setRoomName("Aula 6");
				l19.setTeacher(teacher2);
				officerService.addLecture(l19);

				Lecture l20 = new Lecture();
				l20.setId("20");
				l20.addStartTime(2020, 11, 1, 13,0 );
				l20.addEndTime(2020, 11, 1, 14,30);
				l20.setSubjectName("Analisi 1");
				l20.setAvailableSeat(45);
				l20.setTotalSeat(45);
				l20.setLectureType("Laboratorio");
				l20.setCourse(c);
				l20.setSurnameString("Torchiano");
				l20.setRoomName("Aula 1");
				l20.setTeacher(teacher);
				officerService.addLecture(l20);

				Lecture l21 = new Lecture();
				l21.setId("21");
				l21.addStartTime(2020, 11, 8, 13,0 );
				l21.addEndTime(2020, 11, 8, 14,30);
				l21.setSubjectName("Analisi 1");
				l21.setAvailableSeat(45);
				l21.setTotalSeat(45);
				l21.setLectureType("Laboratorio");
				l21.setCourse(c);
				l21.setSurnameString("Torchiano");
				l21.setRoomName("Aula 1");
				l21.setTeacher(teacher);
				officerService.addLecture(l21);

				Lecture l22 = new Lecture();
				l22.setId("22");
				l22.addStartTime(2020, 11, 15, 13,0 );
				l22.addEndTime(2020, 11, 15, 15,30);
				l22.setSubjectName("Analisi 1");
				l22.setAvailableSeat(45);
				l22.setTotalSeat(45);
				l22.setLectureType("Laboratorio");
				l22.setCourse(c);
				l22.setSurnameString("Torchiano");
				l22.setRoomName("Aula 1");
				l22.setTeacher(teacher);
				//officerService.addLecture(l22);

				Lecture l23 = new Lecture();
				l23.setId("23");
				l23.addStartTime(2020, 11, 22, 13,0 );
				l23.addEndTime(2020, 11, 22, 15,30);
				l23.setSubjectName("Analisi 1");
				l23.setAvailableSeat(45);
				l23.setTotalSeat(45);
				l23.setLectureType("Laboratorio");
				l23.setCourse(c);
				l23.setSurnameString("Torchiano");
				l23.setRoomName("Aula 1");
				l23.setTeacher(teacher);
				//officerService.addLecture(l23);
				// l1.setId("1");
				// l1.addStartTime(2020, 10, 20, 15, 0);
				// l1.addEndTime(2020, 10, 20, 16, 30);
				// l1.setSubjectName(c.getName());
				// l1.setAvailableSeat(50);
				// l1.setTotalSeat(50);
				// l1.setLectureType("Lab");
				// l1.setCourse(c);
				// l1.setSurnameString("Torchiano");
				// l1.setRoomName("Aula 1");
				// l1.setTeacher(teacher);
				// officerService.addLecture(l1);

				// Lecture l = new Lecture();
				// l.setId("2");
				// l.addStartTime(2020, 10, 25, 8,30 );
				// l.addEndTime(2020, 10, 25, 10,0);
				// l.setSubjectName(c.getName());
				// l.setAvailableSeat(100);
				// l.setTotalSeat(100);
				// l.setLectureType("Teoria");
				// l.setCourse(c);
				// l.setSurnameString("Vetrò");
				// l.setRoomName("Aula 1");
				// l.setTeacher(teacher);
				// officerService.addLecture(l);

				// Lecture l3 = new Lecture();
				// l3.setId("3");
				// l3.addStartTime(2020, 10, 27, 8,30 );
				// l3.addEndTime(2020, 10, 27, 10,0);
				// l3.setSubjectName(c.getName());
				// l3.setAvailableSeat(100);
				// l3.setTotalSeat(100);
				// l3.setLectureType("Teoria");
				// l3.setCourse(c);
				// l3.setSurnameString("Vetrò");
				// l3.setRoomName("Aula 1");
				// l3.setTeacher(teacher);
				// officerService.addLecture(l3);

				System.out.print(teacherRepository.getOne("1").getLectures());

		*/

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