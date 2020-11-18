package team13.pulsbes.services;

import team13.pulsbes.exception.InvalidLectureException;

public interface StudentService {

    String bookLecture (String lectureId,String StudentId) throws InvalidLectureException;

}
