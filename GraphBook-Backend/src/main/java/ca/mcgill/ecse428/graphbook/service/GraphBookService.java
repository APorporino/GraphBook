package ca.mcgill.ecse428.graphbook.service;

import java.sql.Date;
import java.util.List;

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
		
		/*
		 * TODO
		 * Error checking
		 */
		
		student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setStudentId(studentId);
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
	 * Finds all students
	 * @return List of all student objects in the database
	 */
	@Transactional
	public List<Student> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}
	
	/**
	 * Will find a student by a students unique ID
	 * 
	 * @param studentId
	 * @return Student object corresponding to student with that Id
	 */
	@Transactional
	public Student getStudentByStudentId(long studentId) {
		
		Student student = studentRepository.findByStudentId(studentId);
		return student;
	}
	
	/**
	 * Finds list of students by their first name
	 * @param firstName
	 * @return List of student objects
	 */
	@Transactional
	public List<Student> getStudentByFirstName(String firstName) {
		List<Student> students = studentRepository.findByFirstName(firstName);
		return students;
	}
	
	/**
	 * Finds list of students by their last name
	 * @param lastName
	 * @return List of student objects
	 */
	@Transactional
	public List<Student> getStudentByLastName(String lastName) {
		List<Student> students = studentRepository.findByLastName(lastName);
		return students;
	}
	
	/**
	 * Finds list of students by firstName and lastName
	 * @param firstName
	 * @param lastName
	 * @return List of student objects
	 */
	@Transactional
	public List<Student> getStudentByFirstNameAndLastName(String firstName, String lastName) {
		List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName);
		return students;
	}
	
	/**
	 * Find student by unique email
	 * @param email
	 * @return Student object
	 */
	@Transactional
	public Student getStudentByEmail(String email) {
		Student student = studentRepository.findByEmail(email);
		return student;
	}
	
	/**
	 * Deletes student by student ID
	 * @param studentId
	 * @return deleted Student object
	 */
	@Transactional
	public Student deleteStudent(long studentId) {
		Student student = studentRepository.findByStudentId(studentId);
		studentRepository.delete(student);
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
		
		/*
		 * TODO
		 * Save in the repository
		 */
		
		return course;
		
	}
	
	/**
	 * Find All courses
	 * @return List of Course objects
	 */
	@Transactional
	public List<Course> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}
	
	/**
	 * Find course by course ID
	 * @param courseId
	 * @return Course Object
	 */
	@Transactional
	public Course getCourseByCourseId(String courseId) {
		Course course = courseRepository.findByCourseId(courseId);
		return course;
	}
	
	/**
	 * Find course by course name
	 * @param name
	 * @return Course Object
	 */
	@Transactional
	public Course getCourseByCourseNAme(String name) {
		Course course = courseRepository.findByName(name);
		return course;
	}
	
	/**
	 * Deletes a course by course ID
	 * @param courseId
	 * @return deleted Course
	 */
	@Transactional
	public Course deleteCourse(String courseId) {
		Course course = courseRepository.findByCourseId(courseId);
		courseRepository.delete(course);
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
	
	/**
	 * Finds Course Offering By Course Offering Id
	 * @param courseOfferingId
	 * @return CourseOffering object
	 */
	@Transactional
	public CourseOffering getCourseOfferingByCourseOfferingId(long courseOfferingId) {
		CourseOffering courseOffering = courseOfferingRepository.findByCourseOfferingId(courseOfferingId);
		return courseOffering;
	}
	
	/**
	 * Finds course offering by courseId
	 * @param courseId
	 * @return CourseOffering object
	 */
	@Transactional
	public List<CourseOffering> getCourseOfferingByCourseId(String courseId) {
		Course course = courseRepository.findByCourseId(courseId);
		if (course != null) {
			List<CourseOffering> courseOfferings = courseOfferingRepository.findByCourse(course);
			return courseOfferings;
		}
		return null;
	}
	
	/**
	 * Delete course offering by courseOfferingId
	 * @param courseOfferingId
	 * @return deleted Course
	 */
	@Transactional
	public CourseOffering deleteCourseOffering(long courseOfferingId) {
		CourseOffering courseOffering = courseOfferingRepository.findByCourseOfferingId(courseOfferingId);
		courseOfferingRepository.delete(courseOffering);
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
