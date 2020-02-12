package ca.mcgill.ecse428.graphbook.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Edge.Status;
import ca.mcgill.ecse428.graphbook.model.Student;

import ca.mcgill.ecse428.graphbook.dao.*;

@Service
public class GraphBookService {
	
	//Autowired repositories
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EdgeRepository edgeRepository;
	
	@Autowired
	CourseOfferingRepository courseOfferingRepository;
	
	//--------STUDENT----------//
	
	/**
	 * Create a new student
	 * 
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param password
	 * @param createdDate
	 * @return the new student
	 */
	@Transactional
	public Student createStudent(String firstName, String lastName, long studentId, String emailAddress, String password, Date createdDate) {
		
		Student student;
		
		String error = "";
		
		if(firstName == null) {
			error += "First name must be specified! ";
		}
		else if (firstName.equals("")) {
			error += "First name must be specified! ";
		}
		if(lastName == null) {
			error += "Last name must be specified! ";
		}
		else if (lastName.equals("")) {
			error += "Last name must be specified! ";
		}
		if(emailAddress == null) {
			error += "Email address must be specified! ";
		}
		else if (emailAddress.equals("")) {
			error += "Email address must be specified! ";
		}
		if(password == null) {
			error += "Password must be specified! ";
		}
		else if (password.equals("")) {
			error += "Password must be specified! ";
		}
		if (getStudentById(studentId) != null) {
			error += "Student with that studentId already exists! ";
		}
		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setStudentId(studentId);
		student.setEmailAddress(emailAddress);
		student.setPassword(password);
		student.setCreatedDate(createdDate);
		
		studentRepository.save(student);
		
		return student;
	}
	
	/**
	 * Will find a student by a students unique ID
	 * 
	 * @param studentId
	 * @return Student object corresponding to student with that Id
	 */
	public Student getStudentById(long studentId) {
		
		Student student = studentRepository.findById(studentId);
		
		return student;
	}
	
	
	//---------COURSE----------//
	
	/**
	 * Create a new Course.
	 * @param courseId e.g. MATH240
	 * @param name	e.g. Discrete Structures
	 * @param createdDate
	 * @return the new course
	 */
	@Transactional
	public Course createCourse(String courseId, String name, Date createdDate) {
		
		Course course;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		course = new Course();
		course.setCourseId(courseId);
		course.setName(name);
		course.setCreatedDate(createdDate);
		
		courseRepository.save(course);
		
		return course;
		
	}
	
	//------COURSE_OFFERING----//
	
	/**
	 * Create a new course offering.
	 * @param semester
	 * @param createdDate
	 * @return the new course offering
	 */
	@Transactional
	public CourseOffering createCourseOffering(String semester, Date createdDate) {
		
		CourseOffering courseOffering;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		courseOffering = new CourseOffering();
		courseOffering.setSemester(semester);
		courseOffering.setCreatedDate(createdDate);
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return courseOffering;
		
	}
	
	//----------EDGE-----------//
	
	/**
	 * Create a new edge that represents the relationship between two students.
	 * @param follower
	 * @param followee
	 * @param status
	 * @param weight
	 * @param createdDate
	 * @return the new edge
	 */
	@Transactional
	public Edge createEdge(Student follower, Student followee, Status status, int weight, Date createdDate) {
		
		Edge edge;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		edge = new Edge();
		edge.setFollower(follower);
		edge.setFollowee(followee);
		edge.setStatus(status);
		edge.setWeight(weight);
		edge.setCreatedDate(createdDate);
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return edge;
		
	}
	
}
