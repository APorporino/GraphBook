package ca.mcgill.ecse428.graphbook.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.graphbook.dto.*;
import ca.mcgill.ecse428.graphbook.model.Edge;
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
	public StudentDto createStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("studentId") long studentId, @RequestParam("password") String password,
			@RequestParam("emailAddress") String emailAddress) throws IllegalArgumentException {

		Date createdDate = new Date(Calendar.getInstance().getTimeInMillis());
		Student student = service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);

		return convertToDto(student);
	}

	/**
	 * Logs in a student
	 * @param email
	 * @param password
	 *
	 * @return StudentDto StudentDto object for the logged in student
	 * @throws IllegalArgumentException if student credentials aren't correct or does not exist
	 */
	@GetMapping(value = { "/login", "/login/" })
	public StudentDto login(@RequestParam("email") String email, @RequestParam("password") String password) throws IllegalArgumentException {

		Student student = service.login(email, password);
		if (student == null) {
			return null;
		}
		return convertToDto(student);
	}

	/**
	 * This method will update a users bio.
	 * @param bio String representing the bio
	 *
	 * @return StudentDto object for updated student
	 */
	@PostMapping(value = { "/students/updateBio", "students/updateBio/" })
	public StudentDto updateBio(@RequestParam("studentId") long studentId, @RequestParam("bio") String bio) throws IllegalArgumentException {
		Student student = service.updateStudentBio(studentId, bio);
		return convertToDto(student);
	}


	/**
	 * Gets a student by his unique studentId.
	 * @param studentId
	 * @return StudentDto StudentDto object corresponding to the student with that studentId
	 */
	@GetMapping(value = { "/students/getByStudentId", "/students/getByStudentId/" })
	public StudentDto getStudentById(@RequestParam("studentId") long studentId) throws IllegalArgumentException {
		Student student = service.getStudentByStudentId(studentId);
		return convertToDto(student);

	}

	/**
	 * Allows a student to update their email, provided the new email is not already taken
	 * @param studentId
	 * @param newEmail
	 * @return StudentDto StudentDto object corresponding to the specified student with the updated email
	 */
	@PostMapping(value = { "/{studentId}/profile/username" })
	public StudentDto updateStudentEmail(@PathVariable long studentId, @RequestParam("newUsername") String newEmail) {
		Student student = service.updateStudentEmailAddress(studentId, newEmail);
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
		Student student = service.updateStudentAvatar(studentId, newAvatar);
		return convertToDto(student);
	}

	/**
	 * Gets all students.
	 * @return
	 */
	@GetMapping(value = { "/students", "/students/" })
	public List<StudentDto> getAllStudents(){
		List<StudentDto> students = new ArrayList<>();
		for (Student student : service.getAllStudents()) {
			students.add(convertToDto(student));
		}

		return students;
		
	}	
	
	/**
	 * Gets all students a user is connected to.
	 * @return List of students
	 */
	@GetMapping(value = { "/connections", "/connections/" })
	public List<StudentDto> getAllConnections(@RequestParam("email") String email) throws IllegalArgumentException{
		List<StudentDto> students = new ArrayList<>();
		Student student = service.getStudentByEmailAddress(email);
		for (Student s : service.getAllConnections(student.getStudentId())) {
			students.add(convertToDto(s));
		}
		return students;
	}	
	
	/**
	 * Gets all students except for students a user is already connected to.
	 * @return List of students
	 */
	@GetMapping(value = { "/nonConnections", "/nonConnections/" })
	public List<StudentDto> getNonConnections(@RequestParam("email") String email) throws IllegalArgumentException{
		List<StudentDto> students = new ArrayList<>();
		Student student = service.getStudentByEmailAddress(email);
		for (Student s : service.getNonConnections(student.getStudentId())) {
			students.add(convertToDto(s));
		}
		return students;
	}
	
	/**
	 * Deletes a student matching the specified email
	 * @param email
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = {"/delete"})
	public void deleteStudentByEmail(@RequestParam("email") String email) throws IllegalArgumentException{
		service.deleteStudentByEmail(email);
	}
	
	
	
	//---------COURSE----------//



	//------COURSE_OFFERING----//



	//----------EDGE-----------//
	
	/**
	 * Create edge between follower and followee
	 * @param followerId
	 * @param followeeId
	 * @param weight
	 * @return EdgeDto
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/edges/createEdge", "/edges/createEdge/" })
	public EdgeDto createEdge(@RequestParam("followerId") long followerId, @RequestParam("followeeId") long followeeId, 
			@RequestParam("weight") int weight) throws IllegalArgumentException {
		
		Date createdDate = new Date(Calendar.getInstance().getTimeInMillis());
		Edge edge = service.createEdge(followerId, followeeId, Edge.Status.PENDING, weight, createdDate);
		return convertToDto(edge);
	}
	
	/**
	 * Accept Edge
	 * @param edgeId
	 * @return EdgeDto
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/edges/acceptEdge", "/edges/acceptEdge/" })
	public EdgeDto acceptEdge(@RequestParam("edgeId") long edgeId) throws IllegalArgumentException {
		Edge edge = service.getEdgeByEdgeId(edgeId);
		edge.setStatus(Edge.Status.ACCEPTED);
		return convertToDto(edge);
	}
	
	/**
	 * Decline Edge
	 * @param edgeId
	 * @return EdgeDto
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/edges/declineEdge", "/edges/declineEdge/" })
	public EdgeDto declineEdge(@RequestParam("edgeId") long edgeId) throws IllegalArgumentException {
		Edge edge = service.getEdgeByEdgeId(edgeId);
		edge.setStatus(Edge.Status.DECLINED);
		return convertToDto(edge);
	}
	
	
	
	
	//---------------Convert To Domain Model Objects--------------//


	//---------------Convert To Data Transfer Objects--------------//

	public StudentDto convertToDto(Student student) {
		StudentDto studentDto= new StudentDto();

		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setStudentId(student.getStudentId());
		studentDto.setEmailAddress(student.getEmailAddress());
		studentDto.setCreatedDate(student.getCreatedDate());
		studentDto.setStudentId(student.getStudentId());
		studentDto.setBio(student.getBio());
		studentDto.setAvatar(student.getAvatar());
		studentDto.setCourseOfferings(student.getCourseOfferings());

		return studentDto;
	}
	
	/**
	 * Convert Edge to edgeDto
	 * @param edge
	 * @return EdgeDto
	 */
	public EdgeDto convertToDto(Edge edge) {
		if(edge == null) return null;
		EdgeDto edgeDto = new EdgeDto(edge.getEdgeId(), edge.getFollowerId(), 
				edge.getFolloweeId(), String.valueOf(edge.getStatus()), edge.getWeight(), edge.getCreatedDate());
		return edgeDto;
	}
	
	

}
