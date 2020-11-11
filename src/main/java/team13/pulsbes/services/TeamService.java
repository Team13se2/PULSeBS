package team13.pulsbes.services;


import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Course;

import javax.transaction.Transactional;
import java.util.List;


public interface TeamService {


boolean addLecture (LectureDTO lecture);

boolean addBook (LectureDTO lecture);











}
