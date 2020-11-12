package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;

@Service
public class LoginService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	public LoginDTO login(IdPw idpw) {
		LoginDTO login = null;
		
		List<Student> students = new ArrayList<>();
		List<Teacher> teachers = new ArrayList<>();
		
		students = studentRepository.findAll();
		teachers = teacherRepository.findAll();
		
		for(Student s : students) {
			if(s.getEmail() == idpw.getEmail() && s.getPsw() == idpw.getPsw())
				
				return null;
		}
		
		return null;
	}
	
}
