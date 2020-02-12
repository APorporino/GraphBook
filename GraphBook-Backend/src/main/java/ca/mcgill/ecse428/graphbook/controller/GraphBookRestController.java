package ca.mcgill.ecse428.graphbook.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.graphbook.dto.*;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.service.GraphBookService;

@CrossOrigin(origins = "*")
@RestController
public class GraphBookRestController {
	
	@Autowired
	GraphBookService service;
	
	//--------STUDENT----------//
	/**
	 * Creates Student using service method createStudent with fields provided by request
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param password
	 * 
	 * @return StudentDto StudentDto object for the newly created student
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/students/createStudent", "/students/createStudent/" })
	public StudentDto createPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, 
			@RequestParam("studentId") long studentId, @RequestParam("password") String password, 
			@RequestParam("emailAddress") String emailAddress) throws IllegalArgumentException {
		
		Date createdDate = new Date(Calendar.getInstance().getTimeInMillis());
		Student student = service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		
		return convertToDto(student);
	}
	
	
	/**
	 * Gets a student by his unique studentId.
	 * @param studentId
	 * @return StudentDto StudentDto object corresponding to the student with that studentId
	 */
	@PostMapping(value = { "/students/getByStudentId", "/students/getByStudentId/" })
	public StudentDto getStudentById(@RequestParam("studentId") long studentId) throws IllegalArgumentException {
		Student student = service.getStudentById(studentId);
		return convertToDto(student);
		
	}
	
	//TODO
	//method to update a users bio
	//method to update a users profile pic
	
	//We currently have neither of those in the model however so lets wait.
	
	
	
	
	//---------COURSE----------//
	
	
	
	//------COURSE_OFFERING----//
	
	
	
	//----------EDGE-----------//
	
	
	//---------------Convert To Domain Model Objects--------------//
	

	//---------------Convert To Data Transfer Objects--------------//
	
	public StudentDto convertToDto(Student student) {
		StudentDto studentDto= new StudentDto();
		
		studentDto.setStudentFirstName(student.getFirstName());
		studentDto.setStudentLastName(student.getLastName());
		studentDto.setStudentId(student.getStudentId());
		studentDto.setEmailAddress(student.getEmailAddress());
		studentDto.setCreatedDate(student.getCreatedDate());
		studentDto.setStudentId(student.getStudentId());
		
		return studentDto;
	}
	
	

}
