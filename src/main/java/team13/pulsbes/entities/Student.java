package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {

    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;

    @ManyToOne
    @JoinColumn (name = "course_id")
    private Course course;


    public void setCourse (Course course1) {

        if (this.course == null) {
            course1.getStudents().add(this);
            this.course = course1;
            return;
        }
       if (course1 == null)
           this.course.getStudents().remove(this);
       else {
           this.course.getStudents().remove(this);
           course1.getStudents().add(this);
       }
        this.course = course1;

    }

}
