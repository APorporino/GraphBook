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
		Student student = service.getStudentByStudentId(studentId);
		return convertToDto(student);
		
	}
	
	//	@GetMapping(value = { "/students/getByUsername" })
	//	public StudentDto getStudentByUsername(@RequestParam("username") String username) {
	//		//TODO finish this method
	//	}
	
	/**
	 * Allows a student to update their username, provided the new username is not already taken
	 * @param studentId
	 * @param newUsername
	 * @return StudentDto StudentDto object corresponding to the specified student with the updated username
	 */
	@PostMapping(value = { "/{studentId}/profile/username" })
	public StudentDto updateStudentUsername(@PathVariable long studentId, @RequestParam("newUsername") String newUsername) {
		//		if(getStudentByUsername(newUsername) != null) {
		//			throw new Exception("Username is already taken.");
		//		}
		
		Student student = service.getStudentByStudentId(studentId);
		//student.setUsername(newUsername);
		return convertToDto(student);
		
	}
	
	/**
	 * Allows a student to update their user bio
	 * @param studentId
	 * @param newBio
	 * @return StudentDto StudentDto object corresponding to the specified student with the updated bio
	 */
	@PostMapping(value = { "/{studentId}/profile/bio" })
	public StudentDto updateStudentBio(@PathVariable long studentId, @RequestParam("newBio") String newBio) {
		Student student = service.getStudentByStudentId(studentId);
		//student.setBio(newBio);
		return convertToDto(student);
	}
	
	/**
	 * Allows a student to update their avatar
	 * @param studentId
	 * @param newAvatar
	 * @return StudentDto StudentDto object corresponding to the specified student with the updated avatar
	 */
	@PostMapping(value = { "/{studentId}/profile/avatar" })
	public StudentDto updateStudentAvatar(@PathVariable long studentId, @RequestParam("newAvatar") String newAvatar) {
		Student student = service.getStudentByStudentId(studentId);
		//student.setAvatar(newAvatar);
		return convertToDto(student);
	}
	
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
