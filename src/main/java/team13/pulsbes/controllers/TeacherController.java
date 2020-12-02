package team13.pulsbes.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
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

	Logger log = Logger.getLogger("TeacherController");
	private static final String TYPE_TEACHER = "teacher";

	@RequestMapping(value = Constants.GET_NUMBER_STUDENTS_ATTENDING,method = RequestMethod.GET)
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
	@RequestMapping(value = Constants.GET_ALL_LECTURES, method = RequestMethod.GET)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			List<LectureDTO> l = teacherService.getAllLectures(id);
			for(int i=0;i<l.size();i++){
				Integer nr = teacherService.getNumberStudentsAttending(l.get(i).getId());
				l.get(i).setNrStudents(nr);
			}
			return l;
		}
		else return null;

		} catch (InvalidTeacherException | InvalidLectureException e) {
			log.throwing(this.getClass().getName(), "getAllLectures", e);
			return null;
		}
	}
	@RequestMapping(value = Constants.GET_STUDENT_LIST,method = RequestMethod.GET)
	public List<StudentDTO> getStudentList(@RequestParam("lecture_id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException {
		try {
			if (type.equals(TYPE_TEACHER)) {
			return teacherService.getStudentList(id);
			}
			else return null;
		} catch (InvalidLectureException e) {

			log.throwing(this.getClass().getName(), "getStudentList", e);
			return null;
		}
	}
	@RequestMapping(value = Constants.CANCEL_LECTURE,method = RequestMethod.DELETE)
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
	@RequestMapping(value = Constants.CANCEL_PRESENCE_LECTURE,method = RequestMethod.DELETE)
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
	@RequestMapping(value = Constants.GET_PAST_LECTURES, method = RequestMethod.GET)
	public List<LectureDTO> getPastLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			List<LectureDTO> l = teacherService.getPastLectures(id);			
			return l;
		}
		else return null;

		} catch (InvalidTeacherException e) {
			log.throwing(this.getClass().getName(), "getPastLectures", e);
			return null;
		}
	}
}
