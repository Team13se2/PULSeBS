package team13.pulsbes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.utils.Constants;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value = Constants.GET_NUMBER_STUDENTS_ATTENDING,method = RequestMethod.GET)
	public Integer getNumberStudentsAttending(@RequestParam("lecture_id") String id) throws InvalidLectureException {
		try {
			return teacherService.getNumberStudentsAttending(id);
		} catch (InvalidLectureException e) {
			
			System.out.println(e.getMessage());
			return 0;
		}
	}
	@RequestMapping(value = Constants.GET_ALL_LECTURES_T, method = RequestMethod.GET)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id) throws InvalidTeacherException{
		try {
			return teacherService.getAllLectures(id);
		} catch (InvalidTeacherException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value = Constants.GET_STUDENT_LIST,method = RequestMethod.GET)
	public List<StudentDTO> getStudentList(@RequestParam("lecture_id") String id) throws InvalidLectureException {
		try {
			return teacherService.getStudentList(id);
		} catch (InvalidLectureException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
	}
}
