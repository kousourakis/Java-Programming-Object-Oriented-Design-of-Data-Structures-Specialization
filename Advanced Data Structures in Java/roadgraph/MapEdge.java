package roadgraph;
import geography.GeographicPoint;

public class MapEdge {
	private GeographicPoint start,end;
	private String roadName,roadType;
	private double length;
	private double speedlimit;
	
	MapEdge(GeographicPoint from,GeographicPoint to,String name,String type,double dist){
		start=from;
		end=to;
		roadName=name;
		roadType=type;
		length=dist;
		switch (type){
			case "residential": speedlimit=1.1;
				break;
			case "tertiary":speedlimit=0.8;
				break;
			case "secondary":speedlimit=0.9;
				break;
			default:speedlimit=1;
				break;
		}
	}

	public GeographicPoint getDestination() {
		return end;
	}

	public double getLength(){return length;}

	public GeographicPoint getEnd(){return end;}

	public double getSpeedLimit(){return speedlimit;}

}
