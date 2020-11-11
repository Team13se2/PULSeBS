package team13.pulsbes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PulsbesApplication {

	 @Bean
	 ModelMapper modelMapper() {
	 	return new ModelMapper();
	 }

	public static void main(String[] args) {
		SpringApplication.run(PulsbesApplication.class, args);
	}
	@PostConstruct
	public void setupDbWithData() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/seats", "sa", "password");
		conn.close();
	}

}
