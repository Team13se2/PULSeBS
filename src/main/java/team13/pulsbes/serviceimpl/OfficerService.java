package team13.pulsbes.serviceimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.pulsbes.entities.*;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.*;
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
	@Autowired
	ScheduleRepository scheduleRepository;

	public void addLectureRepository(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	public void addStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public void addTeacherRepository(TeacherRepository teacherRepository2) {
		this.teacherRepository = teacherRepository2;		
	}
	public void addCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	public void addScheduleRepository (ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	Logger log = Logger.getLogger("OfficerService");
	private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm";


	public void removeLectures(String year, String dateStart, String dateEnd) throws InvalidCourseException {

		List<Lecture> listLecture = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);	
		Boolean check1 = false;
		Boolean check2 = false;

		listLecture.addAll(lectureRepository.findAll().stream().collect(Collectors.toList()));

		for (Lecture tmpLecture : listLecture) {

			try {check1 = tmpLecture.getStartTime2().after(dateFormat.parse(dateStart));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};
			try {check2 = tmpLecture.getEndTime2().before(dateFormat.parse(dateEnd));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};	
			
			Optional<Course> course = courseRepository.findById(tmpLecture.getCode());
			
			if(!course.isPresent()) {
				throw new InvalidCourseException("Invalid Course");
			}
	
			if(course.get().getYear().equals(year) && check1 && check2) {			
				
				tmpLecture.setBookable(false);
				lectureRepository.save(tmpLecture);
				lectureRepository.flush();
			}
		}	
	}

	public void readdLectures(String year, String dateStart, String dateEnd) throws InvalidCourseException {

		List<Lecture> listLecture = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);	
		Boolean check1 = false;
		Boolean check2 = false;

		listLecture.addAll(lectureRepository.findAll().stream().collect(Collectors.toList()));

		for (Lecture tmpLecture : listLecture) {

			try {check1 = tmpLecture.getStartTime2().after(dateFormat.parse(dateStart));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};
			try {check2 = tmpLecture.getEndTime2().before(dateFormat.parse(dateEnd));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};
			
			Optional<Course> course = courseRepository.findById(tmpLecture.getCode());
			
			if(!course.isPresent()) {
				throw new InvalidCourseException("Invalid Course");
			}
				
			if(course.get().getYear().equals(year) && check1 && check2) {
				tmpLecture.setBookable(true);
				lectureRepository.save(tmpLecture);
				lectureRepository.flush();
			}
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

	public void addScheduleList(File f) throws InvalidCourseException {
		boolean firstline = true;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				if (!firstline) {
					Schedule s = new Schedule();
					String[] schedule = line.split(cvsSplitBy);
					String[] time = schedule[4].split("-");
					String start = time[0];
					String end = time[1];


					s.setCode(schedule[0]);
					s.setRoom(schedule[1]);
					s.setDay(schedule[2]);
					s.setSeats(Integer.valueOf(schedule[3]));
					s.setStartTime(start);
					s.setEndTime(end);

					
					String[] lecture = line.split(cvsSplitBy);
					String [] time2 = lecture[4].split("-");
					String start2 = time2[0];
					String [] splitStart = start2.split(":");
					String startHour = splitStart[0];
					String startMin = splitStart[1];
					String end2= time2[1];
					String [] splitEnd = end2.split(":");
					String endHour = splitEnd[0];
					String endMin = splitEnd[1];
					String strDate = "Error";
					String endDate = "Error";
					DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
					Calendar tmpCal1 = Calendar.getInstance();					
					Calendar tmpCal2 = Calendar.getInstance();
					
					int addDate;
					int i;
					Optional<Course> course = courseRepository.findById(lecture[0]);
					
					if(!course.isPresent()) {
						throw new InvalidCourseException("Invalid Course");
					}
					
					if(course.get().getSemester().equals("1")) {					

					for (i=0;i<17;i++) {

						Lecture l= new Lecture();

						switch (lecture[2]) {

							case "Mon":							
							addDate = i*7;							
							
							tmpCal1.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);
							tmpCal2.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	
							log.info(strDate);

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Tue":
							addDate = 1 + i*7;

							tmpCal1.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);
							tmpCal2.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Wed":
							addDate = 2 + i*7;

							tmpCal1.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);
							tmpCal2.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;
							
							case "Thu":
							addDate = 3 + i*7;

							tmpCal1.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);
							tmpCal2.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Fri":
							addDate = 4 + i*7;

							tmpCal1.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);
							tmpCal2.set(2020, Calendar.OCTOBER, 5, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;
							
							default:
							log.info("Wrong format");
							break;


						}

						l.setRoomName(lecture[1]);
						l.setDay(lecture[2]);
						l.setTotalSeat(Integer.valueOf(lecture[3]));
						l.setAvailableSeat(Integer.valueOf(lecture[3]));
						l.setStartTime(strDate);
						l.setEndTime(endDate);
						l.setCode(lecture[0]);
						l.setSubjectName(courseRepository.getOne(lecture[0]).getName());
						l.setBookable(true);
						l.setNrStudentsBooked(0);
						l.setNrStudentsPresent(0);
						l.setTeacher(courseRepository.getOne(lecture[0]).getTeacher());


						lectureRepository.save(l);
						lectureRepository.flush();
					}

				}
					
				if(course.get().getSemester().equals("2")) {					

					for (i=0;i<17;i++) {

						Lecture l= new Lecture();

						switch (lecture[2]) {

							case "Mon":							
							addDate = i*7;							
							
							tmpCal1.set(2020, Calendar.MARCH, 1, 0, 0, 0);
							tmpCal2.set(2020, Calendar.MARCH, 1, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	
							log.info(strDate);

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Tue":
							addDate = 1 + i*7;

							tmpCal1.set(2020, Calendar.MARCH, 1, 0, 0, 0);
							tmpCal2.set(2020, Calendar.MARCH, 1, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Wed":
							addDate = 2 + i*7;

							tmpCal1.set(2020, Calendar.MARCH, 1, 0, 0, 0);
							tmpCal2.set(2020, Calendar.MARCH, 1, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;
							
							case "Thu":
							addDate = 3 + i*7;

							tmpCal1.set(2020, Calendar.MARCH, 1, 0, 0, 0);
							tmpCal2.set(2020, Calendar.MARCH, 1, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;

							case "Fri":
							addDate = 4 + i*7;

							tmpCal1.set(2020, Calendar.MARCH, 1, 0, 0, 0);
							tmpCal2.set(2020, Calendar.MARCH, 1, 0, 0, 0);

							tmpCal1.add(Calendar.DATE, +addDate);
							tmpCal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
							tmpCal1.set(Calendar.MINUTE, Integer.parseInt(startMin));							
							strDate = dateFormat.format(tmpCal1.getTime());	

							tmpCal2.add(Calendar.DATE, +addDate);
							tmpCal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
							tmpCal2.set(Calendar.MINUTE, Integer.parseInt(endMin));
							endDate = dateFormat.format(tmpCal2.getTime());	
							break;
							
							default:
							log.info("Wrong format");
							break;


						}

						l.setRoomName(lecture[1]);
						l.setDay(lecture[2]);
						l.setTotalSeat(Integer.valueOf(lecture[3]));
						l.setAvailableSeat(Integer.valueOf(lecture[3]));
						l.setStartTime(strDate);
						l.setEndTime(endDate);
						l.setCode(lecture[0]);
						l.setSubjectName(courseRepository.getOne(lecture[0]).getName());
						l.setBookable(true);
						l.setNrStudentsBooked(0);
						l.setNrStudentsPresent(0);
						l.setTeacher(courseRepository.getOne(lecture[0]).getTeacher());


						lectureRepository.save(l);
						lectureRepository.flush();
					}

				}
					scheduleRepository.save(s);
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





	

