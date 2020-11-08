package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Course {

@Id
    private String name;




}



