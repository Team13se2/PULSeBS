package team13.pulsbes.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.services.BookingManagerService;

@Service
public class BookingManagerServiceImpl implements BookingManagerService{

	@Autowired
	LectureRepository lectureRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<LectureDTO> getAllLectures() {      
		return lectureRepository.findAll().stream().map(l->modelMapper.map(l,LectureDTO.class)).collect(Collectors.toList());
	}
	
}
