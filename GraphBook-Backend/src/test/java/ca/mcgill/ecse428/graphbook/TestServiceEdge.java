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
import ca.mcgill.ecse428.graphbook.model.Edge.Status;
import ca.mcgill.ecse428.graphbook.model.Student;
import ca.mcgill.ecse428.graphbook.model.Edge;
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
	public void findShortestPath() {
		
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
			
			/*
			 * 
			 * 				  4					5
			 * 		Jim -------------- Dwight-------Andy
			 * 		 |				__/	 |		   /
			 *  	 |		8  ____/	 | 		  /
			 * 	   9 |	    __/		   8 |       / 	5
			 * 		 |	 __/			 |      /
			 * 		 |	/				 |   __/
			 * 	  Micheal-------------Stanley
			 * 				  9
			 * 
			 */
			
			service.createEdge(studentIds[0], studentIds[1], Status.PENDING, 4, createdDate);	// edge between Jim and Dwight
			service.createEdge(studentIds[0], studentIds[3], Status.PENDING, 9, createdDate);	// edge between Jim and Micheal
			service.createEdge(studentIds[1], studentIds[3], Status.PENDING, 8, createdDate);	// edge between Dwight and Micheal
			service.createEdge(studentIds[1], studentIds[4], Status.PENDING, 8, createdDate);	// edge between Dwight and Stanley
			service.createEdge(studentIds[1], studentIds[2], Status.PENDING, 5, createdDate);	// edge between Dwight and Andy
			service.createEdge(studentIds[2], studentIds[4], Status.PENDING, 5, createdDate);	// edge between Andy and Stanley
			service.createEdge(studentIds[3], studentIds[4], Status.PENDING, 9, createdDate);	// edge between Micheal and Stanley
			
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		
		
		List<Edge> edges = service.getAllEdges();
		assertEquals(7, edges.size());
		
		// shortest path between Jim and Andy should be Jim -> Micheal -> Stanley -> Andy
		String path = service.findShortestPath(studentIds[0], studentIds[2]);
		
		assertEquals("Jim Halpert --> Micheal Scott --> Stanley Hudson --> Andy Bernard", path.trim());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
