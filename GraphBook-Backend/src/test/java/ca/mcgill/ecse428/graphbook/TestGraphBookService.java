package ca.mcgill.ecse428.graphbook;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGraphBookService {

	@Before
	public void setup() {

	}

	@After
	public void tearDown() {

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
		Student st;

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
		Student st;
		try {
			st = service.login(emailAddress, attemptedPassword);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(null, st);
	}

	@Test
	public void testLoginUserDoesNotExist() {
		assertEquals(0, servide.getAllStudents().size());

		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Student st;

		try {
			service.login(emailAddress, password);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(null, st);
	}
}
