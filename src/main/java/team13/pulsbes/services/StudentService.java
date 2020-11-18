package team13.pulsbes.services;

import java.util.List;

import team13.pulsbes.dtos.LectureDTO;

import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;

public interface StudentService {

    String bookLecture (String lectureId,String StudentId) throws InvalidLectureException;
    List<LectureDTO> getAllLectures(String id) throws InvalidStudentException;

}
