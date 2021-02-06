import java.awt.Point;
import java.util.ArrayList;

import graphAndAlgorithm.AdjacentNode;
import graphAndAlgorithm.MyNode;
import utilityclass.SurfacePoint;

public class Driver{
	ArrayList<MyRoad> allRoads;
	public ArrayList<MyRoad> getAllRoads() {
		return allRoads;
	}

	public void setAllRoads(ArrayList<MyRoad> allRoads) {
		this.allRoads = allRoads;
	}

	
	public ArrayList<CrossPoint> allTraficPoint;
	int timeConstant2=80;
	Car car;
	float timeConstant=0.2F;
	SurfacePoint startingPoint;
	SurfacePoint detinationPoint;
	int driveMode;
	boolean flag=true;
	int pathIndex=0;
	int currentLan=1;
	boolean turnLeft=false;
	boolean turnRight=false;
	boolean car_break=false;
	boolean move_forward=false;
	
	RoadSegment currentSegment;
	RoadSegment nextSegment;
	MyNode startingNode;
	MyNode destinationNode;
	ArrayList<MyNode> path;
	MyRoad currentRoad;
	MyNode currentNode;
	double direction=0;
	MyNode targetNode;
	int currentRoadId;
	int previousRoadId;
	int turnRoadid;
	double turningTargetX;
	double turningTargetY;
	
	public void setCurrentRoad(MyRoad mr){
		
		
		this.currentRoad=mr;
		currentRoadId=mr.rId;
		previousRoadId=mr.rId;
		this.currentSegment=currentRoad.getSegmentFromXY(this.startingNode);
		this.currentSegment.carsInSegment.add(this.car);
		
		//if(car.id==6){
			//System.out.println("Next segment id: "+nextSegment.sId);
		//}
		
	}
	
	public void setPath(ArrayList<MyNode> path) {
		this.path = path;
		targetNode=path.get(0);
	}

	

	public void setStartingNode(MyNode startingNode) {
		this.startingNode = startingNode;
		this.currentNode=startingNode;
		
	}

	

	public void setCarPosition() {
		find(targetNode,currentNode.getNeighbourNode());
		car.direction=(float) this.direction;
		this.nextSegment=currentRoad.getNextSegment(currentSegment.getsId(), car.direction);
		car.currentLan=this.currentLan;
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		double deg=0;
		double x1,y1;
		if(sameDirectionWithSegment(this.direction)){
			deg=270;
			x1=currentSegment.start.getX();
			y1=currentSegment.start.getY();
		}else{
			deg=90;
			x1=currentSegment.end.getX();
			y1=currentSegment.end.getY();
		}
		
		lanwidth=((currentRoad.numOfLan-car.currentLan)*lanwidth)+lanwidth/2;
		double x=x1+((Math.cos(Math.toRadians((currentSegment.direction+deg)%360))*lanwidth));
		double y=y1+((Math.sin(Math.toRadians((currentSegment.direction+deg)%360))*lanwidth));
		
		car.positionX=(float) x;
		car.positionY=(float) y;
		
		
		
		
		
	}

	public void setStartingPoint(SurfacePoint startingPoint) {
		this.startingPoint = startingPoint;
		car.setPositionX(startingPoint.getX());
		car.setPositionY(startingPoint.getY());
	}

	
	
	
	public void run() {
		updateSegmentsAndRoad();
		switch(this.driveMode){
		case 1:
			angry();
			
			break;
		case 2:
			//fastDrive();
			//System.out.println("deausjdhlas");
			break;
		default:
			//System.out.println("deausjdhlas");
			break;
		
		}


	}

	boolean consederNextSegment=false;
	int coun=0;
	//boolean segmentUpdate=true;
	private void updateSegmentsAndRoad() {
		coun++;
		car.roadid=currentRoad.rId;
		car.currentSegment=this.currentSegment;
		car.sourceID=startingNode.getNodeId();
		car.destanationId=destinationNode.getNodeId();
		double d=segmentRemaind();
		
		
		if(d<50){
			this.consederNextSegment=true;
		}else{
			this.consederNextSegment=false;
		}
		
		if(d<5){
			System.out.println("current  segment :  "+currentSegment.sId+" next segment: ");
			//currentSegment.getCarsInSegment().remove(car);
			
			if(nextSegment==null){
				
				System.out.println("nextSegment null.................");
				SurfacePoint p=new SurfacePoint(0,0);
				if(sameDirectionWithSegment(car.direction)){
					double x=currentSegment.end.getX()+((Math.cos(Math.toRadians((currentSegment.direction)))*5));
					double y=currentSegment.end.getY()+((Math.sin(Math.toRadians((currentSegment.direction)))*5));
					p.setX((int) x);
					p.setY((int) y);
					
				}else{
					double x=currentSegment.start.getX()+((Math.cos(Math.toRadians((currentSegment.direction+180)%360))*5));
					double y=currentSegment.start.getY()+((Math.sin(Math.toRadians((currentSegment.direction+180)%360))*5));
					p.setX((int) x);
					p.setY((int) y);
				}
				
				boolean loopb=false;
				
				for(int i=0;i<allRoads.size();i++){
					MyRoad mr=allRoads.get(i);
					if(mr!=currentRoad){
						ArrayList<RoadSegment> mySeg=mr.segment;
						for(int j=0;j<mySeg.size();j++){
							RoadSegment rs=mySeg.get(j);
							if(rs.isInside(p)){
								currentRoad=mr;
								previousRoadId=currentRoadId;
								currentRoadId=currentRoad.rId;
								nextSegment=rs;
								if(rs.sId==7){
									coun++;
									System.out.println("calling from here........."+coun);
								}
								loopb=true;
								break;
							}
						}
						
						
					}
					if(loopb)
						break;
					else{
					//	System.out.println("loopb");
					}
				}
				
				
				
			}
			if(nextSegment==null){
				//System.out.println("next segment null  car id: "+car.id+"segment id cur: "+currentSegment.sId);
				
			}
			if(nextSegment!=null){
			nextSegment.getCarsInSegment().add(car);
			currentSegment=nextSegment;
			if(currentSegment.sId==7)
				System.out.println(coun);
			//if((this.left_Turning||this.right_Turning))
			nextSegment=currentRoad.getNextSegment(currentSegment.sId, car.getDirection());
			}
			
		}
		
	}

	private double segmentRemaind() {
		if(sameDirectionWithSegment(car.direction)){
			int d=getDistance(car.forntXY, currentSegment.end);
			double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
			int lanRe=currentRoad.numOfLan-car.currentLan;
			double ded=(lanwidth*lanRe)+(lanwidth/2);
			return d-ded;
		}else{
			int d=getDistance(car.forntXY, currentSegment.start);
			double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
			int lanRe=currentRoad.numOfLan-car.currentLan;
			double ded=(lanwidth*lanRe)+(lanwidth/2);
			return d-ded;
		}
		
		
	}

	private boolean sameDirectionWithSegment(double d) {
		double diff=Math.abs(d-currentSegment.direction);
		if(diff<=30||diff>=330)
			return true;
		return false;
	}
	
	private boolean find(MyNode n, ArrayList<AdjacentNode> ajn) {
		for(int i=0;i<ajn.size();i++){
			if(n.getNodeId()==ajn.get(i).getNode().getNodeId()){
				this.direction=ajn.get(i).getDirection();
				//System.out.println(direction);
				turnRoadid=ajn.get(i).roadId;
				return true;
			}
			
			
		}
		
		
		return false;
	}	



	public double turiningAngle=0;
	public boolean left_Turning=false;
	public boolean right_Turning=false;
	private void angry() {
		if(is_signal_green()){
			breakcomplet=false;
			if(front_blocked() ){
				//System.out.println("front block");
				if(right_blocked()){
					if(left_clear()){
						this.turnLeft=true;
					}else{
						this.car_break=true;
					}
				}
				else{
					this.turnRight=true;
					
				}
			}else{
				this.move_forward=true;
			}
			
			drive();
		}else{
			//signal is not green
			//System.out.println("signal is not green");
			if(!breakcomplet){
				car_break2();
				car_move();
			}
			
			
		}
		boolean tar=targetNode.insideNode(new SurfacePoint((int)car.positionX, (int)car.positionY), car.direction);
		//int ds=getDistance(new SurfacePoint((int)car.positionX, (int)car.positionY), targetNode.getPosition());
		
		if(tar){
			System.out.println("dd:========================= ");
			currentNode=targetNode;
			if(!currentNode.equals(destinationNode)){
				targetNode=path.get(++pathIndex);
				if(find(targetNode,currentNode.getNeighbourNode())){
					//System.out.println("dd: "+car.direction);
					//car.direction=(float) this.direction;
					right_Turning=false;
					left_Turning=false;
					//System.out.println("car direction: "+car.direction+"    turn: "+this.direction);
					
					/*
					
					if(this.direction>car.direction){
						//left turning
						//if(car.id==6)
						//System.out.println("Current Road id: "+currentRoad.rId+"+++++++++++++++++++++");
						double difference=this.direction-car.direction;
						if(difference>180){
							//right turning
							
							right_Turning=true;
							
							this.turiningAngle=difference-180;
						}else{
							//System.out.println("Current Road id: "+currentRoad.rId+"+++++++++++++++++++++");
							//left turning
							left_Turning=true;
							this.turiningAngle=difference;
						}
						
					}else{
						//right turning
						double difference=car.direction-this.direction;
						if(difference>180){
							//left turning
							left_Turning=true;
							this.turiningAngle=difference-180;
						}else{
							//right turning
							right_Turning=true;
							this.turiningAngle=difference;
						}
						
					}*/
					right_Turning=false;
					left_Turning=false;
					if(car.direction==0&&this.direction==90){
						right_Turning=true;
						
					}else if(car.direction==0&&this.direction==270){
						left_Turning=true;
						System.out.println("left turning11111");
					}else if(car.direction==90&&this.direction==0){
						left_Turning=true;
						System.out.println("left turning2");
					}else if(car.direction==90&&this.direction==180){
						right_Turning=true;
						System.out.println("Right turning11111");
					}else if(car.direction==270&&this.direction==0){
						right_Turning=true;
					}else if(car.direction==270&&this.direction==180){
						left_Turning=true;
						System.out.println("left turning3");
					}
					this.turiningAngle=90;
					
					//System.out.println("dd: "+car.direction);
				}
			}
			else{
				
				flag=false;
				currentSegment.getCarsInSegment().remove(car);
			}
		}
		
		if(car.velocity>currentRoad.maxSpeed){
			car.velocity=(float) currentRoad.maxSpeed;
			car.acceleration=0;
			//this.turnRight=true;
			
		}
		if(car.maxSpeed<car.velocity){
			car.acceleration=0;
			car.velocity=(float) car.maxSpeed;
		}
		
		
	}

	private void drive() {
		if(car.status==0&&!car_break){
			car.acceleration=2;
			car.status=1;
		}
		if(this.left_Turning&&car.turningproblem){
			car_turning_left();
		}else if(this.right_Turning&&car.turningproblem){
			car_turning_right();
		}
		else if(this.car_break){
			car_break();
		}else if(this.turnLeft){
			turnLeft();
		}else if(this.turnRight){
			turnRight();
		}else if(this.move_forward){
			
		}
		car_move();
	}
	private void car_turning_right() {
		//System.out.println("calling.......");
		if(calculating_turining_point){
			double targetX,targetY;
			
			double d=getTargetRoaddirection(previousRoadId);
			double width=getTargetRoadWidth(turnRoadid);
			int tl=getRoadLanFromRoadId(turnRoadid);
			double carlan=car.currentLan;
			double lw=(width/2.0)/tl;
			
			double verticaldistance=width-(lw*(carlan-0.5))-11;
			//System.out.println("vertical distance : "+verticaldistance);
			targetX=(car.positionX+((Math.cos(Math.toRadians((d)))*verticaldistance)));
			targetY=(car.positionY+((Math.sin(Math.toRadians((d)))*verticaldistance)));
			
			
			// 1st increment
			
			
			double d2=this.direction;
			getNextRoadsSegment(d2);
			double width2=getTargetRoadWidth(previousRoadId);
			int tl2=getRoadLanFromRoadId(previousRoadId);
			double lw2=(width2/2.0)/tl2;
			double  horizontaldistance=width2-(lw*(carlan));
			//System.out.println("horizontaldistance distance : "+horizontaldistance);
			this.turningTargetX=(targetX+((Math.cos(Math.toRadians((d2)))*horizontaldistance)));
			this.turningTargetY=(targetY+((Math.sin(Math.toRadians((d2)))*horizontaldistance)));
			//MyPanel.points.add(new Point((int)turningTargetX,(int)turningTargetY));
			//System.out.println("Car turning left======initial position "+car.positionX+" , "+car.positionY+"  final position "+turningTargetX+" "+turningTargetY);;
			calculating_turining_point=false;
			this.turningH=new TurningHandeler(car.positionX, car.positionY, turningTargetX, turningTargetY, turiningAngle);
			
			
			
			
			
			
		}
		TuriningResult tr=turningH.getAngleToTurn(car.positionX, car.positionY);
		if(tr.flag==1){
			
			float d=(float) tr.d;
			//car.direction=car.direction-d;
			if(car.direction<0){
				//car.direction=360+car.direction;
				//System.out.println("Right turining: "+car.direction);
			}
			car.direction=(car.direction+d)%360;
			//System.out.println("Right turining: "+tr.d);
			
			//car.direction=(car.direction+d)%360;
			
			
		}else if(tr.flag==2){
			float d=(float) tr.d;
			//System.out.println("Right turining: "+tr.d);
			car.direction=d;
			
			
		}else{
			left_Turning=false;
			car.direction=(float) this.direction;
			calculating_turining_point=true;
			turiningAngle=0;
			turningH=null;
			car.turningproblem=false;
			
		}
		
		
		
		
		
		/*if(turiningAngle>=0){
			car.direction=car.direction-3;
			if(car.direction<0){
				car.direction=360+car.direction;
				//System.out.println("Right turining: "+car.direction);
			}
			turiningAngle-=3;
		}else{
			right_Turning=false;
			car.direction=(float) this.direction;
			calculating_turining_point=true;
		}*/
		
	}

	boolean calculating_turining_point=true;
	double ttX,ttY;
	TurningHandeler turningH;
	private void car_turning_left() {
	//if(car.id==6){
		//System.out.println("car 6 is turning left...................");
	//}
		if(calculating_turining_point){
			double targetX,targetY;
			
			double d=getTargetRoaddirection(previousRoadId);
			double width=getTargetRoadWidth(turnRoadid);
			int tl=getRoadLanFromRoadId(turnRoadid);
			double carlan=car.currentLan;
			//System.out.println("turn id roadid"+turnRoadid);
			double lw=(width/2.0)/tl;
			double verticaldistance=lw*(carlan-0.5)-11;
			//System.out.println("turn id roadid : "+lw);
			targetX=(car.positionX+((Math.cos(Math.toRadians((d)))*verticaldistance)));
			targetY=(car.positionY+((Math.sin(Math.toRadians((d)))*verticaldistance)));
			
			
			// 1st increment
			
			
			double d2=this.direction;
			getNextRoadsSegment(d2);
			double width2=getTargetRoadWidth(previousRoadId);
			int tl2=getRoadLanFromRoadId(previousRoadId);
			double lw2=(width2/2.0)/tl2;
			double  horizontaldistance=(lw*(carlan));
			this.turningTargetX=(targetX+((Math.cos(Math.toRadians((d2)))*horizontaldistance)));
			this.turningTargetY=(targetY+((Math.sin(Math.toRadians((d2)))*horizontaldistance)));
			
			//MyPanel.points.add(new Point((int)turningTargetX,(int)turningTargetY));
			calculating_turining_point=false;
			this.turningH=new TurningHandeler(car.positionX, car.positionY, turningTargetX, turningTargetY, turiningAngle);
			
			
			//System.out.println("Car turning Right======initial position "+car.positionX+" , "+car.positionY+"  final position "+turningTargetX+" "+turningTargetY);;
			
			
			
			
		}
		
		
		TuriningResult tr=turningH.getAngleToTurn(car.positionX, car.positionY);
		if(tr.flag==1){
			float d=(float) tr.d;
			
			car.direction=car.direction-d;
			if(car.direction<0){
				car.direction=360+car.direction;
				//System.out.println("Right turining: "+car.direction);
			}
			
		}else if(tr.flag==2){
			float d=(float) tr.d;
			car.direction=d;
			
			
		}else{
			right_Turning=false;
			car.direction=(float) this.direction;
			calculating_turining_point=true;
			turiningAngle=0;
			turningH=null;
			car.turningproblem=false;
			
		}
		
		
		
		/*if(turiningAngle>=0){
			car.direction=(car.direction+3)%360;
			turiningAngle-=3;
		}else{
			left_Turning=false;
			car.direction=(float) this.direction;
			calculating_turining_point=true;
		}*/
		
	}

	private double getTargetRoadWidth(int id){
		for(int i=0;i<allRoads.size();i++){
			MyRoad mr=allRoads.get(i);
			if(mr.rId==id)
				return mr.roadWidth;
		}
		
		return 0;
	}
	
	private double getTargetRoaddirection(int id){
		for(int i=0;i<allRoads.size();i++){
			MyRoad mr=allRoads.get(i);
			if(mr.rId==id)
				return mr.roadDirection;
		}
		
		return 400;
	}
	
	private int getRoadLanFromRoadId(int id) {
		for(int i=0;i<allRoads.size();i++){
			MyRoad mr=allRoads.get(i);
			if(mr.rId==id)
				return mr.numOfLan;
		}
		
		return -1;
	}
	boolean changinglan=false;
	double rx,ry;
	private void turnRight() {
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		
		
		if(!changinglan){
			car.direction+=30;
			car.direction=car.direction%360;
			//car.destinationLan=2;
			rx=car.positionX;
			ry=car.positionY;
			changinglan=true;
		}
		double l=Math.hypot(rx-car.positionX, ry-car.positionY);
		l=l*0.5;
		
		if(lanwidth-l<0.3){
			//car.positionY=lan2y;
			car.direction-=30;
			if(car.direction<0){
				car.direction=360-car.direction;
			}
			car.currentLan=2;
			this.turnRight=false;
			changinglan=false;
			//this.turnLeft=true;
		}
		
	}

	private void turnLeft() {
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		
		if(!changinglan){
			
			car.direction-=30;
			if(car.direction<0){
				car.direction=360+car.direction;
				car.destinationLan=1;
				rx=car.positionX;
				ry=car.positionY;
				//System.out.println("left: "+car.direction);
			}
			
			
			changinglan=true;
		}
		double l=Math.hypot(rx-car.positionX, ry-car.positionY);
		l=l*0.5;
		if(lanwidth-l<0.3){
			//car.positionY=lan1y;
			car.direction+=30;
			car.direction=car.direction%360;
			car.currentLan=1;
			this.turnLeft=false;
			changinglan=false;
			//this.turnRight=true;
		}
	}
	int breack_counter=0;
	private void car_break() {
		if(breack_counter<1){
			car.acceleration=(float) ((float) (-1*(car.maxSpeed/(timeConstant*timeConstant2)))-0.2);
			//System.out.println("Breaked: "+car.acceleration);
			car.acceleration=-15.5F;
		}
		
		breack_counter++;
		//System.out.println("break1: "+car.acceleration);
		if(car.velocity==0||car.velocity<0.03||breack_counter>30){
			//System.out.println("break");
			car.velocity=0;
			car.acceleration=0;
			breack_counter=0;
			this.car_break=false;
			car.status=0;
		}
		
	}
	boolean breakcomplet=false;
	private void car_break2() {
		if(breack_counter<1){
			//car.acceleration=(float) ((float) (-1*(car.maxSpeed/(timeConstant*timeConstant2)))-0.2);
			//System.out.println("Breaked: "+car.acceleration);
			car.acceleration=-2.5F;
		}
		
		breack_counter++;
		//System.out.println("break1: "+car.acceleration);
		if(car.velocity==0||car.velocity<0.03||breack_counter>10){
			//System.out.println("break");
			car.velocity=0;
			car.acceleration=0;
			breack_counter=0;
			this.car_break=false;
			car.status=0;
			breakcomplet=true;
		}
		
	}

	//boolean insignalzone=false;
	private boolean is_signal_green() {
		
		
		for(int i=0;i<allTraficPoint.size();i++){
			CrossPoint xp=allTraficPoint.get(i);
			for(int j=0;j<xp.allSignal.size();j++){
				RoadSignal rds=xp.allSignal.get(j);
				
				boolean t=rds.isInside(new SurfacePoint((int)car.positionX, (int)car.positionY));
				if(t){
					//insignalzone=true;
					if(rds.rgb!=0){
						return false;
					}else{
						return true;
					}
				}else{
					//insignalzone=false;
				}
			}
		}
		return true;
	}

	private void getNextRoadsSegment(double d){
		nextSegment=currentRoad.getNextSegment(currentSegment.sId, d);
	}
	
	private boolean front_blocked() {
		ArrayList<Car> cars=currentSegment.getCarsInSegment();
		double t=timeConstant*timeConstant2;
		double a=car.maxSpeed/(t);
		double l=(car.maxSpeed*car.maxSpeed)/(2*a)+20;
		l=35;
		for(int i=0;i<cars.size();i++){
			Car c=cars.get(i);
			if(car!=c){
			double dis=getCarDistance(c);
			
			//System.out.println("Safe distance: "+l);
			if(l>dis){
				//System.out.println(dis);
				if(infocus(c)){
					return true;
				}
				}
			}
		}
		
		if(this.consederNextSegment&&nextSegment!=null){
			ArrayList<Car> cars2=nextSegment.getCarsInSegment();
			for(int i=0;i<cars2.size();i++){
				Car c=cars2.get(i);
				double dis=getCarDistance(c);
				if(l>dis){
					if(infocus(c)){
						return true;
					}
				}
			}
			
		}
		return false;
	}

	private boolean inFocusForLeft(Car c){
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		double X,Y;
		X=(car.positionX+((Math.cos(Math.toRadians((car.direction+270)%360))*lanwidth)));
		Y=(car.positionY+((Math.sin(Math.toRadians((car.direction+270)%360))*lanwidth)));
		
		
		double Ax, Ay,a1x,a1y,a2x,a2y,a3x,a3y,a4x,a4y;
		int safeDistanceBack=25,forntDistanc=60,coverWidth=18;
		
		
		Ax=(X+((Math.cos(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		Ay=(Y+((Math.sin(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		
		
		a1x=Ax+((Math.cos(Math.toRadians((car.direction+90)%360))*(coverWidth/2)));
		a1y=Ay+((Math.sin(Math.toRadians((car.direction+90)%360))*(coverWidth/2)));
		
		a2x=a1x+((Math.cos(Math.toRadians((car.direction+270)%360))*coverWidth));
		a2y=a1y+((Math.sin(Math.toRadians((car.direction+270)%360))*coverWidth));
		
		a3x=a2x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc));
		a3y=a2y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc));
		
		a4x=a1x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc));
		a4y=a1y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc));
		
		
		
		if(isInside(a1x, a1y, a2x, a2y, a3x, a3y,a4x,a4y, c.positionX, c.positionY)){
			//System.out.println("working");
			return true;
			
			
			
		}
		
		
		return true;
	}
	
	private boolean inFocusForRight(Car c){
		
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		double X,Y;
		X=(car.positionX+((Math.cos(Math.toRadians((car.direction+90)%360))*lanwidth)));
		Y=(car.positionY+((Math.sin(Math.toRadians((car.direction+90)%360))*lanwidth)));
		
		
		double Ax, Ay,a1x,a1y,a2x,a2y,a3x,a3y,a4x,a4y;
		int safeDistanceBack=25,forntDistanc=60,coverWidth=18;
		
		
		Ax=(X+((Math.cos(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		Ay=(Y+((Math.sin(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		
		
		a1x=Ax+((Math.cos(Math.toRadians((car.direction+90)%360))*(coverWidth/2)));
		a1y=Ay+((Math.sin(Math.toRadians((car.direction+90)%360))*(coverWidth/2)));
		
		a2x=a1x+((Math.cos(Math.toRadians((car.direction+270)%360))*coverWidth));
		a2y=a1y+((Math.sin(Math.toRadians((car.direction+270)%360))*coverWidth));
		
		a3x=a2x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc));
		a3y=a2y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc));
		
		a4x=a1x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc));
		a4y=a1y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc));
		
		
		
		if(isInside(a1x, a1y, a2x, a2y, a3x, a3y,a4x,a4y, c.positionX, c.positionY)){
			//System.out.println("working");
			return true;
			
			
			
		}
		
		
		return true;
	}
	
	
	private boolean infocus(Car c) {
		
		//rad=Math.toDegrees(Math.atan(ratio));
		double Ax, Ay,a1x,a1y,a2x,a2y,a3x,a3y,a4x,a4y;
		int safeDistanceBack=0,forntDistanc=65,coverWidth=18;
		if(this.changinglan){
			coverWidth=30;
		}
		Ax=(int) (car.backXY.getX()+((Math.cos(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		Ay=(int) (car.backXY.getY()+((Math.sin(Math.toRadians((car.direction+180)%360))*safeDistanceBack)));
		
		
		a1x=(int) (Ax+((Math.cos(Math.toRadians((car.direction+90)%360))*(coverWidth/2))));
		a1y=(int) (Ay+((Math.sin(Math.toRadians((car.direction+90)%360))*(coverWidth/2))));
		
		a2x=(int) (a1x+((Math.cos(Math.toRadians((car.direction+270)%360))*coverWidth)));
		a2y=(int) (a1y+((Math.sin(Math.toRadians((car.direction+270)%360))*coverWidth)));
		
		a3x=(int) (a2x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc)));
		a3y=(int) (a2y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc)));
		
		a4x=(int) (a1x+((Math.cos(Math.toRadians((car.direction)))*forntDistanc)));
		a4y=(int) (a1y+((Math.sin(Math.toRadians((car.direction)))*forntDistanc)));
		
		
		
		if(isInside(a1x, a1y, a2x, a2y, a3x, a3y,a4x,a4y, c.positionX, c.positionY)){
			
			return true;
			
			
			
		}
		
		
		
		
		return false;
	}
	boolean f=true;
	   
	public boolean isInside(double x1, double y1, double x2, double y2, double x3, double y3,double x4,double y4, float x, float y)
	{  
	   /* Calculate area of triangle ABC */
	   double A = rectArea (x1, y1, x2, y2, x3, y3);
	 
	   double A1 = area (x, y, x2, y2, x3, y3);
	   double A2 = area (x1, y1, x, y, x2, y2);  
	   double A3 = area (x3, y3, x4, y4, x, y);
	   double A4 = area (x1, y1, x4, y4, x, y);
	   
	   /* Check if sum of A1, A2 and A3 is same as A */
	 /*  if(f){
		   System.out.println("x1,y1= "+x1+", "+y1);
		   System.out.println("x2,y2= "+x2+", "+y2);
		   System.out.println("x3,y3= "+x3+", "+y3);
		   System.out.println("x4,y4= "+x4+", "+y4);
		   
		   System.out.println("point"+x+", "+y);
		   
		   System.out.println("A1 "+A1);
		   System.out.println("A2 "+A2);
		   System.out.println("A3 "+A3);
		   System.out.println("A4 "+A4);
		   f=false;
		   
	   }
	   */
	   double t=A1+A2+A3+A4;
	   //System.out.println("Total area: " +A+" Susmmed area: "+t);
	   
	   
	   return (A == A1 + A2 + A3 + A4);
	}
	 

	public double rectArea(double x1,double y1,double x2,double y2,double x3,double y3){
		double l=Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1), 2));
		double w=Math.sqrt(Math.pow((x2-x3),2)+Math.pow((y2-y3), 2));
		
		
		return l*w;
	}
	
	public double area(double x1, double y1, double x2, double y2, double x3, double y3)
	{
	   return  Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
	}
	
	
	private double getCarDistance(Car c) {
		double d=Math.hypot(car.forntXY.getX()-c.backXY.getX(),car.forntXY.getY()-c.forntXY.getY());
		return d;
	}

	private double getCarDistance_right(Car c) {
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		double X,Y;
		X=(car.positionX+((Math.cos(Math.toRadians((car.direction+90)%360))*lanwidth)));
		Y=(car.positionY+((Math.sin(Math.toRadians((car.direction+90)%360))*lanwidth)));
		
		
		double d=Math.hypot(X-c.positionX,Y-c.positionY);
		return d;
	}
	
	
	private double getCarDistance_Left(Car c) {
		double lanwidth=((currentRoad.roadWidth/2)/currentRoad.numOfLan);
		double X,Y;
		X=(car.positionX+((Math.cos(Math.toRadians((car.direction+270)%360))*lanwidth)));
		Y=(car.positionY+((Math.sin(Math.toRadians((car.direction+270)%360))*lanwidth)));
		
		
		double d=Math.hypot(X-c.positionX,Y-c.positionY);
		return d;
	}
	
	private boolean in_front(Car c) {
		if((315<=c.direction&&315<=car.direction)||(45>=c.direction&&45>=car.direction)){
			if(car.positionX<c.positionX&&car.velocity>c.velocity)
				return true;
			return false;
		}else if((135>=c.direction&&135>=car.direction)&&(45<c.direction&&45<car.direction)){
			if(car.positionY<c.positionY&&car.velocity>c.velocity)
				return true;
			return false;
		}
		else if((225>=c.direction&&225>=car.direction)&&(135<c.direction&&135<car.direction)){
			if(car.positionX>c.positionX&&car.velocity>c.velocity)
				return true;
			return false;
		}
		else if((314>=c.direction&&314>=car.direction)&&(225<c.direction&&225<car.direction)){
			if(car.positionY>c.positionY&&car.velocity>c.velocity)
				return true;
			return false;
		}
		return false;
	}

	

	private boolean right_blocked() {
		//if(insignalzone)
			//return true;
		int lani=car.currentLan;
		double t=timeConstant*timeConstant2;
		double a=car.maxSpeed/(t);
		double l=(car.maxSpeed*car.maxSpeed)/(2*a)+20;
		l=80;
		if(lani<currentRoad.numOfLan){
			ArrayList<Car> cars=currentSegment.getCarsInSegment();
			for(int i=0;i<cars.size();i++){
				Car c=cars.get(i);
				double dis=getCarDistance_right(c);
				if(l>dis){
					if(car!=c){
						if(inFocusForRight(c)){
							return true;
						}
					}
				}
			}
			
			if(this.consederNextSegment&&nextSegment!=null){
				ArrayList<Car> cars2=nextSegment.getCarsInSegment();
				for(int i=0;i<cars2.size();i++){
					Car c=cars2.get(i);
					double dis=getCarDistance_right(c);
					if(l>dis){
						if(inFocusForRight(c)){
							return true;
						}
					}
				}
				
			}
			return false;
			
			
		}else{
			return true;
		}
		
	}



	private boolean left_clear() {
		//if(insignalzone)
			//return false;
		double t=timeConstant*timeConstant2;
		double a=car.maxSpeed/(t);
		double l=(car.maxSpeed*car.maxSpeed)/(2*a)+20;
		l=50;
		if(car.currentLan>1){
			ArrayList<Car> cars=currentSegment.getCarsInSegment();
			for(int i=0;i<cars.size();i++){
				Car c=cars.get(i);
				double dis=getCarDistance_Left(c);
				if(l>dis){
					if(car!=c){
					if(inFocusForLeft(c)){
						return false;
					}
					}
				}
			}
			
			if(this.consederNextSegment&&nextSegment!=null){
				ArrayList<Car> cars2=nextSegment.getCarsInSegment();
				for(int i=0;i<cars2.size();i++){
					Car c=cars2.get(i);
					double dis=getCarDistance_Left(c);
					if(l>dis){
						if(inFocusForLeft(c)){
							return false;
						}
					}
				}
				
			}
			return true;
			
			
			
		}
		else{
			return false;
		}
		
	}

	private void car_move() {
		float direction=car.getDirection();
		float acceleration=car.getAcceleration();
		float velocity=car.getVelocity();
		
		float acX=car.getAccelerationX();
		float acY=car.getAccelerationY();
		float veX=car.getInitVelocityX();
		float veY=car.getInitVelocityY();
		float poX=car.getPositionX();
		float poY=car.getPositionY();
		
		distributeCompunent(acX,acY,veX,veY,poX,poY,direction,acceleration,velocity);
		this.car.updateForntBack();
		
		
	}

	private void distributeCompunent(float acX, float acY, float veX, float veY, float poX, float poY, float direction,
			float acceleration, float velocity) {
		
		double radians = Math.toRadians(direction);
		acX=(float) (acceleration*Math.cos(radians));
		acY=(float) (acceleration*Math.sin(radians));
		car.setAccelerationX(acX);
		car.setAccelerationY(acY);
		car.setAccelerationX(acX);
		car.setAccelerationY(acY);
		
		//car.direction++;
		//if(car.direction>360)
			//car.direction=0;
		
		veX=(float) (velocity*Math.cos(radians));
		veY=(float) (velocity*Math.sin(radians));
		
		
		car.positionX+=(veX*timeConstant)+(0.5F*car.accelerationX*timeConstant*timeConstant);
		car.positionY+=(veY*timeConstant)+(0.5F*car.accelerationY*timeConstant*timeConstant);
		car.setInitVelocityX(veX);
		car.setInitVelocityY(veY);
		
		car.initVelocityX+=car.accelerationX*timeConstant;
		car.initVelocityY+=car.accelerationY*timeConstant;
		
		float ve=(float) Math.sqrt(((car.initVelocityX*car.initVelocityX)+(car.initVelocityY*car.initVelocityY)));
		car.setVelocity(ve);
		//car.myPanel.setCarXY(Math.round(car.positionX), Math.round(car.positionY));
		
		
		
		
	}	
	
	private int getDistance(SurfacePoint startPoint,SurfacePoint endPoint) {
		double d=Math.hypot(startPoint.getX()-endPoint.getX(), startPoint.getY()-endPoint.getY());
		return (int)d;
	}

	public ArrayList<MyNode> getPath() {
		return path;
	}
	public SurfacePoint getDetinationPoint() {
		return detinationPoint;
	}

	public void setDetinationPoint(SurfacePoint detinationPoint) {
		this.detinationPoint = detinationPoint;
	}

	public int getDriveMode() {
		return driveMode;
	}

	public void setDriveMode(int driveMode) {
		this.driveMode = driveMode;
	}

	public Driver(Car c) {
		// TODO Auto-generated constructor stub
		this.car=c;
	}
	public MyNode getDestinationNode() {
		return destinationNode;
	}

	public void setDestinationNode(MyNode destinationNode) {
		this.destinationNode = destinationNode;
	}

	public SurfacePoint getStartingPoint() {
		return startingPoint;
		
	}
	public MyNode getStartingNode() {
		return startingNode;
	}
}
