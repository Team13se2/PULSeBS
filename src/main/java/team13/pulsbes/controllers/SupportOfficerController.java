package team13.pulsbes.controllers;

import java.io.File;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidUserException;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/support_officer")
public class SupportOfficerController {
	
	@Autowired
	OfficerService officerService;
	
	Logger log = Logger.getLogger("SupportOfficerController");
	private static final String TYPE_SUPPORT = "support_officer";
	
	@GetMapping(value = Constants.ADD_STUDENTS)
	public void addStudents(@RequestBody File f,@CookieValue(value = "username") String username,@CookieValue(value = "type") String type) throws InvalidUserException {
		if(type == TYPE_SUPPORT) {
			officerService.addStudentList(f);
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
	@GetMapping(value = Constants.ADD_TEACHERS)
	public void addTeachers(@RequestBody File f,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException {
		if(type == TYPE_SUPPORT) {
			officerService.addTeacherList(f);
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
	@GetMapping(value = Constants.ADD_COURSES)
	public void addCourses(@RequestBody File f,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException {
		if(type == TYPE_SUPPORT) {
			officerService.addCourseList(f);
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
	@GetMapping(value = Constants.ENROLL_STUDENTS)
	public void enrollStudents(@RequestBody File f,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidCourseException, InvalidStudentException, InvalidUserException {
		if(type == TYPE_SUPPORT) {
			officerService.enrollStudent(f);
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
}
