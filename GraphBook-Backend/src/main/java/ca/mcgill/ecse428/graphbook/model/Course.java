package ca.mcgill.ecse428.graphbook.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	private String courseID;

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	@Id
	public String getCourseID() {
		return this.courseID;
	}
	
	private String name;

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	private Set<SpecificCourse> specificCourse;

	@OneToMany(mappedBy="course")
	public Set<SpecificCourse> getSpecificCourse() {
		return this.specificCourse;
	}

	public void setSpecificCourse(Set<SpecificCourse> specificCourses) {
		this.specificCourse = specificCourses;
	}

}
