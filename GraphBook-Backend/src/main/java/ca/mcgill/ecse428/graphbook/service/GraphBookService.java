package ca.mcgill.ecse428.graphbook.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.graphbook.dao.CourseOfferingRepository;
import ca.mcgill.ecse428.graphbook.dao.CourseRepository;
import ca.mcgill.ecse428.graphbook.dao.EdgeRepository;
import ca.mcgill.ecse428.graphbook.dao.StudentRepository;
import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Edge.Status;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.util.DijkstraEdge;
import ca.mcgill.ecse428.graphbook.util.DijkstraVertex;

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



	//-------GENERAL METHODS---//


	/**
	 * Login student by emailAddress
	 * @param authenticationToken
	 * @param password
	 * @return Student object
	 */
	public Student login(String emailAddress, String password) {
		Student st = studentRepository.findByEmailAddress(emailAddress);
		if(st != null) {
			if (st.getPassword().equals(password)) {
				return st;
			}
		}
		return null;
	}

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
		else if (firstName.trim().equals("")) {
			error += "First name must be specified! ";
		}
		if(lastName == null) {
			error += "Last name must be specified! ";
		}
		else if (lastName.trim().equals("")) {
			error += "Last name must be specified! ";
		}
		if(emailAddress == null) {
			error += "Email address must be specified! ";
		}
		else if (emailAddress.trim().equals("")) {
			error += "Email address must be specified! ";
		}
		if(password == null) {
			error += "Password must be specified! ";
		}
		else if (password.trim().equals("")) {
			error += "Password must be specified! ";
		}

		if (studentRepository.findByEmailAddress(emailAddress) != null) {
			error += "Student with that email address already exists!";
		}
		if (studentRepository.findByStudentId(studentId) != null) {
			error += "Student with that studentId already exists!";
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
		student.setBio(null);
		student.setCourseOfferings(null);

		studentRepository.save(student);

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
	 * Finds all non connections of a student.
	 * @return List of all students a user is not connected to
	 */
	@Deprecated
	@Transactional
	public List<Student> getNonConnections(String email){
		//list of all students
		List<Student> nonConnections = studentRepository.findAll();
		//our student
		Student student = studentRepository.findByEmailAddress(email);
		
		long studentId = student.getStudentId();
		
		//will hold the ID's of the students followers and followee's
		long[] followers = new long[1000];	
		long[] following = new long[1000];  //max 2000 connections
		
		//search through all edges to get all connections for the student
		for (Edge edge : edgeRepository.findAll()){
			if (edge.getFolloweeId() == studentId) {
				followers[followers.length - 1] = edge.getFollowerId();
			}
			else if (edge.getFollowerId() == studentId) {
				following[following.length - 1] = edge.getFolloweeId();
			}
		}
		
		//go through all students and remove the student if there is a connection
		for (Student stu : nonConnections) {
			for (int i=0; i<followers.length; i++) {
				if (stu.getStudentId() == followers[i]) {
					nonConnections.remove(stu);
				}
			}
			for (int i=0; i<following.length; i++) {
				if (stu.getStudentId() == following[i]) {
					nonConnections.remove(stu);
				}
			}
		}
		//remove the student since he cant be connected to himself
		nonConnections.remove(student);
		return nonConnections;	
	}
	
	/**
	 * Finds all the students that are not yet connected to a specific student
	 * @param studentId
	 * @return All students not yet connected to the student.
	 */
	@Transactional
	public List<Student> getNonConnections(long studentId){
		
		List<Student> nonConnections = studentRepository.findAll();
		
		System.out.println("IHIDIJIDJ");
		/*
		 * For all students, check if there exists an edge between the current student 
		 * and the specified one. If so, remove it from the non connections.
		 */
		for(int i = 0; i < nonConnections.size(); i++) {
			
			if(edgeRepository.findByFollowerIdAndFolloweeId(studentId, nonConnections.get(i).getStudentId()) != null) {
				nonConnections.remove(i);
			} else if (edgeRepository.findByFollowerIdAndFolloweeId(nonConnections.get(i).getStudentId(), studentId) != null) { 
				nonConnections.remove(i);
			}
		}
		System.out.println("krnij");
		
		return nonConnections;
	}
	
	/**
	 * Find all students a user is connected to.
	 * @return List of all students a user is connected to
	 */
	@Deprecated
	@Transactional
	public List<Student> getAllConnections(String email){
		//list of all students
		List<Student> connections = new ArrayList<Student>();
		//our student
		Student student = studentRepository.findByEmailAddress(email);

		long studentId = student.getStudentId();
		//search through all edges to get all connections for the student
		for (Edge edge : edgeRepository.findAll()){
			if (edge.getFolloweeId() == studentId) {
				
				connections.add(studentRepository.findByStudentId(edge.getFollowerId()));
			}
			else if (edge.getFollowerId() == studentId) {
				connections.add(studentRepository.findByStudentId(edge.getFolloweeId()));
			}
		}
		return connections;	

	}
	
	/**
	 * Faster implementation of the find all students a user is connected to.
	 * @param studentId
	 * @return List of all students a user is connected to.
	 */
	@Transactional
	public List<Student> getAllConnections(long studentId){
		
		List<Student> connections = new ArrayList<Student>();									// returned connections initialized
				
		List<Edge> edges = edgeRepository.findByFollowerIdOrFolloweeId(studentId, studentId);	// edges of the input student
		
		for(Edge e : edges) {
			if(e.getFollowerId() == studentId) {
				connections.add(studentRepository.findByStudentId(e.getFolloweeId()));			// we want the followee
			} else {
				connections.add(studentRepository.findByStudentId(e.getFollowerId()));			// we want the follower
			}
		}
		
		return connections;
	}
	
	/**
	 * Will find a student by a students unique ID
	 * 
	 * @param studentId
	 * @return Student object corresponding to student with that Id
	 */
	@Transactional
	public Student getStudentByStudentId(long studentId) {
		String error = "";
		Student student = studentRepository.findByStudentId(studentId);
		if (student == null) {
			error = error + "Student with this Id not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		return student;
	}

	/**
	 * Finds list of students by their first name
	 * @param firstName
	 * @return List of student objects
	 */
	@Transactional
	public List<Student> getStudentByFirstName(String firstName) {
		String error = "";
		List<Student> students = studentRepository.findByFirstName(firstName);
		if (students.size() == 0) {
			error += "Student with this first name not found.";
		}
		if (error.trim().length() > 0) {
			throw new IllegalArgumentException(error);
		}
		return students;
	}

	/**
	 * Get all the students that take a course offering
	 * @param courseOfferingId
	 * @return the set of students
	 */
	@Transactional
	public List<Student> getStudentsByCourseOfferingId(long courseOfferingId){
		String error = "";
		CourseOffering courseOffering = this.getCourseOfferingByCourseOfferingId(courseOfferingId);
		List<Student> students = toList(courseOffering.getStudents());
		if (students == null) {
			error = error + "Student not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		return students;
	}

	/**
	 * Finds list of students by their last name
	 * @param lastName
	 * @return List of student objects
	 */
	@Transactional
	public List<Student> getStudentByLastName(String lastName) {
		String error = "";
		List<Student> students = studentRepository.findByLastName(lastName);
		if (students.size() == 0) {
			error = error + "Student with this last name not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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
		String error = "";
		List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName);
		if (students.size() == 0) {
			error = error + "Student with this name not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		return students;
	}

	/**
	 * Find student by unique email
	 * @param email
	 * @return Student object
	 */
	@Transactional
	public Student getStudentByEmailAddress(String emailAddress) {
		String error = "";
		Student student = studentRepository.findByEmailAddress(emailAddress);
		if (student == null) {
			error = error + "Student with this email not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		return student;
	}

	/**
	 * Find student by unique email and password
	 * @param email
	 * @param password
	 * @return Student object
	 */
	@Transactional
	public Student getStudentByEmailAddressAndPassword(String emailAddress, String password) {
		String error = "";
		Student student = studentRepository.findByEmailAddressAndPassword(emailAddress, password);
		if (student == null) {
			error = error + "Student not found.";
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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
	
	/**
	 * Deletes student by email
	 * @param email
	 * @return deleted Student object
	 */
	@Transactional
	public Student deleteStudentByEmail(String email) {
		Student student = studentRepository.findByEmailAddress(email);
		studentRepository.delete(student);
		return student;
	}
	
	/**
	 * Delete all students from the database
	 */
	@Transactional
	public void deleteAllStudents() {
		studentRepository.deleteAll();
	}

	/**
	 * Update a student's account email address
	 * @param studentId
	 * @param emailAddress
	 * @return
	 */
	@Transactional
	public Student updateStudentEmailAddress(long studentId, String emailAddress) {
		Student student = studentRepository.findByStudentId(studentId);
		String error = "";
		if(studentRepository.findByEmailAddress(emailAddress) != null) {
			error += "Email address already exists.";
		}

		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		student.setEmailAddress(emailAddress);
		studentRepository.save(student);
		return student;
	}

	/**
	 * Update a student's account password
	 * @param studentId
	 * @param password
	 * @return
	 */
	@Transactional
	public Student updateStudentPassword(long studentId, String password) {
		Student student = studentRepository.findByStudentId(studentId);
		student.setPassword(password);
		studentRepository.save(student);
		return student;
	}

	/**
	 * Updates a students bio.
	 * 
	 * @param String bio to be updated to
	 */
	@Transactional
	public Student updateStudentBio(long studentId, String bio) {
		Student student = studentRepository.findByStudentId(studentId);
		student.setBio(bio);
		studentRepository.save(student);
		return student;

	}

	/**
	 * Updates a students avatar.
	 * 
	 * @param String avatar to be updated to
	 */
	@Transactional
	public Student updateStudentAvatar(long studentId, String avatar) {
		Student student = studentRepository.findByStudentId(studentId);
		student.setAvatar(avatar);
		studentRepository.save(student);
		return student;
	}

	/**
	 * Updates a student's course offering list. If the student has no course offering taken yet, it will 
	 * create a new list with this offering. Otherwise, it will append it to the list if the course offering was not 
	 * already in the list.
	 * 
	 * This method also adds the student specified to the list of students inside the course offering by the
	 * same logic as presented above for the appending of a course offering.
	 * 
	 * @param studentId
	 * @param courseOfferingId
	 * 
	 */
	@Transactional
	public void updateStudentWithANewCourseOffering(long studentId, long courseOfferingId) {

		Student student = studentRepository.findByStudentId(studentId);
		CourseOffering courseOffering = courseOfferingRepository.findByCourseOfferingId(courseOfferingId);

		Set<CourseOffering> currentCourseOfferings = student.getCourseOfferings();

		/*
		 *  We will check if the course offering list for this student is null.
		 *  If so, simply create a new list with the requested course offering.
		 *  If not, check if the course offering is already in the list, and add it if it is not.
		 */
		if(currentCourseOfferings == null) {
			// add the course offering to the list inside student
			currentCourseOfferings = new HashSet<CourseOffering>();
			currentCourseOfferings.add(courseOffering);

		}else {

			if(currentCourseOfferings.contains(courseOffering)) {
				throw new IllegalArgumentException("This student is already taking this course offering!");			
			} else {
				currentCourseOfferings.add(courseOffering);

			}
		}

		Set<Student> currentStudents = courseOffering.getStudents();

		if(currentStudents == null) {
			currentStudents = new HashSet<Student>();
			currentStudents.add(student);
		}else {
			if(currentStudents.contains(student)) {
				throw new IllegalArgumentException("This student is already taking this course offering!");			
			} else {
				currentStudents.add(student);
			}
		}

		studentRepository.save(student);
		courseOfferingRepository.save(courseOffering);

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

	/**
	 * Delete all the courses in the database.
	 */
	@Transactional
	public void deleteAllCourses() {
		courseRepository.deleteAll();
	}



	//------COURSE_OFFERING----//

	/**
	 * Create a new course offering.
	 * @param semester
	 * @param createdDate
	 * @param courseId
	 * @return the new course offering
	 */
	@Transactional
	public CourseOffering createCourseOffering(String semester, Date createdDate, String courseId) {

		CourseOffering courseOffering;

		Course course = this.getCourseByCourseId(courseId);

		/*
		 * TODO
		 * Error checking
		 */

		courseOffering = new CourseOffering();
		courseOffering.setSemester(semester);
		courseOffering.setCreatedDate(createdDate);
		courseOffering.setCourse(course);

		courseOfferingRepository.save(courseOffering);

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
	 * Finds all the course offerings in the database.
	 * @return All the course offerings in the database.
	 */
	@Transactional
	public List<CourseOffering> getAllCourseOfferings(){
		List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();
		return courseOfferings;
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

	/**
	 * Delete all the course offerings.
	 */
	@Transactional 
	public void deleteAllCourseOfferings(){
		courseOfferingRepository.deleteAll();
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
	public Edge createEdge(long followerId, long followeeId, Status status, int weight, Date createdDate) {

		Edge edge;
		String error = "";

		if(studentRepository.findByStudentId(followerId) == null) {
			error += "No student was found with the follower studentId.";
		}
		if(studentRepository.findByStudentId(followeeId) == null) {
			error += "No student was found with the followee studentId.";
		}
		if(followerId == followeeId) {
			error += "The two students must be distinct.";
		}
		if (status == null) {
			error += "An edge status needs to be specified upon creation.";
		}
		else if (status != Status.PENDING) {
			error += "An edge status needs to be pending upon creaton.";
		}
		if(weight < 1 || weight > 10) {
			error += "The weight of an edge needs to be within 1 and 10.";
		}
		if(edgeRepository.findByFollowerIdAndFolloweeId(followerId, followeeId) != null) {
			error += "An edge connecting these two students already exists.";
		}

		if (error.trim().length() > 0) {
			throw new IllegalArgumentException(error);
		}

		edge = new Edge();
		edge.setFollowerId(followerId);
		edge.setFolloweeId(followeeId);
		edge.setStatus(status);
		edge.setWeight(weight);
		edge.setCreatedDate(createdDate);

		edgeRepository.save(edge);

		return edge;

	}

	/**
	 * Return all the existing edges currently in the database.
	 * @return List of all edge objects in the DB.
	 */
	public List<Edge> getAllEdges() {
		List<Edge> edges = edgeRepository.findAll();
		return edges;
	}


	public Edge getEdgeByEdgeId(long edgeId) {
		Edge edge = edgeRepository.findByEdgeId(edgeId);
		return edge;
	}

	/**
	 * Finds all edges for a given followee and status
	 * @param status
	 * @param followeeId
	 * @return list of edges
	 */
	public List<Edge> getEdgesByStatusAndFolloweeId(String status, long followeeId) {
		List<Edge> edges = edgeRepository.findByStatusAndFolloweeId(status, followeeId);
		return edges;
	}

	/**
	 * Finds edge by followerId and followeeId
	 * @param followerId
	 * @param followeeId
	 * @return Edge object
	 */
	public Edge getEdgeByFollowerIdAndFolloweeId(long followerId, long followeeId) {
		Edge edge = edgeRepository.findByFollowerIdAndFolloweeId(followerId, followeeId);
		return edge;
	}

	/**
	 * Finds edge by followerId or followeeId
	 * @param followerId
	 * @param followeeId
	 * @return Edge object
	 */
	public List<Edge> getEdgesByFollowerIdOrFolloweeId(long followerId, long followeeId) {
		List<Edge> edges = edgeRepository.findByFollowerIdOrFolloweeId(followerId, followeeId);
		return edges;
	}

	/**
	 * Deletes all the edges in the database
	 */
	public void deleteAllEdges() {
		edgeRepository.deleteAll();
	}
	/**
	 * Update the status of an edge to ACCEPTED or DECLINED, depending on the answer of the followee 
	 * upon the reception of the relation invitation.
	 * @param followerId
	 * @param followeeId
	 * @param status
	 * @return the edge updated
	 */
	public Edge updateEdgeStatus(long followerId, long followeeId, Status status) {
		String error = "";
		Edge edge;

		if(studentRepository.findByStudentId(followerId) == null) {
			error += "No student was found with the follower studentId.";
		}
		if(studentRepository.findByStudentId(followeeId) == null) {
			error += "No student was found with the followee studentId.";
		}

		edge = edgeRepository.findByFollowerIdAndFolloweeId(followerId, followeeId);

		if(edge == null) {
			error += "No edge currently exists between these two students.";
		}
		if (status == Status.PENDING) {
			error += "You can only change the status of an edge to ACCEPTED or DECLINED.";
		}

		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		edge.setStatus(status);

		return edge;
	}






	//--------------UTIL---------------//

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;

	}

	public boolean validateEmailAddressFormat(String emailAddress) {
		String pattern = "[\\w\\d\\._]+@[\\w]*\\.[\\w]{2,3}";

		boolean isValid = emailAddress.matches(pattern);

		return isValid;
	}

	/**
	 * Implementation of Dijkstra's algorithm to find the shortest path between two students. The shortest path
	 * will maximize the weights of the edges connecting the students. The logic is that to get to any other student,
	 * you should go through students with the highest connection (highest weight value);
	 * 
	 * @param startingId
	 * @param targetId
	 */
	public String findShortestPath(long startingId, long targetId) {

		ArrayList<DijkstraVertex> allDijkstraVertices = new ArrayList<DijkstraVertex>() {

			private static final long serialVersionUID = 1L;

			@Override
			public int indexOf(Object o) {
				assert o != null;

				DijkstraVertex v = (DijkstraVertex) o;
				for(int i = 0; i < this.size(); i++) {
					if(this.get(i).getStudentId() == v.getStudentId()) {
						return i;
					}
				}
				return -1;
			}
		};

		DijkstraVertex starting = new DijkstraVertex(startingId);
		DijkstraVertex target = new DijkstraVertex(targetId);

		allDijkstraVertices.add(starting);
		allDijkstraVertices.add(target);

		List<Edge> allEdges = getAllEdges();

		for(Edge e : allEdges) {
			Student s1 = getStudentByStudentId(e.getFollowerId());
			DijkstraVertex dijkstraFollower = new DijkstraVertex(s1.getStudentId());		// convert the student to its vertex representation

			Student s2 = getStudentByStudentId(e.getFolloweeId());
			DijkstraVertex dijkstraFollowee = new DijkstraVertex(s2.getStudentId());		// convert the student to its vertex representation

			int weight = 10 - e.getWeight();												// the weight is reversed to get the shortest path			

			int followerIndex = allDijkstraVertices.indexOf(dijkstraFollower);

			if(followerIndex != -1) {
				dijkstraFollower = allDijkstraVertices.get(followerIndex);					// if it exists, we want to modify the existing one
			} else {
				allDijkstraVertices.add(dijkstraFollower);									// if not, we add to the list of all vertices
			}


			int followeeIndex = allDijkstraVertices.indexOf(dijkstraFollowee);

			if(followeeIndex != -1) {
				dijkstraFollowee = allDijkstraVertices.get(followeeIndex);					// if it exists, we want to modify the existing one
			} else {
				allDijkstraVertices.add(dijkstraFollowee);									// if not, we add to the list of all vertices
			}

			DijkstraEdge dijkstraEdge = new DijkstraEdge(dijkstraFollower, dijkstraFollowee, weight);	// create the Dijkstra edge


			dijkstraFollower.getEdges().add(dijkstraEdge);
			dijkstraFollowee.getEdges().add(dijkstraEdge);
		}


		/* 
		 * this is now the beginning of the proper Dijkstra algorithm
		 */

		ArrayList<DijkstraVertex> visited = new ArrayList<DijkstraVertex>() {

			private static final long serialVersionUID = 1L;

			/**
			 * Overridden method to check if there is a vertex with the same full name
			 */
			@Override
			public boolean contains(Object o) {
				DijkstraVertex v = (DijkstraVertex) o;
				boolean found = false;
				for(DijkstraVertex pVertex : this) {
					if(pVertex.getStudentId() == (v.getStudentId())) {
						found = true;
					}
				}
				return found;
			}
		};

		ArrayList<DijkstraVertex> unvisited = new ArrayList<>(allDijkstraVertices);

		starting.setDistanceFromStart(0);							// the starting vertex is at distance 0 from itself, all others at INF

		while(unvisited.size() != 0) {

			DijkstraVertex current = findLowestValue(unvisited);
			System.out.println("Current : " + this.getStudentByStudentId(current.getStudentId()).getFirstName());
			int currentDistFromStart = current.getDistanceFromStart();

			// iterate over all the neighbours of the current vertex
			if(current.getEdges() != null) {

				for(DijkstraEdge dEdge : current.getEdges()) {
					DijkstraVertex neighbour = dEdge.getNeighbourOf(current);

					// make sure the vertex is not yet visited
					if(visited.contains(neighbour)) {
						continue;
					}

					System.out.println("Current neighbour: " + this.getStudentByStudentId(neighbour.getStudentId()).getFirstName());

					int neighbourDistFromStart = neighbour.getDistanceFromStart();

					System.out.println("current dist: " + currentDistFromStart);
					System.out.println("neighbour dist: " + neighbourDistFromStart);
					if(currentDistFromStart + dEdge.getWeight() < neighbourDistFromStart) {
						System.out.println("Updating vertex\n");
						neighbour.setDistanceFromStart(currentDistFromStart + dEdge.getWeight());
						neighbour.setPreviousVertex(current);
					}

				}
			}

			printResults(allDijkstraVertices);

			visited.add(current);
			unvisited.remove(current);

		}

		/*
		 * We can now display the shortest path found
		 */
		
		if(target.getDistanceFromStart() == Integer.MAX_VALUE) {			// no path was found
			return ("No path exists. Just go talk to them.");
		}

		DijkstraVertex current = target;
		int lengthOfPath = current.getDistanceFromStart();

		String path = "";
		while(current != null) {
			Student s = this.getStudentByStudentId(current.getStudentId());
			String sFullName = s.getFirstName() + " " + s.getLastName();
			if(current != starting) {
				path = " --> " + sFullName + path;
			} else {
				path = sFullName + path;
			}
			current = current.getPreviousVertex();
		}
		
		String startingFullname = this.getStudentByStudentId(starting.getStudentId()).getFirstName();
		startingFullname += " " + this.getStudentByStudentId(starting.getStudentId()).getLastName();
		
		String targetFullname = this.getStudentByStudentId(target.getStudentId()).getFirstName();
		targetFullname += " " + this.getStudentByStudentId(target.getStudentId()).getLastName();
		
		System.out.println("The best path from " + startingFullname + " to " + targetFullname + " is :");
		System.out.println(path);
		System.out.println("Length of path = " + lengthOfPath);

		return path;

	}

	/**
	 * Find the lowest distance from starting node inside all the unvisited vertices
	 * 
	 * @param unvisited
	 * @return
	 */
	private static DijkstraVertex findLowestValue(List<DijkstraVertex> unvisited) {
		DijkstraVertex record = unvisited.get(0);

		for (DijkstraVertex v: unvisited) {
			if( v.getDistanceFromStart() < record.getDistanceFromStart()) {
				record = v;
			}
		}

		return record;
	}

	private void printResults(List<DijkstraVertex> visited) {
		for(DijkstraVertex vertex: visited) {
			if(vertex.getPreviousVertex() == null){
				Student st = getStudentByStudentId(vertex.getStudentId());
				System.out.print("Vertex " + st.getFirstName() + " " + st.getLastName());
				System.out.print(" -- dist = " + vertex.getDistanceFromStart());
				System.out.print(" \n");
			} else{
				Student st = getStudentByStudentId(vertex.getStudentId());
				Student prev = getStudentByStudentId(vertex.getPreviousVertex().getStudentId());
				System.out.print("Vertex " + st.getFirstName() + " " + st.getLastName());
				System.out.print(" -- dist = " + vertex.getDistanceFromStart());
				System.out.print(" -- prev " + prev.getFirstName() + " " + prev.getLastName() + "\n");
			}
		}
		System.out.println("\n\n\n");
	}



}
