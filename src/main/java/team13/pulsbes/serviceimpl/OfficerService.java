package team13.pulsbes.serviceimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;
import team13.pulsbes.entities.Student;
import team13.pulsbes.entities.Teacher;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.CourseRepository;
import team13.pulsbes.repositories.LectureRepository;
import team13.pulsbes.repositories.StudentRepository;
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
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	
	public void addTeacher(Teacher t) {
		teacherRepository.save(t);
	}
	
	@SuppressWarnings("deprecation")
	public void addLecture(Lecture l) throws ParseException {
		lectureRepository.save(l);
		Timer timer = new Timer();
		
		//Date notificationTime =  new Date();
		
		Date notificationTime = l.getStartTime2();
		notificationTime.setDate(notificationTime.getDate() - 1);
		notificationTime.setHours(23);
		Calendar now = Calendar.getInstance();
		
		if(notificationTime.before(now.getTime())) {
			timer.schedule(new TimerTask() {
				@Override
			    public void run() {
			        //setEmail(emailContent, subject);
					
					notificationService.sendMessage(l.getTeacher().getEmail(), 
										"Students attending lecture of " + l.getSubjectName(),
										"Number of students attending the lecture is " + l.getStudents().size() + ".\n The lecture is scheduled at "+ l.getStartTime() + ".");
			    }
				}, notificationTime);
		}
	}
	
	public void addStudentList(File f) {
		boolean firstline = true;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
	            br = new BufferedReader(new FileReader(f));
	            while ((line = br.readLine()) != null) {
	            	if(!firstline) {  	
	            		Student s = new Student();
		                String[] student = line.split(cvsSplitBy);
		                s.setId(student[0]);
		                s.setName(student[1]);
		                s.setSurname(student[2]);
		                s.setCity(student[3]);
		                s.setEmail(student[4]);
		                s.setBirthday(student[5]);
		                s.setSSN(student[6]);
		                s.setPsw("psw");
		                studentRepository.save(s);
	            	}
	            firstline = false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	
	public void addTeacherList(File f) {
		boolean firstline = true;

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
	            br = new BufferedReader(new FileReader(f));
	            while ((line = br.readLine()) != null) {
	            	if(!firstline) {
	            	Teacher t = new Teacher();
	                	
	                String[] teacher = line.split(cvsSplitBy);
	                t.setNumber	(teacher[0]);
	                t.setName	(teacher[1]);
	                t.setSurname(teacher[2]);             
	                t.setEmail	(teacher[3]);           
	                t.setSSN	(teacher[4]);
	                t.setPsw("psw");
	                teacherRepository.save(t);
	            }
	            firstline = false;
	            
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public void addCourseList(File f) {
		boolean firstline = true;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null) {
            	if(!firstline) {
	            	Course c = new Course();
	            	String name;
	                String[] course = line.split(cvsSplitBy);
	                if(course[3].charAt(0) == '"') {
	                	name = course [3] + course[4];
	                	course[4] = course[5]; 
	                }
	                else 
	                	name = course[3];
	                
	                
	                c.setCode(course[0]);
	                c.setYear(course[1]);
	                c.setSemester(course[2]);             
	                c.setName(name);           
	                c.setTeacher(teacherRepository.getOne(course[4]));
	                
	                courseRepository.save(c);
            	}   
            	firstline = false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public void enrollStudent(File f) throws InvalidCourseException, InvalidStudentException {
		boolean firstline = true;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null) {
            	if(!firstline) {
	                String[] enroll = line.split(cvsSplitBy);
	                Course c = courseRepository.getOne(enroll[0]);
	                Student s = studentRepository.getOne(enroll[1]);
	                s.addCourse(c);
	                //c.newStudentEnrolled(s);
	                //courseRepository.save(c);
	                studentRepository.save(s);
            	}   
            	firstline = false;
            	
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
	

