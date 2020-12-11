package team13.pulsbes.allTestBookingManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.serviceimpl.BookingManagerServiceImpl;

class TestGetAllLectures {

	@Autowired
	BookingManagerServiceImpl bookingService;
	@Autowired
	LectureRepository lectureRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		lectureRepository = mock(LectureRepository.class);
		modelMapper = mock(ModelMapper.class);
		bookingService = new BookingManagerServiceImpl();
		bookingService.addLectureRepository(lectureRepository);
		bookingService.addModelMapper(modelMapper);
	}
	
	@Test
	void testGetAllLectures() {
		Lecture l = new Lecture();
		LectureDTO lDto = new LectureDTO();
		Course c = new Course();
		List<Lecture> lectures = new ArrayList<>();
		l.setId("1");
		l.setSubjectName(c.getName());
		l.setAvailableSeat(50);
		l.setTotalSeat(50);
		l.setRoomName("Aula 1");
		
		lDto.setId("1");
		lDto.setSubjectName(c.getName());
		lDto.setAvailableSeat(50);
		lDto.setTotalSeat(50);
		lDto.setLectureType("Lab");
		lDto.setSurnameString("Torchiano");
		lDto.setRoomName("Aula 1");
		
		lectures.add(l);
		when(lectureRepository.findAll()).thenReturn(lectures);
		when(modelMapper.map(any(), any())).thenReturn(lDto);
		
		assertEquals(l.getId(),  bookingService.getAllLectures().get(0).getId());
		assertEquals(l.getSubjectName(),  bookingService.getAllLectures().get(0).getSubjectName());
		assertEquals(l.getAvailableSeat(),  bookingService.getAllLectures().get(0).getAvailableSeat());
		assertEquals(l.getTotalSeat(),  bookingService.getAllLectures().get(0).getTotalSeat());
		assertEquals(l.getRoomName(),  bookingService.getAllLectures().get(0).getRoomName());
	}

}
