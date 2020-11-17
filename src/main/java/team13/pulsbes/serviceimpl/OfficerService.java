package team13.pulsbes.serviceimpl;

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
	
	public void addLecture(Lecture l) {
		lectureRepository.save(l);
		Timer timer = new Timer();
		Date notificationTime = l.getStartTime();
		notificationTime.setHours(notificationTime.getHours()-1);
		
		//notificationTime.add(Calendar.HOUR_OF_DAY, -1);
		
		timer.schedule(new TimerTask() {
			@Override
		    public void run() {
		        //Call your method here
		        //setEmail(emailContent, subject);
				notificationService.sendMessage(l.getTeacher().getEmail(), "Students attending lecture", "Number of students attending the lecture is " + l.getStudents().size());
		    }
			}, notificationTime);
		}
	
}
	

