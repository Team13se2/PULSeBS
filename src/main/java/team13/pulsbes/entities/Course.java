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

   private String Id;

   private String name;

   @ManyToOne
   @JoinColumn (name = "teacher_id")
   private Teacher teacher;

   @OneToMany (mappedBy = "course")
   List <Student> students;
   {
      students = new ArrayList<>();
   }



}



