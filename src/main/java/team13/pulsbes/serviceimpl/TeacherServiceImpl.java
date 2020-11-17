package team13.pulsbes.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.services.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired 
	ModelMapper modelMapper;
	@Autowired
	LectureRepository lectureRepository;
	
	public void addRepo (TeacherRepository tr) {
		this.teacherRepository = tr;
	}
	public void addMM (ModelMapper mm) {
		this.modelMapper = mm;
	}
	@Override
	public Integer getNumberStudentsAttending(String id) throws InvalidLectureException{
		if (id == null) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		return lectureRepository.getOne(id).getStudents().size();
	}

	@Override
	public List<LectureDTO> getAllLectures(String id) throws InvalidTeacherException {
		if(id.equals("-1")) {
			throw new InvalidTeacherException("Teacher can't be null");
		}
		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(Objects::nonNull)
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}
}
