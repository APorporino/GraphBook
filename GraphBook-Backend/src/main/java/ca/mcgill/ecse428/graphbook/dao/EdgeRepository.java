package ca.mcgill.ecse428.graphbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.graphbook.model.Edge;
import ca.mcgill.ecse428.graphbook.model.Student;

public interface EdgeRepository extends CrudRepository<Edge, Long>{
	
	List<Edge> findAll();
	
//	List<Edge> findByFollowerId(long followerId);
//	
//	List<Edge> findByFolloweeId(long followeeId);
	
	Edge findByEdgeId(long edgeId);
	
	List<Edge> findByWeight(int weight);
	
	//Edge findByFollowerIdAndFolloweeId(long followerId, long followeeId);
	
	//List<Edge> findByStatusAndFolloweeId(String status, long followeeId);
	
	void deleteAll();
}
