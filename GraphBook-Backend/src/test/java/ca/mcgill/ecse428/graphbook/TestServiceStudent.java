package ca.mcgill.ecse428.graphbook;

//import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

//import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.service.GraphBookService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceStudent {

	@Autowired
	private GraphBookService service;


	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void deleteStudents() {
		service.deleteAllCourseOfferings();
		service.deleteAllCourses();
		service.deleteAllStudents();	

	}


	@Test
	public void createValidStudent() {
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

	}

	@Test
	public void createStudentNullFirstName() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = null;
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals(error, "First name must be specified!");
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentEmptyFirstName() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = " ";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals("First name must be specified!", error);
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentNullLastName() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = null;
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals(error, "Last name must be specified!");
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentEmptyLastName() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = " ";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals(error, "Last name must be specified!");
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentNullEmailAddress() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = null;
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals(error, "Email address must be specified!");
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentEmptyEmailAddress() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = " ";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals(error, "Email address must be specified!");
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentNullPassword() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = null;
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals("Password must be specified!", error);
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentEmptyPassword() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "      ";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Student> students = service.getAllStudents();

		assertEquals("Password must be specified!", error );
		assertEquals(0, students.size());

	}

	@Test
	public void createStudentWithDuplicateStudentId() {

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

		// now the second student
		String firstName2 = "Jimmy2";
		String lastName2 = "Flimmy2";
		long studentId2 = students.get(0).getStudentId();
		String emailAddress2 = "jimmy.flimmythesecond@mail.com";
		String password2 = "jimmy2";
		Date createdDate2 = Date.valueOf(LocalDate.now(Clock.systemUTC()));
		String error = "";
		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate2);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(error, "Student with that studentId already exists!");

		students = service.getAllStudents();
		assertEquals(1, students.size());
		assertEquals(firstName, students.get(0).getFirstName());
		assertEquals(lastName, students.get(0).getLastName());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(emailAddress, students.get(0).getEmailAddress());
		assertEquals(password, students.get(0).getPassword());
		assertEquals(createdDate, students.get(0).getCreatedDate());

	}

	@Test
	public void createStudentWithDuplicateEmailAddress() {

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

		// now the second student
		String firstName2 = "Jimmy2";
		String lastName2 = "Flimmy2";
		long studentId2 = 255654212;
		String emailAddress2 = emailAddress;
		String password2 = "jimmy2";
		Date createdDate2 = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		String error = "";
		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate2);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(error, "Student with that email address already exists!");

		students = service.getAllStudents();
		assertEquals(1, students.size());
		assertEquals(firstName, students.get(0).getFirstName());
		assertEquals(lastName, students.get(0).getLastName());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(emailAddress, students.get(0).getEmailAddress());
		assertEquals(password, students.get(0).getPassword());
		assertEquals(createdDate, students.get(0).getCreatedDate());

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
	public void updateStudentEmptyBio() {
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

		String bio = "I love cryptocurrency.";

		try {
			service.updateStudentBio(studentId, bio);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(bio, students.get(0).getBio());
	}

	@Test
	public void updateStudentExistingBio() {
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

		String bio = "I love cryptocurrency.";

		try {
			service.updateStudentBio(studentId, bio);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(bio, students.get(0).getBio());

		String newBio = "I hate cryptocurrency.";

		try {
			service.updateStudentBio(studentId, newBio);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(newBio, students.get(0).getBio());

	}

	@Test
	public void updateStudentAvatar() {
		// create the student
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Mike";
		String lastName = "Tyson";
		long studentId = 255654211;
		String emailAddress = "mike.tyson@mail.com";
		String password = "mt";
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

		String avatar = "http://www.fb.com/mikeTysonPicture1";

		try {
			service.updateStudentAvatar(studentId, avatar);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(avatar, students.get(0).getAvatar());

		String newAvatar = "http://www.fb.com/mikeTysonPicture2";

		try {
			service.updateStudentAvatar(studentId, newAvatar);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(newAvatar, students.get(0).getAvatar());

	}

	@Test
	public void updateEmailAddressWithValidEmailAddress() {
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


		String newEmailAddress = "jim.flim@mail.ca";

		try {
			service.updateStudentEmailAddress(studentId, newEmailAddress);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(1, students.size());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(newEmailAddress, students.get(0).getEmailAddress());

	}

	@Test
	public void validEmailAddressFormat() {

		String validEmailAddress1 = "jim.flim@gmail.com";
		String validEmailAddress2 = "felix.sim@gmail.ca";
		String validEmailAddress3 = "tony_porp@email.org";
		String validEmailAddress4 = "a._b...__c@yahoo.net";

		String[] validEmailAddresses = {
				validEmailAddress1,
				validEmailAddress2,
				validEmailAddress3,
				validEmailAddress4
		};

		String invalidEmailAddress1 = "jimmyflimmy";
		String invalidEmailAddress2 = "jimflim@gmail";
		String invalidEmailAddress3 = "jim.flim.jim@gmail.c";
		String invalidEmailAddress4 = "tony.porp$$$$";
		String invalidEmailAddress5 = "jimmyflimmy@email.como";

		String[] invalidEmailAddresses = {
				invalidEmailAddress1,
				invalidEmailAddress2,
				invalidEmailAddress3,
				invalidEmailAddress4,
				invalidEmailAddress5
		};

		for(int i = 0; i < validEmailAddresses.length; i++) {
			assertTrue(service.validateEmailAddressFormat(validEmailAddresses[i]));
		}

		for(int i = 0; i < invalidEmailAddresses.length; i++) {
			assertFalse(service.validateEmailAddressFormat(invalidEmailAddresses[i]));
		}
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

		try {
			service.login(emailAddress, password);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(firstName, students.get(0).getFirstName());
		assertEquals(lastName, students.get(0).getLastName());
		assertEquals(studentId, students.get(0).getStudentId());
		assertEquals(emailAddress, students.get(0).getEmailAddress());
		assertEquals(password, students.get(0).getPassword());
		assertEquals(createdDate, students.get(0).getCreatedDate());
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
		List<Student> students = service.getAllStudents();
		Student st = null;
		try {
			st = service.login(emailAddress, password);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(null, st);
	}

	@Test
	public void updateEmailAddressWithExistingEmailAddress() {
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

		//create 2nd student
		String firstName2 = "Jimmy2";
		String lastName2 = "Flimmy2";
		long studentId2 = 255654212;
		String emailAddress2 = "jimmy.flimmy2@mail.com";
		String password2 = "jimmy";
		Date createdDate2 = Date.valueOf(LocalDate.now(Clock.systemUTC()));

		try {
			service.createStudent(firstName2, lastName2, studentId2, emailAddress2, password2, createdDate2);
		} catch(IllegalArgumentException e) {
			fail();
		}

		students = service.getAllStudents();

		assertEquals(2, students.size());
		assertEquals(firstName2, students.get(1).getFirstName());
		assertEquals(lastName2, students.get(1).getLastName());
		assertEquals(studentId2, students.get(1).getStudentId());
		assertEquals(emailAddress2, students.get(1).getEmailAddress());
		assertEquals(password2, students.get(1).getPassword());
		assertEquals(createdDate2, students.get(1).getCreatedDate());


		//email adress which already exists
		String newEmailAddress = "jimmy.flimmy2@mail.com";	//same as emailAddress2

		String error = "";
		try {
			service.updateStudentEmailAddress(studentId, newEmailAddress);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(error, "Email address already exists.");
		assertEquals(2, students.size());
		assertNotEquals(students.get(0).getEmailAddress(), students.get(1).getEmailAddress());	//make sure not the same
		assertEquals(emailAddress, students.get(0).getEmailAddress());	//make sure its equal to the old one (not updated)

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
