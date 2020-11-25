package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidCourseException;

public interface TeacherService {
	
	Integer getNumberStudentsAttending(String id) throws InvalidLectureException;
	List<LectureDTO> getAllLectures(String id) throws InvalidTeacherException;
	List<StudentDTO> getStudentList(String id) throws InvalidLectureException;
	String cancelLecture(String lectureId, String courseId) throws InvalidLectureException, InvalidCourseException;
	String changeLectureType(String lectureId, String courseId) throws InvalidLectureException, InvalidCourseException;
}
