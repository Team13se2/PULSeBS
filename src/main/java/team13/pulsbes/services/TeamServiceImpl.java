package team13.pulsbes.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.Calendar;

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
    public boolean addBook(LectureDTO lecture) {
        return false;
    }
}
