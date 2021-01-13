package team13.pulsbes.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.LectureDTO;
import team13.pulsbes.dtos.StudentDTO;
import team13.pulsbes.entities.PDFExporter;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidTeacherException;
import team13.pulsbes.services.BookingManagerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/booking_manager")
public class BookingManagerController {

	@Autowired
	BookingManagerService bookingService;

	Logger log = Logger.getLogger("BookingManagerController");
	private static final String TYPE_BOOKING_MANAGER = "booking_manager";
	private static final String CONTACT_REPORT = "ContactReport_";
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String HEADER = "attachment; filename=";

	@GetMapping(value = Constants.GET_ALL_LECTURES)
	public List<LectureDTO> getAllLectures(@CookieValue(value = "username") String username,
			@CookieValue(value = "id") String id, @CookieValue(value = "type") String type) {
		if (type.equals(TYPE_BOOKING_MANAGER))
			return bookingService.getAllLectures();
		else
			return Collections.emptyList();
	}

	@GetMapping(value = Constants.GET_CONTACT_REPORT_STUDENT)
	public List<StudentDTO> getContactReportStudent(@RequestParam("studentId") String studentId,
			@CookieValue(value = "type") String type) throws InvalidStudentException {
		if (type.equals(TYPE_BOOKING_MANAGER))
			return bookingService.getContactReportStudent(studentId);
		else
			return Collections.emptyList();
	}

	@GetMapping(value = Constants.GET_CONTACT_REPORT_STUDENT_CSV)
	public void getContactReportStudentCSV(@RequestParam("studentId") String studentId,
			@CookieValue(value = "type") String type, HttpServletResponse response) throws InvalidStudentException, IOException {
		if(type.equals(TYPE_BOOKING_MANAGER)) {
			response.setContentType("application/csv"); 			
			Calendar tmpCal = Calendar.getInstance();
			String fileName = CONTACT_REPORT + String.valueOf(tmpCal.get(Calendar.YEAR)) + "_" + String.valueOf(tmpCal.get(Calendar.MONTH) + 1) + "_" + String.valueOf(tmpCal.get(Calendar.DATE)) + ".csv";
			String headerKey = CONTENT_DISPOSITION;
			String headerValue = HEADER + fileName + ".csv";
			response.setHeader(headerKey, headerValue);
			FileUtils.copyFile(bookingService.getContactReportStudentCSV(studentId), response.getOutputStream());
		}		
	}
	
	@GetMapping(value = Constants.GET_CONTACT_REPORT_STUDENT_PDF) 
	public void getContactReportStudentPDF(@RequestParam("studentId") String studentId,
	@CookieValue(value = "type") String type, HttpServletResponse response) throws DocumentException, IOException,
			InvalidStudentException {
		if(type.equals(TYPE_BOOKING_MANAGER)){
			response.setContentType("application/pdf");
			Calendar tmpCal = Calendar.getInstance();
			String fileName = CONTACT_REPORT + String.valueOf(tmpCal.get(Calendar.YEAR)) + "_" + String.valueOf(tmpCal.get(Calendar.MONTH) + 1) + "_" + String.valueOf(tmpCal.get(Calendar.DATE)) + ".csv";
				
			String headerKey = CONTENT_DISPOSITION;
			String headerValue = HEADER + fileName + ".pdf";
			response.setHeader(headerKey, headerValue);
		
			List<StudentDTO> listStudents = bookingService.getContactReportStudent(studentId);
		
			PDFExporter exporter = new PDFExporter(listStudents);
			exporter.export(response);
		}		
		
	}

	@GetMapping(value = Constants.GET_CONTACT_REPORT_TEACHER_CSV)
	public void getContactReportTeacherCSV(@RequestParam("teacherId") String teacherId,
			@CookieValue(value = "type") String type, HttpServletResponse response) throws InvalidTeacherException, IOException {
		if(type.equals(TYPE_BOOKING_MANAGER)) {
			response.setContentType("application/csv"); 			
			Calendar tmpCal = Calendar.getInstance();
			String fileName = CONTACT_REPORT + String.valueOf(tmpCal.get(Calendar.YEAR)) + "_" + String.valueOf(tmpCal.get(Calendar.MONTH) + 1) + "_" + String.valueOf(tmpCal.get(Calendar.DATE)) + ".csv";
			String headerKey = CONTENT_DISPOSITION;
			String headerValue = HEADER + fileName + ".csv";
			response.setHeader(headerKey, headerValue);
			FileUtils.copyFile(bookingService.getContactReportTeacherCSV(teacherId), response.getOutputStream());			
		}
	}
	
	@GetMapping(value = Constants.GET_CONTACT_REPORT_TEACHER_PDF) 
	public void getContactReportTeacherPDF(@RequestParam("teacherId") String teacherId,
	@CookieValue(value = "type") String type, HttpServletResponse response) throws DocumentException, IOException,
			InvalidTeacherException {
		if(type.equals(TYPE_BOOKING_MANAGER)){
			response.setContentType("application/pdf");
			Calendar tmpCal = Calendar.getInstance();
			String fileName = CONTACT_REPORT + String.valueOf(tmpCal.get(Calendar.YEAR)) + "_" + String.valueOf(tmpCal.get(Calendar.MONTH) + 1) + "_" + String.valueOf(tmpCal.get(Calendar.DATE)) + ".csv";
				
			String headerKey = CONTENT_DISPOSITION;
			String headerValue = HEADER + fileName + ".pdf";
			response.setHeader(headerKey, headerValue);
		
			List<StudentDTO> listStudents = bookingService.getContactReportTeacher(teacherId);
		
			PDFExporter exporter = new PDFExporter(listStudents);
			exporter.export(response);
		}		
		
	}
}
