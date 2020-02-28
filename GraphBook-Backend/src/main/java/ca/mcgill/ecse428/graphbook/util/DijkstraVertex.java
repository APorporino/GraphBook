package ca.mcgill.ecse428.graphbook.util;

import java.util.List;

public class DijkstraVertex {

	DijkstraVertex previousVertex;

	String name;

	List<DijkstraEdge> edges;

	public DijkstraVertex(String name) {
		previousVertex = null;
		edges = null;
		this.name = name;
	}
}
