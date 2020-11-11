package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Course {

   @Id

   private String CourseId;

   private String name;

   @OneToMany (mappedBy = "course_id")
   List <Student> students;
   {
      students = new ArrayList<>();
   }

   @ManyToMany (mappedBy = "courses")
   List <Subject> subjects;
   {
      subjects = new ArrayList<>();
   }


}



