package team13.pulsbes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;

import team13.pulsbes.services.StudentService;
import team13.pulsbes.utils.Constants;


@RestController
@RequestMapping("/API/students")

public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = Constants.GET_ALL_LECTURES_S, method = RequestMethod.GET)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,@CookieValue(value = "id") String id) throws InvalidStudentException{
		try {
			return studentService.getAllLectures(id);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
    @RequestMapping(value = Constants.BOOK_LECTURE, method = RequestMethod.GET)
	public String bookLecture (@RequestParam("lecture_id") String l_id, @CookieValue(value = "username") String username,@CookieValue(value = "id") String s_id ) throws InvalidLectureException, InvalidStudentException{
		try {
			return studentService.bookLecture(l_id, s_id);
		} catch (InvalidStudentException | InvalidLectureException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}



}
