package team13.pulsbes.controllers;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.utils.Constants;

@RequestMapping("/teacher")
@RestController
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	Logger log = Logger.getLogger("TeacherController");
	private static final String TYPE_TEACHER = "teacher";

	@GetMapping(value = Constants.GET_NUMBER_STUDENTS_ATTENDING)
	public Integer getNumberStudentsAttending(@RequestParam("lecture_id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException {
		try {
			if (type.equals(TYPE_TEACHER)) {
				return teacherService.getNumberStudentsAttending(id);
			}
			else return 0;

		} catch (InvalidLectureException e) {

			log.throwing(this.getClass().getName(), "getNumberStudentsAttending", e);
			return 0;
		}
	}
	@GetMapping(value = Constants.GET_ALL_LECTURES)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			List<LectureDTO> l = teacherService.getAllLectures(id);
			for(int i=0;i<l.size();i++){
				Integer nr = teacherService.getNumberStudentsAttending(l.get(i).getId());
				l.get(i).setNrStudentsBooked(nr);
			}
			return l;
		}
		else return Collections.emptyList();

		} catch (InvalidTeacherException | InvalidLectureException e) {
			log.throwing(this.getClass().getName(), "getAllLectures", e);
			return Collections.emptyList();
		}
	}
	@GetMapping(value = Constants.GET_STUDENT_LIST)
	public List<StudentDTO> getStudentList(@RequestParam("lecture_id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidTeacherException {
		try {
			if (type.equals(TYPE_TEACHER)) {
			return teacherService.getStudentList(id);
			}
			else return Collections.emptyList();
		} catch (InvalidLectureException e) {

			log.throwing(this.getClass().getName(), "getStudentList", e);
			return Collections.emptyList();
		}
	}
	@DeleteMapping(value = Constants.CANCEL_LECTURE)
	public String cancelLecture(@RequestParam("lecture_id") String lectureId,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException {
		try {
			if (type.equals(TYPE_TEACHER)) {
			return teacherService.cancelLecture(lectureId,id);
			}
			else return null;
		} catch (InvalidLectureException | InvalidCourseException e) {

			log.throwing(this.getClass().getName(), "cancelLecture", e);
			return e.getMessage();
		}
	}
	@DeleteMapping(value = Constants.CANCEL_PRESENCE_LECTURE)
	public String cancelPresenceLecture(@RequestParam("lecture_id") String lectureId,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException, ParseException,InvalidTeacherException {
		try {
			if (type.equals(TYPE_TEACHER)) {
			return teacherService.cancelLecture(lectureId,id);
			}
			else return null;
		} catch (InvalidLectureException | InvalidCourseException e) {

			log.throwing(this.getClass().getName(), "cancelLecture", e);
			return e.getMessage();
		}
	}
	@GetMapping(value = Constants.GET_PAST_LECTURES)
	public List<LectureDTO> getPastLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			//List<LectureDTO> l = teacherService.getPastLectures(id);			
			return teacherService.getPastLectures(id);
		}
		else return Collections.emptyList();

		} catch (InvalidTeacherException e) {
			log.throwing(this.getClass().getName(), "getPastLectures", e);
			return Collections.emptyList();
		}
	}
	@PostMapping(value = Constants.ADD_PRESENCE)
	public String addPresence(String lectureId, String studentId, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidStudentException{
		try {
			if (type.equals(TYPE_TEACHER)) {			
			return teacherService.addPresence(lectureId, studentId);
		}
		else return null;

		} catch (InvalidLectureException | InvalidStudentException e) {
			log.throwing(this.getClass().getName(), "addPresence", e);
			return e.getMessage();
		}
	}
}
