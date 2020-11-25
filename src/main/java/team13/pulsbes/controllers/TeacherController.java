package team13.pulsbes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.utils.Constants;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value = Constants.GET_NUMBER_STUDENTS_ATTENDING,method = RequestMethod.GET)
	public Integer getNumberStudentsAttending(@RequestParam("lecture_id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException {
		try {
			if (type.equals("teacher")) {
				return teacherService.getNumberStudentsAttending(id);
			}
			else return 0;
			
		} catch (InvalidLectureException e) {
			
			System.out.println(e.getMessage());
			return 0;
		}
	}
	@RequestMapping(value = Constants.GET_ALL_LECTURES, method = RequestMethod.GET)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals("teacher")) {
			List<LectureDTO> l = teacherService.getAllLectures(id);
			for(int i=0;i<l.size();i++){
				Integer nr = teacherService.getNumberStudentsAttending(l.get(i).getId());
				l.get(i).setNrStudents(nr);
			}
			return l;
		}
		else return null;
		
		} catch (InvalidTeacherException | InvalidLectureException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value = Constants.GET_STUDENT_LIST,method = RequestMethod.GET)
	public List<StudentDTO> getStudentList(@RequestParam("lecture_id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException {
		try {
			if (type.equals("teacher")) {
			return teacherService.getStudentList(id);
			}
			else return null;
		} catch (InvalidLectureException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value = Constants.CANCEL_LECTURE,method = RequestMethod.GET)
	public String cancelLecture(@RequestParam("lecture_id") String lectureId, @RequestParam("course_id") String courseId, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException {
		try {
			if (type.equals("teacher")) {
			return teacherService.cancelLecture(lectureId, courseId);
			}
			else return null;
		} catch (InvalidLectureException | InvalidCourseException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value = Constants.CHANGE_LECTURE_TYPE,method = RequestMethod.GET)
	public String changeLectureType(@RequestParam("lecture_id") String lectureId, @RequestParam("course_id") String courseId, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException {
		try {
			if (type.equals("teacher")) {
			return teacherService.changeLectureType(lectureId, courseId);
			}
			else return null;
		} catch (InvalidLectureException | InvalidCourseException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
	}
}
