package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;

public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Long>{
	
	CourseOffering findBySpecificCourseID(Long courseOfferingID);

	List<CourseOffering> findByStudent(String studentUsername);
	
	List<CourseOffering> findByCourse(Course course);
		
	List<CourseOffering> findAll();
	
	void deleteAll();

}
