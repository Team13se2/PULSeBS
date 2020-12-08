package team13.pulsbes.serviceimpl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.TeacherRepository;
import team13.pulsbes.services.NotificationService;

@Service
public class OfficerService {
	
	@Autowired
	NotificationService notificationService;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	LectureRepository lectureRepository;
		
	public void addTeacher(Teacher t) {
		teacherRepository.save(t);
	}
	
	public void addLecture(Lecture l) throws ParseException {
		lectureRepository.save(l);
		Timer timer = new Timer();
		
		//Date notificationTime =  new Date();
		
		Date notificationTime = l.getStartTime2();
		notificationTime.setDate(notificationTime.getDate() - 1);
		notificationTime.setHours(23);
		Calendar now = Calendar.getInstance();
		
		//if(notificationTime.before(now.getTime())) {
			timer.schedule(new TimerTask() {
				@Override
			    public void run() {
			        //setEmail(emailContent, subject);
					
					notificationService.sendMessage(l.getTeacher().getEmail(), 
										"Students attending lecture of " + l.getSubjectName(),
										"Number of students attending the lecture is " + l.getStudents().size() + ".\n The lecture is scheduled at "+ l.getStartTime() + ".");
			    }
				}, notificationTime);
		//}
	}
	
}
	

