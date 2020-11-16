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
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.services.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired 
	ModelMapper modelMapper;
	
	@Override
	public Integer getNumberStudentsAttending(LectureDTO l) throws InvalidLectureException{
		System.out.println("diocane");
		if (l == null) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		
		System.out.println(l.getStudents().size());
		return l.getStudents().size();
	}

	@Override
	public List<Lecture> getAllLectures(String id) throws InvalidTeacherException {
		if(id.equals(-1)) {
			throw new InvalidTeacherException("Teacher can't be null");
		}
		Teacher t = teacherRepository.getOne(id);
		return t.getLectures();
	}
	
	@Override
	public void mailForNumber(TeacherDTO tDto) {
		
		
	}

}
