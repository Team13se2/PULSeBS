package team13.pulsbes.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.LectureIdStudentId;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.StudentPresence;
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
	public Integer getNumberStudentsAttending(@RequestParam("lecture_id") Integer id, @CookieValue(value = "type") String type) throws InvalidLectureException {
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
	@GetMapping(value = Constants.GET_NUMBER_STUDENTS_PRESENT)
	public Integer getNumberStudentsPresent(@RequestParam("lecture_id") Integer id, @CookieValue(value = "type") String type) throws InvalidLectureException {
		try {
			if (type.equals(TYPE_TEACHER)) {
				return teacherService.getNumberStudentsPresent(id);
			}
			else return 0;

		} catch (InvalidLectureException e) {

			log.throwing(this.getClass().getName(), "getNumberStudentsPresent", e);
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
	public List<StudentPresence> getStudentList(@RequestParam("lecture_id") Integer id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidTeacherException {
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
	public String cancelLecture(@RequestParam("lecture_id") Integer lectureId,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException {
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
	public String cancelPresenceLecture(@RequestParam("lecture_id") Integer lectureId,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidCourseException, ParseException,InvalidTeacherException {
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
	@GetMapping(value = Constants.GET_CURRENT_LECTURES)
	public List<LectureDTO> getCurrentLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			//List<LectureDTO> l = teacherService.getPastLectures(id);			
			return teacherService.getCurrentLectures(id);
		}
		else return Collections.emptyList();

		} catch (InvalidTeacherException e) {
			log.throwing(this.getClass().getName(), "getCurrentLectures", e);
			return Collections.emptyList();
		}
	}
	@GetMapping(value = Constants.GET_DAILY_LECTURES)
	public List<LectureDTO> getDailyLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) throws InvalidTeacherException{
		try {
			if (type.equals(TYPE_TEACHER)) {
			//List<LectureDTO> l = teacherService.getPastLectures(id);			
			return teacherService.getDailyLectures(id);
		}
		else return Collections.emptyList();

		} catch (InvalidTeacherException e) {
			log.throwing(this.getClass().getName(), "getDailyLectures", e);
			return Collections.emptyList();
		}
	}
	@PostMapping(value = Constants.ADD_PRESENCE)
	public String addPresence(@RequestBody LectureIdStudentId lectureIdStudentId, @CookieValue(value = "type") String type) throws InvalidLectureException, InvalidStudentException{
		try {
			if (type.equals(TYPE_TEACHER)) {			
			return teacherService.addPresence(lectureIdStudentId.getLectureId(),lectureIdStudentId.getStudentId());
		}
		else return null;

		} catch (InvalidLectureException | InvalidStudentException e) {
			log.throwing(this.getClass().getName(), "addPresence", e);
			return e.getMessage();
		}
	}
	@GetMapping(value = Constants.TEACHER_TUTORIAL)
	public StreamingResponseBody teacherTutorial() throws FileNotFoundException {
		File video = new File("videos\\teacher_tutorial.mp4");		
		final InputStream videoFileStream = new FileInputStream(video);
		
		return (os)->readAndWrite(videoFileStream, os);		
		
	}
	private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
	    byte[] data = new byte[2048];
	    int read = 0;
	    while ((read = is.read(data)) > 0) {
	        os.write(data, 0, read);
	    }
	    os.flush();
	}
}
