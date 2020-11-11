package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidTeacherException;

public interface TeacherService {
	
	Integer getStudentsAttending(LectureDTO l) throws InvalidTeacherException;
	void mailForNumber(TeacherDTO tDto);
}
