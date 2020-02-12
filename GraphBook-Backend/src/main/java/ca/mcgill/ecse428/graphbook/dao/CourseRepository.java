package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	List<Course> findAll();
	
	Course findByCourseId(String courseId);
	
	Course findByName(String name);
	
	void deleteByCourseId(String courseId);
	
	boolean existsByCourseId(String courseId);
	
	void deleteAll();
}