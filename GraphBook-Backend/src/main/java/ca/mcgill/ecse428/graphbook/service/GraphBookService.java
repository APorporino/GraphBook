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
	
	@Transactional
	public CourseOffering createSpecificCourse(String semester, Date createdDate) {
		
		CourseOffering courseOffering;
		
		/*
		 * TODO
		 * Error checking
		 */
		
		courseOffering = new CourseOffering();
		courseOffering.setSemester(semester);
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return courseOffering;
		
	}
}
