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
public class TestServiceEdge {
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
		assertEquals(1, students.size());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
