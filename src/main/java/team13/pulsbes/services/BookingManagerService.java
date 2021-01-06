package team13.pulsbes.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidTeacherException;

public interface BookingManagerService {
	
	List<LectureDTO> getAllLectures ();
	public List<StudentDTO> getContactReportStudent(String studentId) throws InvalidStudentException;
	public File getContactReportStudentCSV(String studentId) throws InvalidStudentException, IOException;
	public List<StudentDTO> getContactReportTeacher(String studentId) throws InvalidTeacherException;
	public File getContactReportTeacherCSV(String studentId) throws InvalidTeacherException, IOException;
	
}
