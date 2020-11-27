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
import java.text.ParseException;
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

	private static final String dateFormatString = "yyyy-MM-dd HH:mm";
	
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

		Calendar tmpCal = Calendar.getInstance();

		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(x -> { try { System.out.println(x.getStartTime2()); return x.getStartTime2().after(tmpCal.getTime()); } catch (ParseException e) {log.throwing(this.getClass().getName(), "getAllLectures", e); return false;} })
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
	public String cancelLecture(String lectureId, String TeacherId) throws InvalidLectureException, InvalidCourseException {
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}

		Teacher teacher = teacherRepository.findById(TeacherId).get();
		System.out.println(teacher.getEmail());
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();		
		tmpCal.add(Calendar.HOUR_OF_DAY, -1);

		
		try { if(tmpLecture.getStartTime2().before(tmpCal.getTime())) {

            teacher.removeLecture(tmpLecture);
            System.out.println(teacher.getLectures());
            lectureRepository.delete(tmpLecture);
            teacherRepository.save(teacher);
            teacherRepository.flush();

            //lectureRepository.delete(tmpLecture);


			return ("Lecture cancelled");

			}

			else return ("Lecture is too late to be cancelled");

		}

		catch (Exception e)

		{
			log.throwing(this.getClass().getName(), "cancelLecture", e);
			return e.getMessage();
		}

	}

	@Override
	public String cancelPresenceLecture(String lectureId, String TeacherId) throws InvalidLectureException, InvalidCourseException {
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException("Lecture can't be null");
		}

		Teacher teacher = teacherRepository.findById(TeacherId).get();
		System.out.println(teacher.getEmail());
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();		
		tmpCal.add(Calendar.MINUTE, -30);

		
		try { if(tmpLecture.getStartTime2().before(tmpCal.getTime())) {

            teacher.removeLecture(tmpLecture);
            System.out.println(teacher.getLectures());
            lectureRepository.delete(tmpLecture);
            teacherRepository.save(teacher);
            teacherRepository.flush();

            //lectureRepository.delete(tmpLecture);


			return ("Lecture was changd from in presence to online");

			}

			else return ("Lecture is too late to be changed");

		}

		catch (Exception e)

		{
			log.throwing(this.getClass().getName(), "cancelPresenceLecture", e);
			return e.getMessage();
		}

	}

	@Override
	public List<LectureDTO> getPastLectures(String id) throws InvalidTeacherException {
		if(id.equals("-1")) {
			throw new InvalidTeacherException("Teacher can't be null");
		}

		Calendar tmpCal = Calendar.getInstance();

		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(x -> { try { System.out.println(x.getEndTime2()); return x.getEndTime2().before(tmpCal.getTime()); } catch (ParseException e) {log.throwing(this.getClass().getName(), "getPastLectures", e); return false;} })
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}
}
