import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import graphAndAlgorithm.MyNode;
import utilityclass.MyRect;
import utilityclass.MyShapes;
import utilityclass.SurfacePoint;

public class MyRoad {

	int rId;
	int numOfLan;
	double maxSpeed;
	int roadWidth;
	double roadLength;
	ArrayList<MyShapes> roadPath=new ArrayList<>();
	ArrayList<RoadSegment> segment;
	double roadDirection;
	//these properties is for drawing purpose only.
	SurfacePoint startPoint;
	SurfacePoint endPoint;
	ArrayList<MyRect> roadRect=new ArrayList<>();
	
	
	public void makeRoadRact(SurfacePoint a,SurfacePoint b){
		startPoint=a;
		endPoint=b;
		this.roadDirection = Math.toDegrees(Math.atan2(a.getY() - b.getY(), a.getX() - b.getX())+ Math.PI);
		
		
		makePath();
		
	}
	
	private void makePath() {
		GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, segment.size()+1);
		polyline.moveTo(segment.get(0).start.getX(), segment.get(0).start.getY());
		for(int i=0;i<segment.size();i++){
			polyline.lineTo(segment.get(i).end.getX(), segment.get(i).end.getY());
		}
		MyShapes ms=new MyShapes();
		ms.setPolyline(polyline);
		ms.setColor(Color.BLACK);
		ms.setWidth(this.roadWidth);
		
		MyShapes ms1=new MyShapes();
		ms1.setPolyline(polyline);
		ms1.setColor(Color.YELLOW);
		ms1.setWidth(2);
		this.roadPath.add(ms);
		this.roadPath.add(ms1);
		for(int i=1;i<this.numOfLan;i++){
			GeneralPath pl=new GeneralPath(GeneralPath.WIND_EVEN_ODD, segment.size()+1);
			GeneralPath pl1=new GeneralPath(GeneralPath.WIND_EVEN_ODD, segment.size()+1);
			int x,y;
			int ofset;
			ofset=((this.roadWidth/2)/this.numOfLan)*i;
			x=(int) (segment.get(0).start.getX()+((Math.cos(Math.toRadians((segment.get(0).direction+90+180)%360))*ofset)));
			y=(int) (segment.get(0).start.getY()+((Math.sin(Math.toRadians((segment.get(0).direction+90+180)%360))*ofset)));
			pl.moveTo(x,y);
			
			x=(int) (segment.get(0).start.getX()+((Math.cos(Math.toRadians((segment.get(0).direction+90)%360))*ofset)));
			y=(int) (segment.get(0).start.getY()+((Math.sin(Math.toRadians((segment.get(0).direction+90)%360))*ofset)));
			pl1.moveTo(x,y);
			
			
			for(int j=0;j<segment.size();j++){
				x=(int) (segment.get(j).end.getX()+((Math.cos(Math.toRadians((segment.get(j).direction+90+180)%360))*ofset)));
				y=(int) (segment.get(j).end.getY()+((Math.sin(Math.toRadians((segment.get(j).direction+90+180)%360))*ofset)));
				pl.lineTo(x, y);
				
				x=(int) (segment.get(j).end.getX()+((Math.cos(Math.toRadians((segment.get(j).direction+90)%360))*ofset)));
				y=(int) (segment.get(j).end.getY()+((Math.sin(Math.toRadians((segment.get(j).direction+90)%360))*ofset)));
				pl1.lineTo(x, y);
		
			}
			MyShapes s=new MyShapes();
			s.setColor(Color.WHITE);
			s.setWidth(2);
			s.setPolyline(pl);
			s.dashe=true;
			this.roadPath.add(s);
			
			MyShapes s1=new MyShapes();
			s1.setColor(Color.WHITE);
			s1.setWidth(2);
			s1.dashe=true;
			s1.setPolyline(pl1);
			this.roadPath.add(s1);
		}
	}

	public ArrayList<MyShapes> getRoadPath() {
		return roadPath;
	}

	public void setRoadPath(ArrayList<MyShapes> roadPath) {
		this.roadPath = roadPath;
	}

	public MyRoad() {
		// TODO Auto-generated constructor stub
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getNumOfLan() {
		return numOfLan;
	}

	public void setNumOfLan(int numOfLan) {
		this.numOfLan = numOfLan;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getRoadWidth() {
		return roadWidth;
	}

	public void setRoadWidth(int roadWidth) {
		this.roadWidth = roadWidth;
	}

	public double getRoadLength() {
		return roadLength;
	}

	public void setRoadLength(double roadLength) {
		this.roadLength = roadLength;
	}

	public ArrayList<RoadSegment> getSegment() {
		return segment;
	}

	public void setSegment(ArrayList<RoadSegment> segment) {
		this.segment = segment;
	}
	public RoadSegment getNextSegment(int cId,double d){
		if(cId==7)
		System.out.println("cid "+cId+"  d: "+d);
		for(int i=0;i<segment.size();i++){
			
			if(cId==segment.get(i).getsId()){
				
				if(samedirection(d,segment.get(i))){
					
					if(i+1<segment.size()){
						
						return segment.get(i+1);
					}else{
						
						return null;
					}
				}else{
					if(i-1>=0){
						return segment.get(i-1);
					}else{
						
						return null;
					}
				}
			}
		}
		
		return null;
	}
	
	private boolean samedirection(double d, RoadSegment roadSegment) {
		double diff=Math.abs(d-roadSegment.direction);
		if(diff<=30||diff>=330)
			return true;
		return false;
	}

	private void init2() {
		//solid road
		int x,y,w,h;
		y=startPoint.getY();
		x=startPoint.getX()-(roadWidth/2);
		h=getDistance();
		w=roadWidth;
		MyRect myRect=new MyRect(x, y, w, h);
		myRect.setColor(Color.BLACK);
		
		//divider
		x=startPoint.getX()-1;
		y=startPoint.getY();
		h=getDistance();
		w=2;
		MyRect myRect2 =new MyRect(x, y, w, h);
		myRect2.setColor(Color.YELLOW);
		roadRect.add(myRect);
		roadRect.add(myRect2);
		
		//lane divider
		for(int i=0;i<getDistance();i=i+60){
			int x1,y1,w1,h1;
			h1=2;
			w1=20;
			x1=startPoint.getY()+i;
			y1=startPoint.getX()-(roadWidth/4)+1;
			MyRect r1=new MyRect(y1, x1, h1, w1);
			
			
			y1=startPoint.getX()+(roadWidth/4)+1;
			
			MyRect r2=new MyRect(y1, x1, h1, w1);
			r1.setColor(Color.white);
			r2.setColor(Color.white);
			roadRect.add(r1);
			roadRect.add(r2);

		}
		
		
		
		
		
		
		
	}

	private void init() {
		//solid road
		int x,y,w,h;
		x=startPoint.getX();
		y=startPoint.getY()-(roadWidth/2);
		w=getDistance();
		h=roadWidth;
		MyRect myRect=new MyRect(x, y, w, h);
		myRect.setColor(Color.black);
		
		
		x=startPoint.getX();
		y=startPoint.getY()-1;
		h=2;
		
		MyRect myRect2=new MyRect(x, y, w, h);
		myRect2.setColor(Color.yellow);
		
		roadRect.add(myRect);
		roadRect.add(myRect2);
		
		for(int i=0;i<getDistance();i=i+60){
			int x1,y1,w1,h1;
			h1=2;
			w1=20;
			x1=startPoint.getX()+i;
			y1=startPoint.getY()-(roadWidth/4)+1;
			MyRect r1=new MyRect(x1, y1, w1, h1);
			y1=startPoint.getY()+(roadWidth/4)+1;
			
			MyRect r2=new MyRect(x1, y1, w1, h1);
			r1.setColor(Color.white);
			r2.setColor(Color.white);
			roadRect.add(r1);
			roadRect.add(r2);

		}
		
		
		
	}
	


	public ArrayList<MyRect> getRoadRect() {
		return roadRect;
	}

	public void setRoadRect(ArrayList<MyRect> roadRect) {
		this.roadRect = roadRect;
	}

	private int getDistance() {
		double d=Math.hypot(startPoint.getX()-endPoint.getX(), startPoint.getY()-endPoint.getY());
		return (int)d;
	}

	public RoadSegment getSegmentFromXY(MyNode mn) {
		for(int i=0;i<this.segment.size();i++){
			RoadSegment rs=segment.get(i);
			if(mn.getPosition().getX()==rs.start.getX()&&mn.getPosition().getY()==rs.start.getY()){
				return rs;
			}
		}
		System.out.println("currentsegment null");
		return null;
	}
}
	