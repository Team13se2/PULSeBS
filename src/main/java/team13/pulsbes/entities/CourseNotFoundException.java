package team13.pulsbes.entities;

import team13.pulsbes.services.TeamServiceException;

public class CourseNotFoundException extends TeamServiceException {

    public CourseNotFoundException (String courseName) {System.out.println(courseName + "not found");}
}
