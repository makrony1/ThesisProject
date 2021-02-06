import java.util.ArrayList;

import utilityclass.SurfacePoint;

public class RoadSegment {
	int sId;
	SurfacePoint start;
	SurfacePoint end;
	boolean node;
	double direction=0;
	double roadWidth;
	double length;
	double x1,y1,x2,y2,x3,y3,x4,y4;
	
	
	public double getRoadWidth() {
		return roadWidth;
	}
	public void setRoadWidth(double roadWidth) {
		this.roadWidth = roadWidth;
		initiallizeCorners();
		
		
		
		
		
	}
	private void initiallizeCorners() {
		
		double Ax, Ay,a1x,a1y,a2x,a2y,a3x,a3y,a4x,a4y;
		
		
		Ax=this.start.getX();
		Ay=this.start.getY();
		
		
		a1x=Ax+((Math.cos(Math.toRadians((this.direction+90)%360))*(this.roadWidth/2)));
		a1y=Ay+((Math.sin(Math.toRadians((this.direction+90)%360))*(this.roadWidth/2)));
		
		a2x=a1x+((Math.cos(Math.toRadians((this.direction+270)%360))*this.roadWidth));
		a2y=a1y+((Math.sin(Math.toRadians((this.direction+270)%360))*this.roadWidth));
		
		a3x=a2x+((Math.cos(Math.toRadians((this.direction)))*this.length));
		a3y=a2y+((Math.sin(Math.toRadians((this.direction)))*this.length));
		
		a4x=a1x+((Math.cos(Math.toRadians((this.direction)))*this.length));
		a4y=a1y+((Math.sin(Math.toRadians((this.direction)))*this.length));
		
		
		x1=a1x;
		y1=a1y;
		
		x2=a2x;
		y2=a2y;
		
		x3=a3x;
		y3=a3y;
		
		x4=a4x;
		y4=a4y;
		
		
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public SurfacePoint getStart() {
		return start;
	}
	public void setStart(SurfacePoint start) {
		this.start = start;
	}
	public SurfacePoint getEnd() {
		return end;
	}
	public void setEnd(SurfacePoint end) {
		this.end = end;
		
		this.direction = Math.toDegrees(Math.atan2(this.start.getY() - this.end.getY(), this.start.getX() - this.end.getX())+ Math.PI);
		length=Math.hypot(start.getX()-end.getX(), start.getY()-end.getY());
		
	}
	public boolean isNode() {
		return node;
	}
	public void setNode(boolean node) {
		this.node = node;
	}
	public ArrayList<Car> getCarsInSegment() {
		return carsInSegment;
	}
	public void setCarsInSegment(ArrayList<Car> carsInSegment) {
		this.carsInSegment = carsInSegment;
	}
	public void setCarinthisSegment(Car c){
		carsInSegment.add(c);
	}
	
	public boolean isInside(SurfacePoint p){
		//System.out.println("x:"+p.getX()+"  y: "+p.getY());
		   /* Calculate area of triangle ABC */
		   double A = rectArea ();
		   double x,y;
		   x=p.getX();
		   y=p.getY();
		 
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
	
	
	ArrayList<Car> carsInSegment=new ArrayList<>();

}
