package ca.mcgill.ecse428.graphbook.service;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Student;

@Service
public class GraphBookService {
	
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
	
	@Transactional
	public Course createCourse(String courseId, String name) {
		
		Course course;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		course = new Course();
		course.setCourseId(courseId);
		course.setName(name);
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return course;
		
	}
	
	@Transactional
	public CourseOffering createSpecificCourse() {
		CourseOffering courseOffering;
		
		courseOffering = new CourseOffering();
		
		return courseOffering;
		
	}
}
