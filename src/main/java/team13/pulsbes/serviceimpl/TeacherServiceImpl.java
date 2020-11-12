package team13.pulsbes.serviceimpl;

import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.services.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	
	
	@Override
	public Integer getNumberStudentsAttending(LectureDTO l) throws InvalidTeacherException{
		if (l == null) {
			throw new InvalidTeacherException("Lecture can't be null");
		}
		return l.getStudents().size();
	}

	@Override
	public List<Lecture> getAllLectures(TeacherDTO tDTO) throws InvalidTeacherException {
		if(tDTO == null) {
			throw new InvalidTeacherException("Teacher can't be null");
		}
		
		
		return null;
	}
	
	@Override
	public void mailForNumber(TeacherDTO tDto) {
		
		
	}

}
