package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;

import team13.pulsbes.services.StudentService;
import team13.pulsbes.repositories.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;
    
    String bookingSuccess = "The lecture was corrrectly booked";    
    String bookingFailure = "The lecture has no more available seats, retry later";

    Logger log = Logger.getLogger("StudentServiceImpl");
    
    ArrayList<Lecture> lectureList = new ArrayList<>();

    public StudentServiceImpl(){

		lectureList.add(0,new Lecture("analisi", 300, 0));

    }
    

    @Override
    public String bookLecture (String lectureId) throws InvalidLectureException{

        Student currentStudent = new Student("s123456", "Mario", "Pino");        
        Lecture lectureSelected = lectureList.stream().filter(lecture -> lectureId.equals(lecture.getId())).findAny().orElse(null);
        
        if (lectureSelected == null) {

            throw new InvalidLectureException("Lecture can't be null"); 

        }

        Integer availableSeats = lectureSelected.getAvailableSeat();

        if (availableSeats>0) {

            try {lectureSelected.addStudentAttending(currentStudent);} catch (Exception e) {log.throwing(this.getClass().getName(), "addStudentAttending", e);}

            lectureSelected.setAvailableSeat(availableSeats - 1);
            
            return (bookingSuccess);
        }
        else {
            return (bookingFailure);
        }

        
    }

    /*
    @Override
    public String bookLecture (LectureDTO lDTO) throws InvalidLectureException{

        Student currentStudent = new Student("s123456", "Mario", "Pino");        
        
        if (lDTO == null) {

            throw new InvalidLectureException("Lecture can't be null");            

        }
                
        Integer availableSeats = lDTO.getAvailableSeat();

        try {lectureSelected.addStudentAttending(currentStudent);} catch (Exception e) {log.throwing(this.getClass().getName(), "addStudentAttending", e);}

                lectureSelected.setBookedSeat(bookedSeats + 1);
                return (bookingSuccess);
            }
            else {
                return (bookingFailure);
            }
    }
    */

}
