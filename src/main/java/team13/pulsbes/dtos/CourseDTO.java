package team13.pulsbes.dtos;

import lombok.Data;

import javax.persistence.Id;

@Data
public class CourseDTO {

    @Id
    private String Id;

    private String name;

    private String TeacherId;
}
