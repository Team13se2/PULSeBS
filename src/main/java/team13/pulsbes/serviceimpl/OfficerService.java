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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.stereotype.Service;

import team13.pulsbes.dtos.CourseDTO;
import team13.pulsbes.dtos.ScheduleDTO;
import team13.pulsbes.entities.*;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.repositories.*;
import team13.pulsbes.services.NotificationService;

@Service
public class OfficerService {

	@Autowired
	ModelMapper modelMapper;

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
	@Autowired
	HolidayRepository holidayRepository;

	
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


	public List<ScheduleDTO> getSchedule()
	{
		List <ScheduleDTO> scheduleDTOS = scheduleRepository.findAll().stream().map(s-> modelMapper.map(s,ScheduleDTO.class)).collect(Collectors.toList());
        List <ScheduleDTO> scheduleDTOS1 = new ArrayList<>();
		for (ScheduleDTO scheduleDTO : scheduleDTOS)
        {
            scheduleDTO.setCoursename(courseRepository.findById(scheduleDTO.getCode()).get().getName());
            scheduleDTOS1.add(scheduleDTO);
        }

		return scheduleDTOS1;
	}



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


	public void modifySchedule(Integer id,String code, String dateStart, String startTime, String endTime, Integer seats, String Room, String Day) throws ParseException, InvalidCourseException {
		Calendar tmpCal1 = Calendar.getInstance();
		Calendar tmpCal2 = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
		Date start = dateFormat.parse(dateStart);
		int addDate;
		int i;	String strDate = "Error";
		String endDate = "Error";
		String startMin,startHour,endHour,endMin,yyyy,mm,dd;
		String[] Start = startTime.split(":");
		String[] End = endTime.split(":");
		startHour = Start[0];
		startMin = Start[1];
		endHour = End[0];
		endMin = End[1];
		String[] dateSplit = dateStart.split(" ");
		String[] dateSplit2 = dateSplit[0].split("-");
		yyyy = dateSplit2[0];
		mm = dateSplit2[1];
		dd = dateSplit2 [2];
		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mm);
		List <Student> studentsbooked = new ArrayList<>();





		for (Lecture l : lectureRepository.findAll()) {

			if (l.getIdschedule().equals(id)) {
				try {

					if (l.getStartTime2().after(start)) {

						studentsbooked = new ArrayList<>(l.getStudents());
						System.out.println(studentsbooked);

						lectureRepository.delete(l);
						lectureRepository.flush();

						for (Student tmpStudent : studentsbooked) {


							notificationService.sendMessage(tmpStudent.getEmail(),
									"Schedule modified ",
									"Dear "+tmpStudent.getName()+" "+tmpStudent.getSurname()+ ", \n" +
											"The schedule of one of your booked lectures has been changed. \n" +
											"Booked lecture: " + l.getSubjectName() + " scheduled for " + l.getStartTime() + "\n" +

											"Starting from " + dateStart + " the lecture will take place at "  + Day + " in room " + Room +
									" from " +startTime + " to " +endTime + "\n."
									+ "If you want to conirm your booking you'll need to book the new lecture. Thank you."

							);

						}
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

		}









		Optional<Course> course = courseRepository.findById(code);

		if(!course.isPresent()) {
			throw new InvalidCourseException("Invalid Course");
		}

		if(course.get().getSemester().equals("1")) {

			for (i = 0; i < 17; i++) {

				Lecture l2 = Lecture.builder()
						.code(code)
						.day(Day)
						.totalSeat(seats)
						.roomName(Room)
						.startTime(startTime)
						.endTime(endTime)
						.availableSeat(seats)
						.bookable(true)
						.nrStudentsPresent(0)
						.nrStudentsBooked(0)
						.idschedule(id)
						.build();
				l2.setSubjectName(courseRepository.getOne(l2.getCode()).getName());
				l2.setTeacher(courseRepository.getOne(l2.getCode()).getTeacher());

				switch (l2.getDay()) {

					case "Mon":
						addDate = i * 7;

						tmpCal1.set(year, month, Integer.parseInt(dd), 0, 0, 0);
						tmpCal2.set(year, month, Integer.parseInt(dd), 0, 0, 0);

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
						addDate = 1 + i * 7;

						tmpCal1.set(year, month, Integer.parseInt(dd), 0, 0, 0);
						tmpCal2.set(year, month, Integer.parseInt(dd), 0, 0, 0);

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
						addDate = 2 + i * 7;

						tmpCal1.set(year, month, Integer.parseInt(dd), 0, 0, 0);
						tmpCal2.set(year, month, Integer.parseInt(dd), 0, 0, 0);

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
						addDate = 3 + i * 7;

						tmpCal1.set(year, month, Integer.parseInt(dd), 0, 0, 0);
						tmpCal2.set(year, month, Integer.parseInt(dd), 0, 0, 0);

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
						addDate = 4 + i * 7;

						tmpCal1.set(year, month, Integer.parseInt(dd), 0, 0, 0);
						tmpCal2.set(year, month, Integer.parseInt(dd), 0, 0, 0);

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

				l2.setStartTime(strDate);
				l2.setEndTime(endDate);




			/*	holidayRepository.findAll().forEach(h->{

				String [] dateh,datel2;
				boolean flag;
				dateh = h.getDate().split(" ");
				String dayh = dateh[0].concat(dateh[1] + dateh[2] + dateh[4] + dateh[5]);

					try {
						datel2 = l2.getStartTime2().toString().split(" ");
						String dayl2 = datel2[0].concat(datel2[1] + datel2[2] + datel2[4] + datel2[5]);
						if(dayh.equals(dayl2))

							flag = false;

					} catch (ParseException e) {
						e.printStackTrace();
					}

				});
            */
				boolean flag = true;

				for (Holiday h : holidayRepository.findAll()) {
					String[] dateh, datel2;

					dateh = h.getDate().split(" ");
					String dayh = dateh[0].concat(dateh[1] + dateh[2] + dateh[4] + dateh[5]);

					try {
						datel2 = l2.getStartTime2().toString().split(" ");
						String dayl2 = datel2[0].concat(datel2[1] + datel2[2] + datel2[4] + datel2[5]);
						System.out.println(dayh + dayl2);
						if (dayh.equals(dayl2))

							flag = false;

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				if (flag) {

					lectureRepository.save(l2);
					lectureRepository.flush();
				}

			}
		}

		if(course.get().getSemester().equals("2")) {

			for (i=0;i<17;i++) {

				Lecture l2 = Lecture.builder()
						.code(code)
						.day(Day)
						.totalSeat(seats)
						.roomName(Room)
						.startTime(startTime)
						.endTime(endTime)
						.availableSeat(seats)
						.bookable(true)
						.nrStudentsPresent(0)
						.nrStudentsBooked(0)
						.idschedule(id)
						.build();
				l2.setSubjectName(courseRepository.getOne(l2.getCode()).getName());
				l2.setTeacher(courseRepository.getOne(l2.getCode()).getTeacher());

				switch (l2.getDay()) {

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

				l2.setStartTime(strDate);
				l2.setEndTime(endDate);


				lectureRepository.save(l2);
				lectureRepository.flush();
			}

		}

      for (Schedule s : scheduleRepository.findAll())
	  {
	  	if (s.getId().equals(id))
		{
			s.setSeats(seats);
			s.setDay(Day);
			s.setStartTime(startTime);
			s.setEndTime(endTime);
			s.setRoom(Room);

			scheduleRepository.save(s);
			scheduleRepository.flush();
		}
	  }

	}



	@SuppressWarnings("deprecation")
	public void removeHolidays(String dateStart, String dateEnd) throws InvalidCourseException, ParseException {
		List<Lecture> listLecture = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);	
		Date start = dateFormat.parse(dateStart);
		Date end = dateFormat.parse(dateEnd);
		Boolean check1 = false;
		Boolean check2 = false;
			

		
		while(start.before(end)) {
			Holiday h = new Holiday();
		    h.setDate(start.toString());
			start.setDate(start.getDate()+1);
			holidayRepository.save(h);
		}
		Holiday h = new Holiday();
		h.setDate(end.toString());
		holidayRepository.save(h);
		
		listLecture.addAll(lectureRepository.findAll().stream().collect(Collectors.toList()));

		for (Lecture tmpLecture : listLecture) {

			try {check1 = tmpLecture.getStartTime2().after(dateFormat.parse(dateStart));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};
			try {check2 = tmpLecture.getEndTime2().before(dateFormat.parse(dateEnd));} catch (ParseException e) {log.throwing(this.getClass().getName(), "removeLectures", e);};
			
			Optional<Course> course = courseRepository.findById(tmpLecture.getCode());
			
			if(!course.isPresent()) {
				throw new InvalidCourseException("Invalid Course");
			}
				
			if(check1 && check2) {
				lectureRepository.delete(tmpLecture);
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
						l.setIdschedule(s.getId());

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
						l.setId(s.getId());


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





	

