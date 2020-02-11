package ca.mcgill.ecse428.graphbook.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	private Student follower;
	
	public Student getFollower() {
		return this.follower;
	}

	public void setFollower(Student follower) {
		this.follower = follower;
	}
	
	private Student followee;

	public Student getFollowee() {
		return this.followee;
	}

	public void setFollowee(Student followee) {
		this.followee = followee;
	}
	
	public enum Status {
		PENDING, ACCEPTED, DECLINED;
	}
	
	private Status status;

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	private int weight;

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
