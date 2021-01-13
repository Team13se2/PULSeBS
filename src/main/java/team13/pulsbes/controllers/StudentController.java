package team13.pulsbes.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;

import team13.pulsbes.services.StudentService;
import team13.pulsbes.utils.Constants;


@RestController
@RequestMapping("/student")

public class StudentController {

	@Autowired
	StudentService studentService;

	Logger log = Logger.getLogger("StudentController");
	private static final String TYPE_STUDENT = "student";
	
	@GetMapping(value = Constants.GET_ALL_LECTURES)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id,@CookieValue(value = "type") String type) throws InvalidStudentException {
		try {
			if(type.equals(TYPE_STUDENT))
				return studentService.getAllLectures(id);
			else
				return Collections.emptyList();
		} catch (InvalidStudentException e) {
			log.throwing(this.getClass().getName(), "getAllLectures", e);
			return Collections.emptyList();
		}
	}

	@GetMapping(value = Constants.BOOK_LECTURE)
	public String bookLecture(@RequestParam("lecture_id") Integer l_id, @CookieValue(value = "username") String username, @CookieValue(value = "id") String s_id,@CookieValue(value = "type") String type) throws InvalidLectureException, InvalidStudentException {
		try {
			if(type.equals(TYPE_STUDENT))
				return studentService.bookLecture(l_id, s_id);
			else 
				return "null";
		} catch (InvalidStudentException | InvalidLectureException e) {
			log.throwing(this.getClass().getName(), "booklecture", e);
			return null;
		}
	}

	@GetMapping(value = Constants.GET_BOOKED_LECTURES)
	public List<LectureDTO> getBookedLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id,@CookieValue(value = "type") String type) {
		try {
			if(type.equals(TYPE_STUDENT))
				return studentService.getBookedLectures(id);
			else
				return Collections.emptyList();
		} catch (InvalidStudentException e) {
			log.throwing(this.getClass().getName(), "getBookedLectures", e);
			return Collections.emptyList();
		}
	}

	@DeleteMapping(value = Constants.CANCEL_LECTURE)
	public String deleteLecture(@RequestParam("lecture_id")Integer l_id, @CookieValue(value = "username") String username, @CookieValue(value = "id") String s_id,@CookieValue(value = "type") String type) {
		try {
			if(type.equals(TYPE_STUDENT))
				return studentService.deleteLecture(l_id, s_id);
			else 
				return "null";
		} catch (InvalidStudentException | InvalidLectureException e) {
			log.throwing(this.getClass().getName(), "deleteLecture", e);
			return e.getMessage();
		}

	}

	@GetMapping(value = Constants.GET_WAITING_LECTURES)
	public List<LectureDTO> getWaitingLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id,@CookieValue(value = "type") String type) {
		try {
			if(type.equals(TYPE_STUDENT))
				return studentService.getWaitingLectures(id);
			else
				return Collections.emptyList();
		} catch (InvalidStudentException e) {
			log.throwing(this.getClass().getName(), "getWaitingLectures", e);
			return Collections.emptyList();
		}
	}

	@GetMapping(value = Constants.STUDENT_TUTORIAL)
	public StreamingResponseBody studentTutorial() throws FileNotFoundException {
		File video = new File("videos/student_tutorial.mp4");
		try (final InputStream videoFileStream = new FileInputStream(video)){		
			return (os)->readAndWrite(videoFileStream, os);					
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
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