package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Student;

public interface EdgeRepository extends CrudRepository<Edge, Long>{
	
	List<Edge> findAll();
	
	List<Edge> findByFollower(long followerId);
	
	List<Edge> findByFollowee(long followeeId);
	
	Edge findByEdgeId(long edgeId);
	
	List<Edge> findByWeight(int weight);
	
	Edge findByFollowerAndFollowee(long followerId, long followeeId);
	
	List<Edge> findByStatusAndFollowee(String status, Long followeeId);
	
	void deleteAll();
}
