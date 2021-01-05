package team13.pulsbes.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.StudentPresence;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;

public interface TeacherService {
	
	Integer getNumberStudentsAttending(Integer id) throws InvalidLectureException;
	Integer getNumberStudentsPresent(Integer id) throws InvalidLectureException;
	List<LectureDTO> getAllLectures(String id) throws InvalidTeacherException;
	List<StudentPresence> getStudentList(Integer id) throws InvalidLectureException, InvalidTeacherException;
	String cancelLecture(Integer lectureId, String teacherId) throws InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException;
	String cancelPresenceLecture(Integer lectureId, String teacherId) throws InvalidLectureException, InvalidCourseException, ParseException, InvalidTeacherException;
	List<LectureDTO> getPastLectures(String id) throws InvalidTeacherException;
	List<LectureDTO> getCurrentLectures(String id) throws InvalidTeacherException;
	List<LectureDTO> getDailyLectures(String id) throws InvalidTeacherException;
	String addPresence(Integer lectureId, String studentId)throws InvalidLectureException, InvalidStudentException;

}
