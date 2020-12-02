package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;

@Service
public class LoginService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	 public void addStudentRepo (StudentRepository sr) {
			this.studentRepository = sr;
		}
		public void addTeacherRepo(TeacherRepository lr) {
			this.teacherRepository = lr;
		}

	private static final String TYPE_TEACHER = "teacher";
	private static final String TYPE_STUDENT = "student";

	public LoginDTO login(IdPw idpw) throws WrongCredentialsException {
		LoginDTO login = null;
		
		checkEmail(idpw);
		
		//check email's first char to set role
		switch (idpw.getRole()) {
		case TYPE_TEACHER:
			for (Teacher t : teacherRepository.findAll()) {
				if(t.getEmail().equals(idpw.getEmail()) && t.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(null,t);
					break;
				}
			}
			break;
		case TYPE_STUDENT:
			for(Student s : studentRepository.findAll()) {
				if(s.getEmail().equals(idpw.getEmail()) && s.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(s,null);
					break;
				}
			}
			break;
		default:
			break;
		}
	/*	
		if(!idpw.getTeacher()) {
			for(Student s : studentRepository.findAll()) {
				if(s.getEmail().equals(idpw.getEmail()) && s.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(s,null);
					break;
				}
			}
		}
		else if(idpw.getTeacher()) {
			for (Teacher t : teacherRepository.findAll()) {
				if(t.getEmail().equals(idpw.getEmail()) && t.getPsw().equals(idpw.getPsw())) {
					login = loginConverter(null,t);
					break;
				}
			}
		}
*/
		if(login == null) {
			throw new WrongCredentialsException("Wrong Credentials");
		}
		return login;
	}
	
	static private void checkEmail(IdPw idpw) {
		if(idpw.getEmail().charAt(0) == 't') {
			idpw.setRole(TYPE_TEACHER);
			return;
		}
		else if(idpw.getEmail().charAt(0) == 's') {
			idpw.setRole(TYPE_STUDENT);
			return;
		}
		idpw.setRole("exception");
	}

	static private LoginDTO loginConverter(Student s, Teacher t) {
		LoginDTO login = new LoginDTO();
		if(t == null) {
			login.setEmail(s.getEmail());
			login.setId(s.getId());
			login.setName(s.getName());
			login.setSurname(s.getSurname());
			login.setRole(TYPE_STUDENT);
			login.setToken("token");
			return login;
		}else {
			login.setEmail(t.getEmail());
			login.setId(t.getId());
			login.setName(t.getName());
			login.setSurname(t.getSurname());
			login.setRole(TYPE_TEACHER);
			login.setToken("token");		//(LUCA) secondo me setToken deve essere impostato con l'id del teacher:
		}
		return login;
	}
}