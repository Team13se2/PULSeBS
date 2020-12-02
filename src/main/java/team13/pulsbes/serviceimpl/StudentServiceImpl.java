package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.logging.Logger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidStudentException;
import java.text.ParseException;
import java.util.stream.Collectors;

import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.services.StudentService;
import team13.pulsbes.repositories.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NotificationServiceImpl notificationService;

    public void addStudentRepo(StudentRepository sr) {
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

    private static final String STUDENT_NULL = "Student can't be null";
    private static final String STUDENT_NOT_FOUND = "Student not found";

    @Override
    public String bookLecture(String lectureId, String studentId) throws InvalidLectureException, InvalidStudentException {

        log.info("entrato");

        if (studentId.equals("-1")) {
            throw new InvalidStudentException(STUDENT_NULL);
        }

        Student currentStudent = studentRepository.getOne(studentId);
        Optional<Lecture> lectureSelected = lectureRepository.findById(lectureId);

        if (!lectureSelected.isPresent()) {
            throw new InvalidLectureException("Lecture can't be null");
        }


        Integer availableSeats = lectureSelected.get().getAvailableSeat();

        if (availableSeats > 0) {
            log.info("terzo if");
            try {                
                lectureSelected.get().addStudentAttending(currentStudent);

            } catch (Exception e) {
                log.throwing(this.getClass().getName(), "addStudentAttending", e);
            }

            lectureSelected.get().setAvailableSeat(availableSeats - 1);
            currentStudent.addBookLecture(lectureSelected.get());
            studentRepository.save(currentStudent);
            lectureRepository.save(lectureSelected.get());
            notificationService.sendMessage(currentStudent.getEmail(),           								
            								"Booking confirmation for "+ lectureSelected.get().getSubjectName(), 
            								"Dear " + currentStudent.getName() + currentStudent.getSurname() + " \n" +
            								"Booking succeed for " + lectureSelected.get().getSubjectName() + 
            								". \n The lecture is scheduled at: " + lectureSelected.get().getStartTime() + " and will take place in room: " + lectureSelected.get().getRoomName()+
            								". \n Best regards, \n" + lectureSelected.get().getTeacher().getName() + " " + lectureSelected.get().getTeacher().getSurname());

            return ("The lecture was correctly booked");
        } else {
            return ("The lecture has no more available seats, you will receive a mail if a spot opens up");
        }


    }

    @Override
    public List<LectureDTO> getAllLectures(String id) throws InvalidStudentException {
        if (id.equals("-1")) {
            throw new InvalidStudentException(STUDENT_NULL);
        }
        
        if (!studentRepository.existsById(id))
            throw new InvalidStudentException(STUDENT_NOT_FOUND);

        List<Course> listCourse = studentRepository.getOne(id).getCourses();
        List<Lecture> listLecture = new ArrayList<>();
        Calendar tmpCal = Calendar.getInstance();



        for (Course tmpCourse : listCourse) {
            listLecture.addAll(tmpCourse.getLectures());
        }

        return listLecture
                .stream()
                .filter(x -> { { try { return x.getStartTime2().after(tmpCal.getTime()) && !studentRepository.getOne(id).getBookedLectures().contains(x); } catch (ParseException e) {log.throwing(this.getClass().getName(), "getAllLectures", e); return false;} } })
                .map(l -> modelMapper.map(l, LectureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> getBookedLectures(String id) throws InvalidStudentException {
        if (id.equals("-1")) {
            throw new InvalidStudentException(STUDENT_NULL);
        }
        if (!studentRepository.existsById(id))
            throw new InvalidStudentException(STUDENT_NOT_FOUND);

        Calendar tmpCal = Calendar.getInstance();


        return studentRepository.getOne(id)
                .getBookedLectures()
                .stream()
                .filter(x -> { try { return x.getStartTime2().after(tmpCal.getTime()); } catch (ParseException e) {log.throwing(this.getClass().getName(), "getAllLectures", e); return false;} })
                .map(l -> modelMapper.map(l, LectureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteLecture(String lectureId, String studentId) throws InvalidLectureException, InvalidStudentException {

        if (!studentRepository.existsById(studentId)) {
            throw new InvalidStudentException(STUDENT_NOT_FOUND);
        }

        if (!lectureRepository.existsById(lectureId)) {
            throw new InvalidLectureException("Lecture not found");
        }

        Optional<Student> optStudent = studentRepository.findById(studentId);

        if (!optStudent.isPresent()) {
            throw new InvalidStudentException(STUDENT_NULL);
        }

        Student currentStudent = optStudent.get();

        //Student currentStudent = studentRepository.findById(studentId).get();
        Lecture deletingLecture = lectureRepository.getOne(lectureId);


        if (currentStudent.getBookedLectures().contains(deletingLecture)) {
            try {

                //System.out.println(currentStudent.getBookedLectures());
                currentStudent.removeBookedLecture(deletingLecture);
                studentRepository.saveAndFlush(currentStudent);


            } catch (Exception e) {
                log.info("Student has no this lecture booked");
                log.throwing(this.getClass().getName(), "getBookedLectures", e);
            }

            return "Lecture deleted";
        } else {
            return "Student doesn't have this lecture";
        }
    }
}

