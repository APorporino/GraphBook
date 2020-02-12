package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	Course findByCourseID(String courseID);
	
	List<Course> findBySubject(String subject);
	
	void deleteByCourseID(String courseID);
	
	List<Course> findByLevel(String level);
	
	boolean existsByCourseID(String courseID);
	
	void deleteAll();
}