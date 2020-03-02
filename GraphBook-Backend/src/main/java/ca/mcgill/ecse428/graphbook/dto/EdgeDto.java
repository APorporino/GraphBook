package ca.mcgill.ecse428.graphbook.dto;
import java.sql.Date;

public class EdgeDto {

	private long edgeId;
	private long followerId;
	private long followeeId;
	private String status;
	private int weight;
	private Date createdDate;
	
	public EdgeDto(long edgeId, long followerId, long followeeId, String status, int weight, Date createdDate) {
		this.edgeId = edgeId;
		this.followerId = followerId;
		this.followeeId = followeeId;
		this.status = status;
		this.weight = weight;
		this.createdDate = createdDate;
	}
	
	public long getEdgeId() {
		return edgeId;
	}
	
	public void setEdgeId(long newId) {
		this.edgeId = newId;
	}
	
	public long getFollowerId() {
		return followerId;
	}
	
	public void setFollowerId(long newId) {
		this.followerId = newId;
	}
	
	public long getFolloweeId() {
		return followeeId;
	}
	
	public void setFolloweeId(long newId) {
		this.followeeId = newId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int newWeight) {
		this.weight = newWeight;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date newDate) {
		this.createdDate = newDate;
	}
	
	
	
}
