import java.awt.Color;
import java.util.ArrayList;

import utilityclass.MyRect;
import utilityclass.SurfacePoint;

public class Road {
	SurfacePoint startPoint;
	SurfacePoint endPoint;
	int numOfLan;
	int widthOfRoad;
	ArrayList<MyRect> roadRect=new ArrayList<>();
	
	
	public Road(SurfacePoint s1,SurfacePoint s2) {
		this.startPoint=s1;
		this.endPoint=s2;
		this.numOfLan=2;
		this.widthOfRoad=90;
		
		init();
	}
	
	public Road(SurfacePoint s1,SurfacePoint s2,int x) {
		this.startPoint=s1;
		this.endPoint=s2;
		this.numOfLan=2;
		this.widthOfRoad=90;
		
		init2();
	}


	private void init2() {
		//solid road
		int x,y,w,h;
		y=startPoint.getY();
		x=startPoint.getX()-(widthOfRoad/2);
		h=getDistance();
		w=widthOfRoad;
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
			y1=startPoint.getX()-(widthOfRoad/4)+1;
			MyRect r1=new MyRect(y1, x1, h1, w1);
			
			
			y1=startPoint.getX()+(widthOfRoad/4)+1;
			
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
		y=startPoint.getY()-(widthOfRoad/2);
		w=getDistance();
		h=widthOfRoad;
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
			y1=startPoint.getY()-(widthOfRoad/4)+1;
			MyRect r1=new MyRect(x1, y1, w1, h1);
			y1=startPoint.getY()+(widthOfRoad/4)+1;
			
			MyRect r2=new MyRect(x1, y1, w1, h1);
			r1.setColor(Color.white);
			r2.setColor(Color.white);
			roadRect.add(r1);
			roadRect.add(r2);

		}
		
		
		
	}


	private int getDistance() {
		double d=Math.hypot(startPoint.getX()-endPoint.getX(), startPoint.getY()-endPoint.getY());
		return (int)d;
	}
	
}
