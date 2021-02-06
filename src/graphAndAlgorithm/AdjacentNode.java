package graphAndAlgorithm;

public class AdjacentNode{
	double direction;
	double cost;
	double distance;
	MyNode node;
	public int roadId;
	
	public AdjacentNode(double distance,double cost,double direction,MyNode node,int id) {
		this.direction=direction;
		this.distance=distance;
		this.cost=cost;
		this.node =node;
		this.roadId=id;
	}
	
	
	
	public AdjacentNode(MyNode node) {
		this.node=node;
	}
	
	public MyNode getNode() {
		return node;
	}
	public void setNode(MyNode node) {
		this.node = node;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
