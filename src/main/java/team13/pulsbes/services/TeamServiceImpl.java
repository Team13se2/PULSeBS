package team13.pulsbes.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.CourseNotFoundException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamServiceImpl implements TeamService{

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;



    @Override
    public boolean addLecture(LectureDTO lecture) {
        return false;
    }

    @Override
    public List<LectureDTO> getAllLecturesByDay(String day, String courseId) throws CourseNotFoundException {

        if(!courseRepository.existsById(courseId))
            throw new CourseNotFoundException(courseId);

       return courseRepository
               .getOne(courseId)
               .getLectures()
               .stream()
               .filter(l -> l.getStartTime().getDay().equals(day))
               .map(l -> modelMapper.map(l,LectureDTO.class))
               .collect(Collectors.toList());

    }

    @Override
    public boolean addBook(LectureDTO lecture) {
        return false;
    }
}
