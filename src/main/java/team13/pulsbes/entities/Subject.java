package team13.pulsbes.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subject {

  @Id

  private String SubjectId;

  private String Name;

  private String TeacherId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="subject_course", joinColumns = @JoinColumn(name="subject_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))

    List<Course> courses;
    {
        courses = new ArrayList<>();
    }




}
