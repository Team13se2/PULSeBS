package team13.pulsbes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.services.BookingManagerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/booking_manager")
public class BookingManagerController {
	
	@Autowired
	BookingManagerService bookingService;
	
	@GetMapping(value = Constants.GET_ALL_LECTURES)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username, @CookieValue(value = "id") String id){
		return bookingService.getAllLectures();
	}
}
