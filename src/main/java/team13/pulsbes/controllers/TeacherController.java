package team13.pulsbes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.utils.Constants;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value = Constants.GET_NUMBER_STUDENTS_ATTENDING,method = RequestMethod.POST)
	public Integer getNumberStudentsAttending(@RequestBody LectureDTO lDto) throws InvalidTeacherException {		
		try {
			return teacherService.getNumberStudentsAttending(lDto);
		} catch (InvalidTeacherException e) {
			
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
