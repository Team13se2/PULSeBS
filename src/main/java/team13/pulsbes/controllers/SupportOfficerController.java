package team13.pulsbes.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team13.pulsbes.dtos.ScheduleDTO;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidUserException;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/support_officer")
public class SupportOfficerController {

	@Autowired
	OfficerService officerService;

	Logger log = Logger.getLogger("SupportOfficerController");
	private static final String TYPE_SUPPORT = "support_officer";

	@PostMapping(value = Constants.ADD_STUDENTS, consumes = "text/csv")
	public void addStudents(@RequestBody String file, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, IOException {
		File f = new File("Students.csv");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(f);
			myWriter.write(file);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (myWriter != null)
				myWriter.close();

		}

		if (type.equals(TYPE_SUPPORT)) {
			officerService.addStudentList(f);
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}

		} else {
			myWriter.close();
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.ADD_TEACHERS)
	public void addTeachers(@RequestBody String file, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, IOException {
		File f = new File("Teachers.csv");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(f);
			myWriter.write(file);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (myWriter != null)
				myWriter.close();

		}
		if (type.equals(TYPE_SUPPORT)) {
			officerService.addTeacherList(f);
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
		} else {
			myWriter.close();
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.ADD_COURSES)
	public void addCourses(@RequestBody String file, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, IOException {
		File f = new File("Courses.csv");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(f);
			myWriter.write(file);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (myWriter != null)
				myWriter.close();

		}
		if (type.equals(TYPE_SUPPORT)) {
			officerService.addCourseList(f);
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
		} else {
			myWriter.close();
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.ENROLL_STUDENTS)
	public void enrollStudents(@RequestBody String file, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidCourseException, InvalidStudentException, InvalidUserException, IOException {
		File f = new File("Enroll_students.csv");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(f);
			myWriter.write(file);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (myWriter != null)
				myWriter.close();

		}
		if (type.equals(TYPE_SUPPORT)) {
			officerService.enrollStudent(f);
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
		} else {
			myWriter.close();
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.ADD_LECTURES)
	public void addLectures(@RequestBody String file, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidCourseException, InvalidStudentException, InvalidUserException, IOException {
		File f = new File("Add_lectures.csv");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(f);
			myWriter.write(file);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (myWriter != null)
				myWriter.close();

		}
		if (type.equals(TYPE_SUPPORT)) {
			officerService.addScheduleList(f);
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
		} else {
			myWriter.close();
			if (!f.delete()) {
				throw new IOException("Error closing file");
			}
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.REMOVE_LECTURES)
	public void removeLectures(@RequestParam("year") String year, @RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, InvalidCourseException {
		if (type.equals(TYPE_SUPPORT)) {
			officerService.removeLectures(year, dateStart, dateEnd);
		} else {
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.READD_LECTURES)
	public void readdLectures(@RequestParam("year") String year, @RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, InvalidCourseException {
		if (type.equals(TYPE_SUPPORT)) {
			officerService.readdLectures(year, dateStart, dateEnd);
		} else {
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.REMOVE_HOLIDAYS)
	public void removeHolidays(@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @CookieValue(value = "username") String username, @CookieValue(value = "type") String type) throws InvalidUserException, InvalidCourseException, ParseException {
		if (type.equals(TYPE_SUPPORT)) {
			officerService.removeHolidays(dateStart, dateEnd);
		} else {
			throw new InvalidUserException("Invalid User");
		}
	}

	@PostMapping(value = Constants.MODIFY_SCHEDULE)
	public void modifySchedule(@RequestParam("id") Integer id, @RequestParam("dateStart") String dateStart, @RequestParam("code") String code, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, @RequestParam("seats") Integer seats, @RequestParam("Room") String Room, @RequestParam("Day") String Day, @CookieValue(value = "type") String type) throws InvalidUserException, InvalidCourseException, ParseException {

		if (type.equals(TYPE_SUPPORT)) {
			officerService.modifySchedule(id, code, dateStart, startTime, endTime, seats, Room, Day);
		} else {
			throw new InvalidUserException("Invalid User");
		}
	}

	@GetMapping(value = Constants.GET_SCHEDULE)
	public List<ScheduleDTO> getSchedule(@CookieValue(value = "type") String type) throws InvalidUserException {
		if (type.equals(TYPE_SUPPORT)) {
			return officerService.getSchedule();
		} else {
			throw new InvalidUserException("Invalid User");
		}


	}
}

