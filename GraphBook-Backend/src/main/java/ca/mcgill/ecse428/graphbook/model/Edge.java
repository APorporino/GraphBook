package ca.mcgill.ecse428.graphbook.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Edge {

	private long edgeId;
	
	@Id @GeneratedValue
	public long getEdgeId() {
		return this.edgeId;
	}
	
	public void setEdgeId(long edgeId) {
		this.edgeId = edgeId;
	} 
	
	private List<Student> connectedStudents;
	
	@ManyToMany(mappedBy = "connections")
	public List<Student> getConnectedStudents(){
		return this.connectedStudents;
	}
	
	public void setConnectedStudents(List<Student> connectedStudents) {
		this.connectedStudents = connectedStudents;
	}
	
//	private Student followerStudent;
//	
//	public Student getFollowerId() {
//		return this.followerStudent;
//	}
//
//	public void setFollowerStudent(Student followerStudent) {
//		this.followerStudent = followerStudent;
//	}
//	
//	private Student followeeStudent;
//
//	public Student getFolloweeId() {
//		return this.followeeStudent;
//	}
//
//	public void setFolloweeStudent(Student followeeStudent) {
//		this.followeeStudent = followeeStudent;
//	}
	
	public enum Status {
		PENDING, ACCEPTED, DECLINED;
	}
	
	private Status statusRequester;

	public Status getStatusRequester() {
		return this.statusRequester;
	}

	public void setStatusRequester(Status statusRequester) {
		this.statusRequester = statusRequester;
	}
	
	private Status statusRequested;

	public Status getStatusRequested() {
		return this.statusRequested;
	}

	public void setStatusRequested(Status statusRequested) {
		this.statusRequested = statusRequested;
	}
	
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
