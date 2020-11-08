package team13.pulsbes.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Lecture {

    @Id
    private String LectureName;

    private String StartTime;

    private String EndTime;

    private String Location;

    private String LectureType;

    private String SurnameString;



}
