package ca.mcgill.ecse428.graphbook.util;

import java.util.ArrayList;
import java.util.List;

public class DijkstraVertex {

	DijkstraVertex previousVertex = null;
	
	int distanceFromStart;

	/**
	 * Identifier of the Dijkstra vertex.
	 */
	long studentId;

	List<DijkstraEdge> edges;

	public DijkstraVertex(long studentId) {
		previousVertex = null;
		edges = new ArrayList<DijkstraEdge>();
		this.studentId = studentId;
		this.distanceFromStart = Integer.MAX_VALUE;
	}
	
	public List<DijkstraEdge> getEdges(){
		return this.edges;
	}
	
	public long getStudentId() {
		return this.studentId;
	}
	
	public int getDistanceFromStart() {
		return distanceFromStart;
	}

	public void setDistanceFromStart(int distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}
	
	public DijkstraVertex getPreviousVertex() {
		return previousVertex;
	}

	public void setPreviousVertex(DijkstraVertex previousVertex) {
		this.previousVertex = previousVertex;
	}

}
