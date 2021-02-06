
public class TurningHandeler {
	double x1,y1,x2,y2;
	double angleToTurn;
	double totalDistance;
	double totalincX1=0,totalincY=0, totalincX2=0, totalincX3=0,totalincX4=0;
	public TurningHandeler(double x1,double y1,double x2,double y2,double d) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.angleToTurn=d;
		this.totalDistance=getDistanceX(x1,y1,x2,y2);
	}
	int flag=0;
	public TuriningResult getAngleToTurn(double nx,double ny){
		
		double nxdis=getDistanceX(nx, ny, x2, y2);
		double toAngle=0;
		
		double progressX=((totalDistance-nxdis)/totalDistance);
		double r;
		TuriningResult tr=new TuriningResult();
		if(progressX<=0.3){
			toAngle=angleToTurn*0.1;
			//System.out.println("to angle of hhhhhhhhhhhh: "+toAngle);
			double incx=toAngle*(progressX/0.3);
			r=(incx-totalincX1);
			totalincX1=incx;
			if(r<0){
				flag=1;
			}
			//System.out.println("to angle of hhhhhhhhhhhh: "+r);
			tr.flag=1;
		}else if(progressX<=0.7){
			toAngle=angleToTurn*0.2;
			double incx=toAngle*((progressX-.3)/0.4);
			r=(incx-totalincX4);
			totalincX4=incx;
			tr.flag=1;
		}
		else if(progressX<=0.90){
			toAngle=angleToTurn*0.66;
			double incx=toAngle*((progressX-.7)/0.2);
			r=(incx-totalincX2);
			totalincX2=incx;
			tr.flag=1;
		}else if(progressX>.90&&progressX<.999){
			double dd=getDegree(nx, ny, x2, y2);
			r=dd;
			tr.flag=2;
			//toAngle=7.400;
			//double incx=toAngle*((progressX-.85)/0.1);
			//d=d+(incx-totalincX3)*indicator;
			//totalincX3=incx;
		}else{
			tr.flag=3;
			r=0;
		}
		
		
		
		tr.d=r;
		
		
		
		
		return tr;
	}
	
	
	private double getDistanceX(double x1,double y1,double x2,double y2){
		double d=(Math.atan2(y1 - y2, x1 - x2)+ Math.PI);
		double deg=Math.toDegrees(d);
		//if(flag==0){
			//System.out.println("degree:"+deg);
		//}
		return Math.hypot(x1-x2, y1-y2)*Math.cos(d);
	}
	private double getDegree(double x1,double y1,double x2,double y2) {
		return Math.toDegrees((Math.atan2(y1 - y2, x1 - x2)+ Math.PI));
	}
}
