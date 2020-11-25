package team13.pulsbes.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.dtos.TeacherDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.entities.Course;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.TeacherService;
import team13.pulsbes.services.TeacherService;

import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired 
	ModelMapper modelMapper;
	@Autowired
	LectureRepository lectureRepository;
	@Autowired
	CourseRepository courseRepository;
	
	public void addRepo (TeacherRepository tr) {
		this.teacherRepository = tr;
	}
	public void addLectureRepo(LectureRepository lr) {
		this.lectureRepository = lr;
	}
	public void addMM (ModelMapper mm) {
		this.modelMapper = mm;
	}

	Logger log = Logger.getLogger("TeacherServiceImpl");

	@Override
	public Integer getNumberStudentsAttending(String id) throws InvalidLectureException{
		if (id.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		return lectureRepository.getOne(id).getStudents().size();
	}

	@Override
	public List<LectureDTO> getAllLectures(String id) throws InvalidTeacherException {
		if(id.equals("-1")) {
			throw new InvalidTeacherException("Teacher can't be null");
		}
		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(Objects::nonNull)
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentList(String id) throws InvalidLectureException {
		if(id.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		return  lectureRepository.getOne(id)
				.getStudents()
				.stream()
				.filter(Objects::nonNull)
				.map(l -> modelMapper.map(l,StudentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public String cancelLecture(String lectureId, String courseId) throws InvalidLectureException, InvalidCourseException {
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		if(courseId.equals("-1")) {
			throw new InvalidCourseException("Course can't be null");
		}

		Course tmpCourse = courseRepository.getOne(courseId);
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();
		tmpCal.add(Calendar.HOUR, 1);

		try { if(tmpLecture.getStartTime2().after(tmpCal.getTime())) {

			tmpCourse.cancelLecture(tmpLecture);
			System.out.print(tmpCourse.getLectures());
			courseRepository.save(tmpCourse);
			System.out.print(tmpCourse.getLectures());
			lectureRepository.flush();
			return ("Lecture cancelled");

			}

			else return ("Lecture is too soon to be cancelled");

		}

		catch (Exception e)

		{
			log.throwing(this.getClass().getName(), "cancelLecture", e);
			return ("Error");		
		}

	}

	@Override
	public String changeLectureType(String lectureId, String courseId) throws InvalidLectureException, InvalidCourseException {
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}
		if(courseId.equals("-1")) {
			throw new InvalidCourseException("Course can't be null");
		}

		Course tmpCourse = courseRepository.getOne(courseId);
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();
		tmpCal.add(Calendar.MINUTE, 30);

		try { if(tmpLecture.getStartTime2().after(tmpCal.getTime())) {

			tmpCourse.cancelLecture(tmpLecture);
			return ("Lecture in presence cancelled");

			}

			else return ("Lecture is too soon to be cancelled");

		}

		catch (Exception e)

		{
			log.throwing(this.getClass().getName(), "cancelLecture", e);
			return ("Error");		
		}

	}
}
