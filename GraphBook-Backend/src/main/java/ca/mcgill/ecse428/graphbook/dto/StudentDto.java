package ca.mcgill.ecse428.graphbook.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import ca.mcgill.ecse428.graphbook.model.CourseOffering;
import ca.mcgill.ecse428.graphbook.model.Edge;

public class StudentDto {

	private long studentId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Set<CourseOffering> courseOfferings;
	private Date createdDate;
	private String bio;
	private String avatar;
	private List<Edge> connections;
	
	public StudentDto() {
		
	}
	
	public StudentDto(long studentId, String firstName, String lastName, String emailAddress, 
			Set<CourseOffering> courseOfferings, Date createdDate, String bio, List<Edge> connections) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.courseOfferings = courseOfferings;
		this.createdDate = createdDate;
		this.bio = bio;
		this.connections = connections;
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
	
	public String getBio() {
		return this.bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public List<Edge> getConnections(){
		return this.connections;
	}
	
	public void setConnections(List<Edge> connections) {
		this.connections = connections;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
