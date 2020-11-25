package team13.pulsbes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = Constants.GET_ALL_LECTURES, method = RequestMethod.GET)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id) throws InvalidStudentException {
		try {
			return studentService.getAllLectures(id);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = Constants.BOOK_LECTURE, method = RequestMethod.GET)
	public String bookLecture(@RequestParam("lecture_id") String l_id, @CookieValue(value = "username") String username, @CookieValue(value = "id") String s_id) throws InvalidLectureException, InvalidStudentException {
		try {
			return studentService.bookLecture(l_id, s_id);
		} catch (InvalidStudentException | InvalidLectureException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = Constants.GET_BOOKED_LECTURES, method = RequestMethod.GET)
	public List<LectureDTO> getBookedLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id) {
		try {
			return studentService.getBookedLectures(id);
		} catch (InvalidStudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	@DeleteMapping(value = Constants.CANCEL_LECTURE)
	public String deleteLecture(@RequestParam("lecture_id") String l_id, @CookieValue(value = "username") String username, @CookieValue(value = "id") String s_id) {

		try {

			return studentService.deleteLecture(l_id, s_id);
		} catch (InvalidStudentException | InvalidLectureException e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}

	}
}