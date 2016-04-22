package roadgraph;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import geography.GeographicPoint;

public class MapNode implements Comparable<MapNode> {
	private GeographicPoint location;
	private List<MapEdge> edgeList;
	private double distanceFromStart,distanceToEnd;
	
	public MapNode(GeographicPoint loc){
		location = loc;
		edgeList=new ArrayList<MapEdge>();
	}

	public void implementEdge(GeographicPoint to, String roadName, String roadType, double length) {
		edgeList.add(new MapEdge(location, to, roadName, roadType, length));		
	}

	public int compareTo(MapNode x){
		if (distanceFromStart+distanceToEnd > x.getDistanceFromStart()+x.getDistanceToEnd())return 1;
		else if(x.getDistanceFromStart()+x.getDistanceToEnd() > distanceFromStart+distanceToEnd)return -1;
		else return 0;
	}

	public List<GeographicPoint> getNeighbours() {
		List<GeographicPoint> neighbours= edgeList.stream().map(MapEdge::getDestination).collect(Collectors.toList());
		return neighbours;
	}

	public List<MapEdge> getEdgeList(){
		return edgeList;
	}

	public void setDistanceFromStart(double x){
		distanceFromStart =x;
	}

	public double getDistanceFromStart() {
		return distanceFromStart;
	}

	public GeographicPoint getLocation(){return location;}

	public double getDistanceToEnd(){return distanceToEnd;}

	public void setDistanceToEnd(GeographicPoint x) {
		distanceToEnd=Math.sqrt(Math.pow(location.getX()-x.getX(),2)+Math.pow(location.getY()-x.getY(),2));
	}
}
