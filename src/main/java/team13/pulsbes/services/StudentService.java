package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;

import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;

public interface StudentService {

    String bookLecture (String lectureId,String studentId) throws InvalidLectureException, InvalidStudentException;
    public String delayedbookLecture(String lectureId, String studentId) throws InvalidLectureException, InvalidStudentException;
    List<LectureDTO> getAllLectures(String id) throws InvalidStudentException;
	List<LectureDTO> getBookedLectures(String id)throws InvalidStudentException;
	String deleteLecture (String lectureId,String studentId) throws InvalidLectureException,InvalidStudentException;
    void updatequeue(String lectureId) throws InvalidStudentException, InvalidLectureException;
}
