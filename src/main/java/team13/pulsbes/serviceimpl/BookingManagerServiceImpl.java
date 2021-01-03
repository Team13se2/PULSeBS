package team13.pulsbes.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidStudentException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
import team13.pulsbes.services.BookingManagerService;

@Service
public class BookingManagerServiceImpl implements BookingManagerService{

	@Autowired
	LectureRepository lectureRepository;
	@Autowired
    StudentRepository studentRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public void addLectureRepository(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	public void addModelMapper(ModelMapper mm) {
		this.modelMapper = mm;
	}
	public void addStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	Logger log = Logger.getLogger("StudentServiceImpl");

    private static final String STUDENT_NULL = "Student can't be null";
	private static final String STUDENT_NOT_FOUND = "Student not found";
	
	@Override
	public List<LectureDTO> getAllLectures() {      
		return lectureRepository.findAll().stream().map(l->modelMapper.map(l,LectureDTO.class)).collect(Collectors.toList());
	}
	@Override
	public List<StudentDTO> getContactReport(String studentId) throws InvalidStudentException {
		if (studentId.equals("-1")) {
            throw new InvalidStudentException(STUDENT_NULL);
        }
        
        if (!studentRepository.existsById(studentId))
			throw new InvalidStudentException(STUDENT_NOT_FOUND);
			
		List<Course> listCourse = studentRepository.getOne(studentId).getCourses();
		List<Lecture> listLecture = new ArrayList<>();
		List<Student> listStudent = new ArrayList<>();
		Calendar tmpCal = Calendar.getInstance();
		tmpCal.add(Calendar.DAY_OF_MONTH, -14);


		for (Course tmpCourse : listCourse) {
			lectureRepository.findAll().forEach(l -> {
				if (l.getCode().equals(tmpCourse.getCode())) {
					listLecture.add(l);
				}
			});
		}
		List<Lecture> listAttendedLecture = listLecture.stream()
                .filter(x -> { { try { return x.getStartTime2().after(tmpCal.getTime()) 
                    && studentRepository.getOne(studentId).getAttendedLectures().contains(x); } 
					catch (ParseException e) {log.throwing(this.getClass().getName(), "getContactReport", e); return false;} } })
				.collect(Collectors.toList());
				

		for (Lecture tmpLecture : listAttendedLecture) {
			listStudent.addAll(tmpLecture.getStudentsPresent());
		}

		return listStudent
                .stream()
                .distinct()
                .map(l -> modelMapper.map(l, StudentDTO.class))
                .collect(Collectors.toList());            

	}
	@Override
	public File getContactReportStudent(String studentId) throws InvalidStudentException, IOException {
		if (studentId.equals("-1")) {
            throw new InvalidStudentException(STUDENT_NULL);
        }
        
        if (!studentRepository.existsById(studentId))
			throw new InvalidStudentException(STUDENT_NOT_FOUND);
			
		List<Course> listCourse = studentRepository.getOne(studentId).getCourses();
		List<Lecture> listLecture = new ArrayList<>();
		List<Student> listStudent = new ArrayList<>();
		List<StudentDTO> listStudentDTO = new ArrayList<>();		
		Calendar tmpCal = Calendar.getInstance();
		String fileName = String.valueOf(tmpCal.get(Calendar.YEAR)) + "_" + String.valueOf(tmpCal.get(Calendar.MONTH)) + "_" + String.valueOf(tmpCal.get(Calendar.DATE)) + "_" + studentId + ".csv";
		tmpCal.add(Calendar.DAY_OF_MONTH, -14);


		for (Course tmpCourse : listCourse) {
			lectureRepository.findAll().forEach(l -> {
				if (l.getCode().equals(tmpCourse.getCode())) {
					listLecture.add(l);
				}
			});
		}
		List<Lecture> listAttendedLecture = listLecture.stream()
                .filter(x -> { { try { return x.getStartTime2().after(tmpCal.getTime()) 
                    && studentRepository.getOne(studentId).getAttendedLectures().contains(x); } 
					catch (ParseException e) {log.throwing(this.getClass().getName(), "getContactReport", e); return false;} } })
				.collect(Collectors.toList());
				

		for (Lecture tmpLecture : listAttendedLecture) {
			listStudent.addAll(tmpLecture.getStudentsPresent());
		}

		listStudentDTO = listStudent
                .stream()
                .distinct()
                .map(l -> modelMapper.map(l, StudentDTO.class))
				.collect(Collectors.toList());

		File file = new File("../"+fileName);
		if (!file.exists()) {
			file.createNewFile();
		 }
				
		final char CSV_SEPARATOR = ',';

			BufferedWriter writer = new BufferedWriter(new FileWriter(file)); 
			listStudentDTO.forEach(student -> {
					try {
						writer.append(student.getId()).append(CSV_SEPARATOR)
							  .append(student.getName()).append(CSV_SEPARATOR)
							  .append(student.getSurname()).append(CSV_SEPARATOR)
							  .append(student.getCity()).append(CSV_SEPARATOR)
							  .append(student.getBirthday()).append(CSV_SEPARATOR)
							  .append(student.getSSN()).append(CSV_SEPARATOR)
							  .append(student.getEmail()).append(System.lineSeparator());
						writer.close();
					} catch (IOException e) {						
						e.printStackTrace();						
				}
				finally
				{ 
	   			try{
	     	 	if(writer!=null)
		 		writer.close();
	   			}catch(Exception e){
				log.throwing(this.getClass().getName(), "getContactReportStudent", e);
	    	}
		}
		});
			
		
		return file;

	}
	
}
