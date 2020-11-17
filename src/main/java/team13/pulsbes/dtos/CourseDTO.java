package team13.pulsbes.dtos;

import lombok.Data;

import javax.persistence.Id;

@Data
public class CourseDTO {

    @Id
    private String Id;

    private String name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
