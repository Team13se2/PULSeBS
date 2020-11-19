package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import java.util.Objects;
import java.util.stream.Collectors;

import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.repositories.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired 
	ModelMapper modelMapper;

    @Autowired
    NotificationServiceImpl notificationService;
    
    public void addStudentRepo (StudentRepository sr) {
		this.studentRepository = sr;
	}
	public void addLectureRepo(LectureRepository lr) {
		this.lectureRepository = lr;
	}
    public void addNotificationService(NotificationServiceImpl ns) {
    	this.notificationService = ns;
    }
	public void addModelMapper(ModelMapper mm) {
		this.modelMapper = mm;
	}

    Logger log = Logger.getLogger("StudentServiceImpl");

    @Override
    public String bookLecture (String lectureId, String StudentId ) throws InvalidLectureException, InvalidStudentException{

        System.out.println("entrato");

        if(StudentId.equals("-1")) {
			throw new InvalidStudentException("Student can't be null");
        }

        Student currentStudent = studentRepository.getOne(StudentId);
        Optional<Lecture> lectureSelected = lectureRepository.findById(lectureId);
        
        if (!lectureSelected.isPresent()) {
            System.out.println("primo if");
            throw new InvalidLectureException("Lecture can't be null");
        }


        Integer availableSeats = lectureSelected.get().getAvailableSeat();

        if (availableSeats>0) {
            System.out.println("terzo if");
            try {
                System.out.println("1 try");
                lectureSelected.get().addStudentAttending(currentStudent);

            }
            catch (Exception e)

            {
                System.out.println("1 catch");
                log.throwing(this.getClass().getName(), "addStudentAttending", e);}

            lectureSelected.get().setAvailableSeat(availableSeats - 1);

            notificationService.sendMessage("lanarig@gmail.com","Booking confirmation","Booking succeed for " + lectureSelected.get().getSubjectName() + ".");

            return ("The lecture was correctly booked");
        }
        else {
            return ("The lecture has no more available seats, you will receive a mail if a spot opens up");
        }


        
    }

    @Override
	public List<LectureDTO> getAllLectures(String id) throws InvalidStudentException {
		if(id.equals("-1")) {
			throw new InvalidStudentException("Student can't be null");
        }
        
        List<Course> listCourse = studentRepository.getOne(id).getCourses();
        List<Lecture> listLecture = new ArrayList<>();


        for(Course tmpCourse : listCourse) {
            listLecture.addAll(tmpCourse.getLectures());
         }

		return  listLecture
				.stream()
				.filter(Objects::nonNull)
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}


}
