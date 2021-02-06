import utilityclass.SurfacePoint;

public class RoadSignal {
	public int rgb=2;
	public long startime;
	public double x1,y1,x2,y2,x3,y3,x4,y4;
	
	public int lx1,lx2;
	public int ly1,ly2;
	
	
	public void updateAll(){
		lx1=(int) x3;
		lx2=(int) x4;
		
		ly1=(int) y3;
		ly2=(int) y4;
		
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
	
	
}
