package ca.mcgill.ecse428.graphbook.util;

public class DijkstraEdge {


	DijkstraVertex follower;

	DijkstraVertex followee;

	int weight;

	public DijkstraEdge(DijkstraVertex follower, DijkstraVertex followee, int weight) {
		this.follower = follower;
		this.followee = followee;
		this.weight = weight;
	}

	public DijkstraVertex getNeighbourOf(DijkstraVertex vertex) {
		if(this.followee == vertex) {
			return follower;
		} else {
			return followee;
		}
	}

}
