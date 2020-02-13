package ca.mcgill.ecse428.graphbook.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class CourseOffering {
	
	private long courseOfferingId;

	@Id @GeneratedValue
	public long getCourseOfferingId() {
	    return this.courseOfferingId;
	}
	
	public void setCourseOfferingId(long courseOfferingId) {
	    this.courseOfferingId = courseOfferingId;
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
	@JoinTable(
			  name = "student_courses", 
			  joinColumns = @JoinColumn(name = "student_id"), 
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
