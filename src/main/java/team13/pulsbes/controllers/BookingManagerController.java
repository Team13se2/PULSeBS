package team13.pulsbes.controllers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.services.BookingManagerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/booking_manager")
public class BookingManagerController {
	
	@Autowired
	BookingManagerService bookingService;
	
	Logger log = Logger.getLogger("BookingManagerController");
	private static final String TYPE_BOOKING_MANAGER= "booking_manager";
	
	@GetMapping(value = Constants.GET_ALL_LECTURES)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id,@CookieValue(value = "type") String type){
		if(type.equals(TYPE_BOOKING_MANAGER))
			return bookingService.getAllLectures();
		else
			return Collections.emptyList();
	}
	@GetMapping(value = Constants.GET_CONTACT_REPORT)
	public List<StudentDTO> getContactReport(String studentId,@CookieValue(value = "type") String type) throws InvalidStudentException {
		if(type.equals(TYPE_BOOKING_MANAGER))
			return bookingService.getContactReport(studentId);
		else
			return Collections.emptyList();
	}
}
