package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Course;
import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Student;

public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Long>{
	
	CourseOffering findByCourseOfferingId(Long courseOfferingId);

	List<CourseOffering> findByStudents(Student student);
	
	List<CourseOffering> findByCourse(Course course);
		
	List<CourseOffering> findAll();
	
	void deleteAll();

}
