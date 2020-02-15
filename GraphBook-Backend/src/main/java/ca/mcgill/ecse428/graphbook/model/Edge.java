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
	
	private long followerId;
	
	public long getFollowerId() {
		return this.followerId;
	}

	public void setFollowerId(long followerId) {
		this.followerId = followerId;
	}
	
	private long followeeId;

	public long getFolloweeId() {
		return this.followeeId;
	}

	public void setFolloweeId(long followeeId) {
		this.followeeId = followeeId;
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
