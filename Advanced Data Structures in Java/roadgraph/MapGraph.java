/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.*;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	private int numVertices;
	private int numEdges;
	private HashMap<GeographicPoint, MapNode> mapList;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		mapList=new HashMap<GeographicPoint, MapNode>();
		numEdges=numVertices=0;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		HashSet<GeographicPoint> vertSet=new HashSet<GeographicPoint>();
		for(GeographicPoint key:mapList.keySet()){
			vertSet.add(key);
		}
		return vertSet;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		if(mapList.containsKey(location))return false;
		mapList.put(location, new MapNode(location));
		numVertices++;
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		//TODO: Implement this method in WEEK 2
		mapList.get(from).implementEdge(to,roadName,roadType,length);
		numEdges++;
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 
		LinkedList <GeographicPoint> queue=new LinkedList<GeographicPoint>();
		HashSet<GeographicPoint> visited=new HashSet<GeographicPoint>();
		HashMap<GeographicPoint,GeographicPoint> parentMap=new HashMap<GeographicPoint,GeographicPoint>();
		List<GeographicPoint> route=new ArrayList<GeographicPoint>();
		queue.add(start);
		while(queue.size()>0){
			GeographicPoint curr=queue.remove();
			if(curr.equals(goal)){
				//add to list and return
				while(curr!=start){
					route.add(0, curr);
					curr=parentMap.get(curr);
				}
				route.add(0, curr);
				return route;
			}
			for(GeographicPoint vert:mapList.get(curr).getNeighbours()){
				if (visited.contains(vert))continue;
				visited.add(vert);
				queue.add(vert);
				parentMap.put(vert, curr);
			}
		}
		return null;
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		int count=0;
		PriorityQueue<MapNode> pq=new PriorityQueue<>();
		HashSet<MapNode> visited=new HashSet<>();
		HashMap<MapNode,MapNode> parentMap=new HashMap<>();
		for (GeographicPoint point:mapList.keySet()){
			mapList.get(point).setDistanceFromStart(Double.MAX_VALUE);
		}
		mapList.get(start).setDistanceFromStart(0);
		pq.add(mapList.get(start));
		while(!pq.isEmpty()){
			MapNode curr=pq.poll();
			count++;
			if (visited.contains(curr))continue;
			visited.add(curr);
			if (curr==mapList.get(goal)){
				List<GeographicPoint> route=new ArrayList<>();
				while(!(curr==mapList.get(start))){
					route.add(0,curr.getLocation());
					curr=parentMap.get(curr);
				}
				route.add(0,curr.getLocation());
				System.out.println("count="+count);
				return route;
			}
			for(MapEdge edge:curr.getEdgeList()){
				if (visited.contains(mapList.get(edge.getEnd()))|| (pq.contains(mapList.get(edge.getEnd()))
						&& mapList.get(edge.getEnd()).getDistanceFromStart()
							< curr.getDistanceFromStart()+edge.getLength()*edge.getSpeedLimit())){continue;}
				mapList.get(edge.getEnd()).setDistanceFromStart(curr.getDistanceFromStart()+edge.getLength()
						* edge.getSpeedLimit());
				parentMap.put(mapList.get(edge.getEnd()),curr);
				pq.add(mapList.get(edge.getEnd()));
			}
		}
		return new ArrayList<>();
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		int count=0;
		PriorityQueue<MapNode> pq=new PriorityQueue<>();
		HashSet<MapNode> visited=new HashSet<>();
		HashMap<MapNode,MapNode> parentMap=new HashMap<>();
		for (GeographicPoint point:mapList.keySet()){
			mapList.get(point).setDistanceFromStart(Double.MAX_VALUE);
			mapList.get(point).setDistanceToEnd(goal);
		}
		mapList.get(start).setDistanceFromStart(0);
		pq.add(mapList.get(start));
		while(!pq.isEmpty()){
			MapNode curr=pq.poll();
			count++;
			if (visited.contains(curr))continue;
			visited.add(curr);
			if (curr==mapList.get(goal)){
				List<GeographicPoint> route=new ArrayList<>();
				while(!(curr==mapList.get(start))){
					route.add(0,curr.getLocation());
					curr=parentMap.get(curr);
				}
				route.add(0,curr.getLocation());
				System.out.println("count="+count);
				return route;
			}
			for(MapEdge edge:curr.getEdgeList()){
				if (visited.contains(mapList.get(edge.getEnd())) || (pq.contains(mapList.get(edge.getEnd())) &&
						mapList.get(edge.getEnd()).getDistanceFromStart() < curr.getDistanceFromStart()
								+ edge.getLength())){continue;}
				mapList.get(edge.getEnd()).setDistanceFromStart(curr.getDistanceFromStart()+edge.getLength());
				parentMap.put(mapList.get(edge.getEnd()),curr);
				pq.add(mapList.get(edge.getEnd()));
			}
		}
		return new ArrayList<>();
	}

	
	
	public static void main(String[] args)
	{
		String tmp;
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		// Use this code in Week 3 End of Week Quiz
		//MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);


		
	}
	
}
