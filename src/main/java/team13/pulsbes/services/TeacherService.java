package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;

public interface TeacherService {
	
	Integer getNumberStudentsAttending(LectureDTO l) throws InvalidLectureException;
	List<Lecture> getAllLectures(TeacherDTO tDTO) throws InvalidTeacherException;
	void mailForNumber(TeacherDTO tDto);
}
