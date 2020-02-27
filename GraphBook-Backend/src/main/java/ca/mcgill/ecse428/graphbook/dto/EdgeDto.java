package ca.mcgill.ecse428.graphbook.dto;
import java.sql.Date;

public class EdgeDto {

	private long edgeId;
	private long followerId;
	private long followeeId;
	private String status;
	private int weight;
	private Date createdDate;
	
	public EdgeDto(long edgeId, long followerId, long foloweeId, String status, int weight, Date createdDate) {
		this.edgeId = edgeId;
		this.followerId = followerId;
		this.followeeId = followeeId;
		this.status = status;
		this.weight = weight;
		this.createdDate = createdDate;
	}
	
	
	
}
