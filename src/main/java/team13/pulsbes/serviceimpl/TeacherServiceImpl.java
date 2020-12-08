package team13.pulsbes.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.entities.Student;
import team13.pulsbes.exception.InvalidLectureException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.exception.InvalidCourseException;
import java.text.ParseException;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.TeacherService;

import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;
import java.util.Objects;
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
	@Autowired
    NotificationServiceImpl notificationService;	
	
	public void addRepo (TeacherRepository tr) {
		this.teacherRepository = tr;
	}
	public void addLectureRepo(LectureRepository lr) {
		this.lectureRepository = lr;
	}
	public void addMM (ModelMapper mm) {
		this.modelMapper = mm;
	}
	public void addNotificationService(NotificationServiceImpl ns) {
        this.notificationService = ns;
    }

	Logger log = Logger.getLogger("TeacherServiceImpl");

	private static final String LECTURE_NULL = "Lecture can't be null";
	private static final String TEACHER_NULL = "Teacher can't be null";

	@Override
	public Integer getNumberStudentsAttending(String id) throws InvalidLectureException{
		if (id.equals("-1")) {
			throw new InvalidLectureException(LECTURE_NULL);
		}
		return lectureRepository.getOne(id).getStudents().size();
	}

	@Override
	public List<LectureDTO> getAllLectures(String id) throws InvalidTeacherException {
		if(id.equals("-1")) {
			throw new InvalidTeacherException(TEACHER_NULL);
		}

		Calendar tmpCal = Calendar.getInstance();

		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(x -> { try { return x.getStartTime2().after(tmpCal.getTime())
					&& x.isBookable(); } 
					catch (ParseException e) {log.throwing(this.getClass().getName(), "getAllLectures", e); return false;} })
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDTO> getStudentList(String id) throws InvalidLectureException {
		if(id.equals("-1")) {
			throw new InvalidLectureException(LECTURE_NULL);
		}
		return  lectureRepository.getOne(id)
				.getStudents()
				.stream()
				.filter(Objects::nonNull)
				.map(l -> modelMapper.map(l,StudentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public String cancelLecture(String lectureId, String teacherId) throws InvalidLectureException, InvalidCourseException, InvalidTeacherException{
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException(LECTURE_NULL);
		}

		Optional<Teacher> optTeacher = teacherRepository.findById(teacherId);

        if (!optTeacher.isPresent()) {
            throw new InvalidTeacherException(TEACHER_NULL);
        }

        Teacher teacher = optTeacher.get();

		//Teacher teacher = teacherRepository.findById(TeacherId).get();
		log.info(teacher.getEmail());
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();		
		tmpCal.add(Calendar.HOUR_OF_DAY, -1);
		tmpCal.add(Calendar.MONTH, 1);
		
		try { 
			if(tmpLecture.getStartTime2().before(tmpCal.getTime())) {
		
			List<Student> listStudent = tmpLecture.getStudents();
			tmpLecture.setBookable(false);
			lectureRepository.save(tmpLecture);
			lectureRepository.flush();
			/*teacher.removeLecture(tmpLecture);			
            //System.out.println(teacher.getLectures());
            lectureRepository.delete(tmpLecture);
            teacherRepository.save(teacher);
			teacherRepository.flush();*/

			for (Student tmpStudent : listStudent) {

				
				notificationService.sendMessage(tmpStudent.getEmail(),
						"Lecture cancel notification",
						"Dear "+tmpStudent.getName()+" "+tmpStudent.getSurname()+ ", \n" +
						"The lecture of " + tmpLecture.getSubjectName() + " schedlued for " + tmpLecture.getStartTime() +" has been canceled. \n" +
						"Best regards, \n" + teacher.getName() + " " + teacher.getSurname());

			}			
			
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
	public String cancelPresenceLecture(String lectureId, String teacherId) throws InvalidLectureException, InvalidCourseException, InvalidTeacherException{
		if(lectureId.equals("-1")) {
			throw new InvalidLectureException(LECTURE_NULL);
		}

		Optional<Teacher> optTeacher = teacherRepository.findById(teacherId);

        if (!optTeacher.isPresent()) {
            throw new InvalidTeacherException(TEACHER_NULL);
        }

        Teacher teacher = optTeacher.get();

		//Teacher teacher = teacherRepository.findById(TeacherId).get();
		log.info(teacher.getEmail());
		Lecture tmpLecture = lectureRepository.getOne(lectureId);
		Calendar tmpCal = Calendar.getInstance();		
		tmpCal.add(Calendar.MINUTE, -30);
		tmpCal.add(Calendar.MONTH, 1);

		
		try { if(tmpLecture.getStartTime2().before(tmpCal.getTime())) {
			
			List<Student> listStudent = tmpLecture.getStudents();
			tmpLecture.setBookable(false);
			lectureRepository.save(tmpLecture);
			lectureRepository.flush();
			/*teacher.removeLecture(tmpLecture);
            //System.out.println(teacher.getLectures());
            lectureRepository.delete(tmpLecture);
            teacherRepository.save(teacher);
			teacherRepository.flush();*/
			
			for (Student tmpStudent : listStudent) {
				
				notificationService.sendMessage(tmpStudent.getEmail(),
						"Lecture change notification",
						"Dear "+tmpStudent.getName()+" "+tmpStudent.getSurname()+ ", \n" +
						"The lecture of " + tmpLecture.getSubjectName() + " schedlued for " + tmpLecture.getStartTime() +" will take place on the virtual platform. \n" +
						"Best regards, \n" + teacher.getName() + " " + teacher.getSurname());

			}	

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
			throw new InvalidTeacherException(TEACHER_NULL);
		}

		Calendar tmpCal = Calendar.getInstance();

		return  teacherRepository.getOne(id)
				.getLectures()
				.stream()
				.filter(x -> { try { return x.getEndTime2().before(tmpCal.getTime())
					&& x.isBookable(); } 
					catch (ParseException e) {log.throwing(this.getClass().getName(), "getPastLectures", e); return false;} })
				.map(l -> modelMapper.map(l,LectureDTO.class))
				.collect(Collectors.toList());
	}
}
