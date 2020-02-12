package ca.mcgill.ecse428.graphbook.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	private String courseId;

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Id
	public String getCourseId() {
		return this.courseId;
	}
	
	private String name;

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	private Set<CourseOffering> courseOfferings;

	@OneToMany(mappedBy="course")
	public Set<CourseOffering> getCourseOfferings() {
		return this.courseOfferings;
	}

	public void setCourseOfferings(Set<CourseOffering> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}
	
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
