package team13.pulsbes.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.entities.BookingManager;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.SupportOfficer;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.repositories.BookingManagerRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.SupportOfficerRepository;
import team13.pulsbes.repositories.TeacherRepository;

@Service
public class LoginService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	BookingManagerRepository bookingRepository;
	
	@Autowired
	SupportOfficerRepository supportRepository;
	
	public void addStudentRepo (StudentRepository sr) {
		this.studentRepository = sr;
	}
	public void addTeacherRepo(TeacherRepository lr) {
		this.teacherRepository = lr;
	}
	public void addManagerRepo(BookingManagerRepository br) {
		this.bookingRepository = br;
	}
	public void addSupportRepo(SupportOfficerRepository sr) {
		this.supportRepository = sr;
	}	
		
		
	private static final String TOKEN = "token";
	private static final String TYPE_TEACHER = "teacher";
	private static final String TYPE_STUDENT = "student";
	private static final String TYPE_BOOKING_MANAGER = "booking_manager";
	private static final String TYPE_SUPPORT_OFFICER = "support_officer";
	
	public LoginDTO login(IdPw idpw) throws WrongCredentialsException {
		LoginDTO login = null;
		
		checkEmail(idpw);
		
		//check email's first char to set role
		switch (idpw.getRole()) {
		case TYPE_STUDENT:
			for(Student s : studentRepository.findAll()) {
				if(s.getEmail().equals(idpw.getEmail()) && s.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(s,null,null,null);
					break;
				}
			}
			break;
		case TYPE_TEACHER:
			for (Teacher t : teacherRepository.findAll()) {
				if(t.getEmail().equals(idpw.getEmail()) && t.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(null,t,null,null);
					break;
				}
			}
			break;
		case TYPE_BOOKING_MANAGER:
			for(BookingManager b : bookingRepository.findAll()) {
				if(b.getEmail().equals(idpw.getEmail()) && b.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(null,null,b,null);
					break;
				}
			}
			break;
		case TYPE_SUPPORT_OFFICER:
			for(SupportOfficer so : supportRepository.findAll()) {
				if(so.getEmail().equals(idpw.getEmail()) && so.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(null,null,null,so);
					break;
				}
			}
			break;
		default:
			break;
		}
		if(login == null) {
			throw new WrongCredentialsException("Wrong Credentials");
		}
		return login;
	}
	
	private static void checkEmail(IdPw idpw) {
		String at ="@";
		String [] check  = idpw.getEmail().split(at);
		check = check[1].split("\\.");
		
		
		if(check[0].equals("politu")) {
			idpw.setRole(TYPE_TEACHER);
			return;
		}
		else if(check[0].equals("students")) {
			idpw.setRole(TYPE_STUDENT);
			return;
		}
		else if(check[0].equals("booking")) {
			idpw.setRole(TYPE_BOOKING_MANAGER);
			return;
		}
		else if(check[0].equals("officer")) {
			idpw.setRole(TYPE_SUPPORT_OFFICER);
			return;
		}
		idpw.setRole("exception");
	}

	private static LoginDTO loginConverter(Student s, Teacher t,BookingManager b, SupportOfficer so) {
		LoginDTO login = new LoginDTO();
		if(t == null && b == null && so==null) {
			login.setEmail(s.getEmail());
			login.setId(s.getId());
			login.setName(s.getName());
			login.setSurname(s.getSurname());
			login.setRole(TYPE_STUDENT);
			login.setToken(TOKEN);
			return login;
		}else if(s==null && b == null && so == null){
			login.setEmail(t.getEmail());
			login.setId(t.getNumber());
			login.setName(t.getName());
			login.setSurname(t.getSurname());
			login.setRole(TYPE_TEACHER);
			login.setToken(TOKEN);		
		}else if(s==null && t ==null && so == null) {
			login.setEmail(b.getEmail());
			login.setId(b.getId());
			login.setName(b.getName());
			login.setSurname(b.getSurname());
			login.setRole(TYPE_BOOKING_MANAGER);
			login.setToken(TOKEN);
		}else if(s==null&&t==null&&b==null) {
			login.setEmail(so.getEmail());
			login.setId(so.getId());
			login.setName(so.getName());
			login.setSurname(so.getSurname());
			login.setRole(TYPE_SUPPORT_OFFICER);
			login.setToken(TOKEN);
		}
		return login;
	}
}