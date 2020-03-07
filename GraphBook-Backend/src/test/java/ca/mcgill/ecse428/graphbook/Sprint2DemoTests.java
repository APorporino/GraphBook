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
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.model.Edge.Status;
import ca.mcgill.ecse428.graphbook.service.GraphBookService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class Sprint2DemoTests {

	
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
		service.deleteAllEdges();	

	}
	
	@Test
	public void findEdgesByFollowerIdOrFoloweeId() {
		
		// create 5 students 
		assertEquals(0, service.getAllStudents().size());

		String[] firstNames = {"Jim", "Dwight", "Andy", "Micheal", "Stanley"};
		String[] lastNames = {"Halpert", "Schrute", "Bernard", "Scott", "Hudson"};
		long[] studentIds = {255654211, 255654212, 255654213, 255654214, 255654215};
		String[] emailAddresses = {"JH@mail.com", "DS@mail.com", "AB@mail.com", "MS@mail.com", "SH@mail.com",};
		String[] passwords = {"jim", "dwight", "andy", "micheal", "stanley"};
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			for(int i = 0; i < 5; i++) {
				service.createStudent(firstNames[i], lastNames[i], studentIds[i], emailAddresses[i], passwords[i], createdDate);
			}
		} catch(IllegalArgumentException e) {
			fail();
		}

		List<Student> students = service.getAllStudents();
		assertEquals(5, students.size());
		
		// create the edges
		
		try {
			service.createEdge(studentIds[0], studentIds[1], Status.PENDING, 5, createdDate);	// edge between Jim and Dwight
			service.createEdge(studentIds[0], studentIds[2], Status.PENDING, 5, createdDate);	// edge between Jim and Andy
			service.createEdge(studentIds[1], studentIds[2], Status.PENDING, 5, createdDate);	// edge between Dwight and Andy
			service.createEdge(studentIds[3], studentIds[0], Status.PENDING, 5, createdDate);	// edge between Micheal and Jim
			service.createEdge(studentIds[4], studentIds[0], Status.PENDING, 5, createdDate);	// edge between Stanley and Jim
			service.createEdge(studentIds[1], studentIds[4], Status.PENDING, 5, createdDate);	// edge between Dwight and Stanley
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Edge> edges = service.getAllEdges();
		assertEquals(6, edges.size());
		
		// get Jim's edges
		List<Edge> jimEdges = service.getEdgesByFollowerIdOrFolloweeId(studentIds[0], studentIds[0]);
		
		assertEquals(4,jimEdges.size());
		assertEquals(studentIds[0], jimEdges.get(0).getFollowerId());
		assertEquals(studentIds[1], jimEdges.get(0).getFolloweeId());
		
		
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
	
	@Test
	public void updateEmptyCourseOfferingListWithValidCourseOffering() {

		// create the first student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
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

		String courseId = "MATH240";
		String name = "Discrete Structures";

		try {
			service.createCourse(courseId, name, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}

		String semester = "WINTER2020";

		try {
			service.createCourseOffering(semester, createdDate, courseId);
		} catch(IllegalArgumentException e) {
			fail();
		}

		List<CourseOffering> courseOfferings = service.getAllCourseOfferings();

		assertEquals(1, courseOfferings.size());
		assertEquals(semester, courseOfferings.get(0).getSemester());
		assertEquals(createdDate, courseOfferings.get(0).getCreatedDate());



		try {
			service.updateStudentWithANewCourseOffering(studentId, courseOfferings.get(0).getCourseOfferingId());
		} catch(IllegalArgumentException e) {
			fail();
		}

		List<Student> studentsFromCourse = service.getStudentsByCourseOfferingId(courseOfferings.get(0).getCourseOfferingId());

		assertEquals(1, studentsFromCourse.size());
		assertEquals(firstName, studentsFromCourse.get(0).getFirstName());
		assertEquals(lastName, studentsFromCourse.get(0).getLastName());
		assertEquals(studentId, studentsFromCourse.get(0).getStudentId());
		assertEquals(emailAddress, studentsFromCourse.get(0).getEmailAddress());
		assertEquals(password, studentsFromCourse.get(0).getPassword());
		assertEquals(createdDate, studentsFromCourse.get(0).getCreatedDate());


	}
	@Test
	public void findStudentValidEmailAndPassword() {
		// create student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			Student created = service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
			Student returned = service.getStudentByEmailAddressAndPassword(emailAddress, password);
			assertEquals(created.getEmailAddress(), returned.getEmailAddress());
			assertEquals(created.getPassword(), returned.getPassword());
		} catch(IllegalArgumentException e) {
			fail();
		}


	}

	@Test
	public void findStudentIncorrectPassword() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
			Student returned = service.getStudentByEmailAddressAndPassword(emailAddress, "123");
			assertEquals(null, returned);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(error, "Student not found.");
	}

	@Test
	public void findNonExistantStudentByEmail() {
		assertEquals(0, service.getAllStudents().size());
		String error = null;

		try {
			service.getStudentByEmailAddressAndPassword("email", "123");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertTrue(error.contains("Student not found."));

	}
	
	@Test
	public void searchExistingUserByID(){		//search for existing user by studentId
		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

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

		//search by studentID

		assertEquals(studentId, students.get(0).getStudentId());
	}

	@Test
	public void searchNonExistingUserByID (){		//search for existing user by studentId
		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

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

		//search by wrong studentID
		students = service.getAllStudents();

		assertNotEquals(students.get(0).getStudentId(), 123456789);
	}

	@Test
	public void findMultipleStudentsByValidFirstName() {

		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create the second student with the same first name
		assertEquals(1, service.getAllStudents().size());

		String firstName2 = firstName;
		String lastName2 = "GrandMaster";
		long studentId2 = 255654212;
		String emailAddress2 = "jimmy.GrandMaster@mail.com";
		String password2 = "GrandMaster";

		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create a third student with a different first name
		assertEquals(2, service.getAllStudents().size());

		String firstName3 = "Jim";
		String lastName3 = "Halpert";
		long studentId3 = 255654213;
		String emailAddress3 = "jim.halpert@mail.com";
		String password3 = "halpert";

		try {
			service.createStudent(firstName3, lastName3, studentId3, emailAddress3, password3, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert all three students were created
		assertEquals(3, service.getAllStudents().size());
		
		List<Student> studentsWithFirstNameJimmy = service.getStudentByFirstName("Jimmy");
		
		assertEquals(2, studentsWithFirstNameJimmy.size());
		assertEquals(firstName, studentsWithFirstNameJimmy.get(0).getFirstName());
		assertEquals(studentId, studentsWithFirstNameJimmy.get(0).getStudentId());
		assertEquals(firstName2, studentsWithFirstNameJimmy.get(1).getFirstName());
		assertEquals(studentId2, studentsWithFirstNameJimmy.get(1).getStudentId());	

	}
	
	@Test
	public void findZeroStudentsWithInvalidFirstName() {

		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create the second student with the same first name
		assertEquals(1, service.getAllStudents().size());

		String firstName2 = firstName;
		String lastName2 = "GrandMaster";
		long studentId2 = 255654212;
		String emailAddress2 = "jimmy.GrandMaster@mail.com";
		String password2 = "GrandMaster";

		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create a third student with a different first name
		assertEquals(2, service.getAllStudents().size());

		String firstName3 = "Jim";
		String lastName3 = "Halpert";
		long studentId3 = 255654213;
		String emailAddress3 = "jim.halpert@mail.com";
		String password3 = "halpert";

		try {
			service.createStudent(firstName3, lastName3, studentId3, emailAddress3, password3, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert all three students were created
		assertEquals(3, service.getAllStudents().size());
		
		List<Student> studentsWithFirstNameMark;
		String error = "";
		
		try {
			studentsWithFirstNameMark = service.getStudentByFirstName("Mark");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Student with this first name not found.", error);

	}
	
	@Test
	public void findMultipleStudentsByValidLastName() {

		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create the second student with the same first name
		assertEquals(1, service.getAllStudents().size());

		String firstName2 = "GrandMaster";
		String lastName2 = lastName;
		long studentId2 = 255654212;
		String emailAddress2 = "jimmy.GrandMaster@mail.com";
		String password2 = "GrandMaster";

		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create a third student with a different first name
		assertEquals(2, service.getAllStudents().size());

		String firstName3 = "Jim";
		String lastName3 = "Halpert";
		long studentId3 = 255654213;
		String emailAddress3 = "jim.halpert@mail.com";
		String password3 = "halpert";

		try {
			service.createStudent(firstName3, lastName3, studentId3, emailAddress3, password3, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert all three students were created
		assertEquals(3, service.getAllStudents().size());
		
		List<Student> studentsWithLastNameFlimmy = null; 
		try {
			studentsWithLastNameFlimmy = service.getStudentByLastName("Flimmy");
		} catch (Exception e) {
			fail();
		}
		
		
		assertEquals(2, studentsWithLastNameFlimmy.size());
		assertEquals(lastName, studentsWithLastNameFlimmy.get(0).getLastName());
		assertEquals(studentId, studentsWithLastNameFlimmy.get(0).getStudentId());
		assertEquals(lastName2, studentsWithLastNameFlimmy.get(1).getLastName());
		assertEquals(studentId2, studentsWithLastNameFlimmy.get(1).getStudentId());	

	}
	
	@Test
	public void findZeroStudentsWithInvalidLastName() {

		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create the second student with the same first name
		assertEquals(1, service.getAllStudents().size());

		String firstName2 = firstName;
		String lastName2 = "GrandMaster";
		long studentId2 = 255654212;
		String emailAddress2 = "jimmy.GrandMaster@mail.com";
		String password2 = "GrandMaster";

		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// create a third student with a different first name
		assertEquals(2, service.getAllStudents().size());

		String firstName3 = "Jim";
		String lastName3 = "Halpert";
		long studentId3 = 255654213;
		String emailAddress3 = "jim.halpert@mail.com";
		String password3 = "halpert";

		try {
			service.createStudent(firstName3, lastName3, studentId3, emailAddress3, password3, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert all three students were created
		assertEquals(3, service.getAllStudents().size());
		
		List<Student> studentsWithLastNameMark;
		String error = "";
		
		try {
			studentsWithLastNameMark = service.getStudentByLastName("Mark");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Student with this last name not found.", error);

	}
	
	@Test
	public void findMultipleStudentsByValidLastNameAndFirstName() {

		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String firstName2 = firstName;
		String lastName2 = lastName;
		long studentId2 = studentId + 1;
		String emailAddress2 = "jimmy.GrandMaster@mail.com";
		String password2 = "GrandMaster";

		String firstName3 = "Jimmy";
		String lastName3 = "Halpert";
		long studentId3 = studentId2 + 1;
		String emailAddress3 = "jim.halpert@mail.com";
		String password3 = "halpert";

		String firstName4 = "GrandMaster";
		String lastName4 = "Flimmy";
		long studentId4 = studentId3 + 1;
		String emailAddress4 = "GrandMaster.halpert@mail.com";
		String password4 = "PAULSIMON";

		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate);
			service.createStudent(firstName3, lastName3, studentId3, emailAddress3, password3, createdDate);
			service.createStudent(firstName4, lastName4, studentId4, emailAddress4, password4, createdDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert all three students were created
		assertEquals(4, service.getAllStudents().size());
		
		List<Student> studentsWithFirstNameJimmyLastNameFlimmy = null; 
		try {
			studentsWithFirstNameJimmyLastNameFlimmy = service.getStudentByFirstNameAndLastName("Jimmy", "Flimmy");
		} catch (Exception e) {
			fail();
		}
		
		
		assertEquals(2, studentsWithFirstNameJimmyLastNameFlimmy.size());
		assertEquals(lastName, studentsWithFirstNameJimmyLastNameFlimmy.get(0).getLastName());
		assertEquals(studentId, studentsWithFirstNameJimmyLastNameFlimmy.get(0).getStudentId());	// uniqueness
		assertEquals(lastName2, studentsWithFirstNameJimmyLastNameFlimmy.get(1).getLastName());
		assertEquals(studentId2, studentsWithFirstNameJimmyLastNameFlimmy.get(1).getStudentId());	// uniqueness

	}


	
}
