package team13.pulsbes.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher {

    @Id

    private String id;

    private String name;

    private String surname;

    private String Email;

    @OneToMany (mappedBy = "teacher")
    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }

    @OneToMany (mappedBy = "teacher")
    List <Course> courses;
    {
        courses = new ArrayList<>();
    }


    public int addLecture (Lecture lecture)
    {
        lectures.add(lecture);
        lecture.setTeacher(this);
        return lectures.indexOf(lecture);
    }
}
