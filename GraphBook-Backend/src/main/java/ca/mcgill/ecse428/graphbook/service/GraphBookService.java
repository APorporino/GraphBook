package ca.mcgill.ecse428.graphbook.service;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Edge.Status;
import ca.mcgill.ecse428.graphbook.model.Student;

@Service
public class GraphBookService {
	
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
	public Student createStudent(String firstName, String lastName, String emailAddress, String password, Date createdDate) {
		
		Student student;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmailAddress(emailAddress);
		student.setPassword(password);
		student.setCreatedDate(createdDate);
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return student;
	}
	
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
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return course;
		
	}
	
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
