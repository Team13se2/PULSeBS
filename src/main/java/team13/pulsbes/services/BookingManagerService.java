package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.exception.InvalidStudentException;

public interface BookingManagerService {
	
	List<LectureDTO> getAllLectures ();
	public List<StudentDTO> getContactReport(String studentId) throws InvalidStudentException;
	
}
