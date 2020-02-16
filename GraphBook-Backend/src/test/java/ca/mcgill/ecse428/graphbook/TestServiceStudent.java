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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.service.GraphBookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceStudent {

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

		assertEquals(error, "First name must be specified!");
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
		long studentId2 = 255654211;
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
			service.updateStudentBio(studentId, newAvatar);
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
	public void testValidEmailAddressFormat() {
		
		String validEmailAddress1 = "jim.flim@gmail.com";
		String validEmailAddress2 = "felix.sim@gmail.ca";
		String validEmailAddress3 = "tony_porp@email.org";
		String validEmailAddress4 = "a._b...__c@yahoo.net";
		
		String[] validEmailAddresses = {
				validEmailAddress1,
				validEmailAddress2,
				validEmailAddress3,
				validEmailAddress3
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
	public void test_findStudentValidEmailAndPassword() {
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
			assertEquals(created, returned);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	@Test
	public void test_findStudentIncorrectPassword() {
		assertEquals(0, service.getAllStudents().size());

		String firstName = "Jimmy";
		String lastName = "Flimmy";
		long studentId = 255654211;
		String emailAddress = "jimmy.flimmy@mail.com";
		String password = "jimmy";
		Date createdDate = Date.valueOf(LocalDate.now(Clock.systemUTC()));
		
		try {
			service.createStudent(firstName, lastName, studentId, emailAddress, password, createdDate);
			Student created = service.getStudentByEmailAddress(emailAddress);
			Student returned = service.getStudentByEmailAddressAndPassword(emailAddress, "123");
			assertNotEquals(created, returned);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void test_findNonExistantStudentByEmail() {
		assertEquals(0, service.getAllStudents().size());
		String error = null;

		try {
			service.getStudentByEmailAddressAndPassword("email", "123");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertTrue(error.contains("Student not found."));
		
	}
	

















}
