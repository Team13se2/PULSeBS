package team13.pulsbes.exception;

import team13.pulsbes.services.TeamServiceException;

public class CourseNotFoundException extends TeamServiceException {

    public CourseNotFoundException (String courseName) {System.out.println(courseName + "not found");}
}
