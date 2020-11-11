package team13.pulsbes.serviceimpl;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	@Override
	public Integer getStudentsAttending(LectureDTO l) throws InvalidTeacherException{
		if (l == null) {
			throw new InvalidTeacherException("Lecture can't be null");
		}
		return l.getStudents().size();
	}

	@Override
	public void mailForNumber(TeacherDTO tDto) {
		
		
	}
	
}
