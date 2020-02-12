package ca.mcgill.ecse428.graphbook.dto;

import java.sql.Date;
import java.util.Set;

import ca.mcgill.ecse428.graphbook.model.CourseOffering;

public class StudentDto {

	private long studentId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private Set<CourseOffering> courseOfferings;
	private Date createdDate;
	
	public StudentDto() {
		
	}
	
	public StudentDto(long studentId, String firstName, String lastName, String emailAddress, 
			String password, Set<CourseOffering> courseOfferings, Date createdDate) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.courseOfferings = courseOfferings;
		this.createdDate = createdDate;
	}
	
	public long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	
	public String getStudentFirstName() {
		return this.firstName;
	}
	
	public void setStudentFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getStudentLastName() {
		return this.lastName;
	}
	
	public void setStudentLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Set<CourseOffering> getCourseOffering() {
		return this.courseOfferings;
	}
	
	public void setCourseOfferings(Set<CourseOffering> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}
	
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
