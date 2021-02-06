package graphAndAlgorithm;

import java.util.ArrayList;

import utilityclass.SurfacePoint;

public class MyNode {
	String name;
	int nodeId;
	SurfacePoint position;
	ArrayList<AdjacentNode> neighbourNode;

	
	
	public double x1,y1,x2,y2,x3,y3,x4,y4;
	
	
	public void addAdjacentNode(AdjacentNode ad ){
		this.neighbourNode.add(ad);
	}
	
	
	public MyNode() {
		neighbourNode=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public SurfacePoint getPosition() {
		return position;
	}
	public void setPosition(SurfacePoint position) {
		this.position = position;
	}
	public ArrayList<AdjacentNode> getNeighbourNode() {
		return neighbourNode;
	}
	public void setNeighbourNode(ArrayList<AdjacentNode> neighbourNode) {
		this.neighbourNode = neighbourNode;
	}
	
	public boolean insideNode(SurfacePoint p,double direction){
		
		
		double A = rectArea ();
		   double x,y;
		   x=p.getX();
		   y=p.getY();
		   x=x+((Math.cos(Math.toRadians((direction+180)%360))*15));
		   y=y+((Math.sin(Math.toRadians((direction+180)%360))*15));
			
		   
		   double A1 = area (x, y, x2, y2, x3, y3);
		   double A2 = area (x1, y1, x, y, x2, y2);  
		   double A3 = area (x3, y3, x4, y4, x, y);
		   double A4 = area (x1, y1, x4, y4, x, y);
		  
		   
		   return (A == A1 + A2 + A3 + A4);
	}
	
	
	public double rectArea(){
		double l=Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1), 2));
		double w=Math.sqrt(Math.pow((x2-x3),2)+Math.pow((y2-y3), 2));
		
		
		return l*w;
	}
	
	public double area(double x1l, double y1l, double x2l, double y2l, double x3l, double y3l)
	{
	   return  Math.abs((x1l*(y2l-y3l) + x2l*(y3l-y1l)+ x3l*(y1l-y2l))/2.0);
	}
}
