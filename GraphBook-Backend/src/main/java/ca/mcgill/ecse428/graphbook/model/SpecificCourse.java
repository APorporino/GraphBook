package ca.mcgill.ecse428.graphbook.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class SpecificCourse {
	
	private long specificCourseID;

	@Id @GeneratedValue
	public long getSpecificCourseID() {
	    return this.specificCourseID;
	}
	
	public void setSpecificCourseID(long value) {
	    this.specificCourseID = value;
	}
	
	private String semester;
	
	public String getSemester() {
		return this.semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}

	private Course course;

	@ManyToOne(optional=false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	private Set<Student> students;

	@ManyToMany
	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
