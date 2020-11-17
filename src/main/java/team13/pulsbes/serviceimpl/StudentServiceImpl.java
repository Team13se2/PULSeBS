package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;

import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.repositories.TeacherRepository;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    NotificationServiceImpl notificationService;
    
    public void addStudentRepo (StudentRepository sr) {
		this.studentRepository = sr;
	}
	public void addLectureRepo(LectureRepository lr) {
		this.lectureRepository = lr;
	}
    
    String bookingSuccess = "The lecture was corrrectly booked";    
    String bookingFailure = "The lecture has no more available seats, you will receive a mail if a spot opens up";

    Logger log = Logger.getLogger("StudentServiceImpl");

    @Override
    public String bookLecture (String lectureId, String StudentId ) throws InvalidLectureException{

        System.out.println("entrato");

        Student currentStudent = studentRepository.getOne(StudentId);
        Lecture lectureSelected = lectureRepository.getOne(lectureId);
        
        if (lectureSelected == null) {
            System.out.println("primo if");
            throw new InvalidLectureException("Lecture can't be null"); 

        }


        Integer availableSeats = lectureSelected.getAvailableSeat();

        if (availableSeats>0) {
            System.out.println("terzo if");
            try {lectureSelected.addStudentAttending(currentStudent);} catch (Exception e) {log.throwing(this.getClass().getName(), "addStudentAttending", e);}

            lectureSelected.setAvailableSeat(availableSeats - 1);

            notificationService.sendMessage(currentStudent.getEmail(),"Booking confirmation","Booking succeed for " + lectureSelected.getSubjectName() + ".");

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
