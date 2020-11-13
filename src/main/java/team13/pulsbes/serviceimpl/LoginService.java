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
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;

@Service
public class LoginService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentRepository studentRepository;
	

	public LoginDTO login(IdPw idpw) throws WrongCredentialsException {
		LoginDTO login = null;
		
		List<Student> students = new ArrayList<>();
		List<Teacher> teachers = new ArrayList<>();
		
		students = studentRepository.findAll();
		teachers = teacherRepository.findAll();
		if(!idpw.getTeacher()) {
			for(Student s : students) {
				if(s.getEmail() == idpw.getEmail() && s.getPsw() == idpw.getPsw()) {
					
					login = loginConverter(s,null);
					
				}
			}
		}
		else if(idpw.getTeacher()) {
			for (Teacher t : teachers) {
				if(t.getEmail()==idpw.getEmail() && t.getPsw() == idpw.getPsw()) {
					login = loginConverter(null,t);
					
				}
			}
		}
		
		if(login == null) {
			throw new WrongCredentialsException("Wrong Credentials");
		}
		
		return login;
	}
	


	static private LoginDTO loginConverter(Student s, Teacher t) {
		LoginDTO login = new LoginDTO();
		if(t == null) {
			login.setEmail(s.getEmail());
			login.setId(s.getId());
			login.setName(s.getName());
			login.setSurname(s.getSurname());
			login.setTeacher(false);
			login.setToken("token");
			return login;
		}
		login.setEmail(t.getEmail());
		login.setId(t.getId());
		login.setName(t.getName());
		login.setSurname(t.getSurname());
		login.setTeacher(true);
		login.setToken("token");
		return login;
	}
}