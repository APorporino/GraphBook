package ca.mcgill.ecse428.graphbook.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity 
public class Student {
	
	private long studentId;
	
	@Id @GeneratedValue
	public long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}
	public String getFirstName() {
		return this.firstName;
	}
	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	private String emailAddress;
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	private String password;

	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	
	private Set<SpecificCourse> specificCourses;
	
	@ManyToMany
	public Set<SpecificCourse> getSpecificCourses(){
		return this.specificCourses;
	}
	
	public void setSpecificCourses(Set<SpecificCourse> specificCourses) {
		this.specificCourses = specificCourses;
	}

}
