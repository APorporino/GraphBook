package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

	List<Student> findAll();

	List<Student> findByFirstName(String firstName);

	List<Student> findByLastName(String lastName);

	Student findByEmail(String email);

	Student findByStudentId(long studentId);

	List<Student> findByFirstNameAndLastName(String firstName, String lastName);

	void deleteAll();
}