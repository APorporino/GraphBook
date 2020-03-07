package ca.mcgill.ecse428.graphbook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.service.GraphBookService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGraphBookService {

	
	@Autowired
	private GraphBookService service;
	
	
	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void deleteStudents2() {
		service.deleteAllCourseOfferings();
		service.deleteAllCourses();
		service.deleteAllStudents();	

	}

	@Test
	public void testLoginUser() {
		//log in an exitsing user with correct email and studentId password
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654212;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		//create a new user
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}

		List<Student> students = service.getAllStudents();
		assertEquals(1, students.size());
		assertEquals(firstName, students.get(0).getFirstName());
		assertEquals(lastName, students.get(0).getLastName());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(emailAddress, students.get(0).getEmailAddress());
		assertEquals(password, students.get(0).getPassword());
		assertEquals(createdDate, students.get(0).getCreatedDate());

		//now login user
		Student st = null;

		try {
			st = service.login(emailAddress, password);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(firstName, st.getFirstName());
		assertEquals(lastName, st.getLastName());
		assertEquals(studentId, st.getStudentId());
		assertEquals(emailAddress, st.getEmailAddress());
		assertEquals(password, st.getPassword());
		assertEquals(createdDate, st.getCreatedDate());
	}

	@Test
	public void testLoginUserInvalidPassword () {
		//log in an exitsing user with correct email and studentId password
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654212;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		//create a new user
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}

		List<Student> students = service.getAllStudents();
		assertEquals(1, students.size());
		assertEquals(firstName, students.get(0).getFirstName());
		assertEquals(lastName, students.get(0).getLastName());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(emailAddress, students.get(0).getEmailAddress());
		assertEquals(password, students.get(0).getPassword());
		assertEquals(createdDate, students.get(0).getCreatedDate());

		//now login user
		String attemptedPassword = "jimy";
		Student st = null;
		try {
			st = service.login(emailAddress, attemptedPassword);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(null, st);
	}

	@Test
	public void testLoginUserDoesNotExist() {
		assertEquals(0, service.getAllStudents().size());

		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Student st = null;

		try {
			service.login(emailAddress, password);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(null, st);
	}
	
	@Test
	public void testCreateEdge() {
		assertEquals(0, service.getAllEdges().size());
		
		long edgeId = 12345L;
		long followerId = 123L;
		long followeeId = 456L;
		Edge.Status status = Edge.Status.PENDING;
		int weight = 8;
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));
		
		try {
			service.createEdge(followerId, followeeId, status, weight, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, service.getAllEdges().size());
		assertEquals(12345L, service.getAllEdges().get(0).getEdgeId());
		assertEquals(123L, service.getAllEdges().get(0).getFollowerId());
		assertEquals(456L, service.getAllEdges().get(0).getFolloweeId());
		assertEquals(8, service.getAllEdges().get(0).getWeight());

	}
	
	@Test
	public void testCreateEdgeSameId() {
		assertEquals(0, service.getAllEdges().size());
		
		long edgeId = 12345L;
		long followerId = 123L;
		long followeeId = 456L;
		Edge.Status status = Edge.Status.PENDING;
		int weight = 8;
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));
		Edge edge = null;
		try {
			edge = service.createEdge(followerId, followeeId, status, weight, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(null, edge);

	}
	
	@Test
	public void testCourseCreated() {
		assertEquals(0, service.getAllEdges().size());
		
		try {
			List<Course> courses = service.getAllCourses();

		} catch(IllegalArgumentException e) {
			fail();
		}
		
	}
	
}
