package ca.mcgill.ecse428.graphbook.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

@Entity 
public class Student {
	
	private long studentId;
	
	@Id
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
	
	private Set<CourseOffering> courseOfferings;
	
	@ManyToMany(mappedBy = "students")
	public Set<CourseOffering> getCourseOfferings(){
		return this.courseOfferings;
	}
	
	public void setCourseOfferings(Set<CourseOffering> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}
	
	private String bio;
	
	
	public String getBio() {
        return this.bio;
    }
	
	public void setBio(String bio) {
        this.bio = bio;
    }

	
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	private List<Edge> connections;
	
	@ManyToMany
	@JoinTable(
			  name = "connections", 
			  joinColumns = @JoinColumn(name = "student_id"), 
			  inverseJoinColumns = @JoinColumn(name = "edge_id"))
	public List<Edge> getConnections(){
		return this.connections;
	}
	
	public void setConnections(List<Edge> connections) {
		this.connections = connections;
	}

}
