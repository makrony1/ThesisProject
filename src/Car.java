import java.awt.Color;
import java.util.ArrayList;

import graphAndAlgorithm.AdjacentNode;
import graphAndAlgorithm.MyNode;
import utilityclass.SurfacePoint;

public class Car {
	MyNode startingNode;
	MyNode destinationNode;
	ArrayList<MyNode> path;
	int status=1;
	int id;
	int pathindex=0;
	MyNode currentNode;
	//double direction;
	MyNode targetNode;
	int currentLan=1;
	int destinationLan=0;
	float positionX;
	float positionY;
	float accelerationX;
	float accelerationY;
	float initVelocityY;
	float initVelocityX;
	int driveMode;
	float acceleration;
	float velocity;
	float direction;
	double maxSpeed;
	int sourceID;
	int destanationId;
	boolean turningproblem=true;
	
	RoadSegment currentSegment;
	RoadSegment nextSegment;
	RoadSegment previousSegment;
	SurfacePoint forntXY;
	SurfacePoint backXY;
	int roadid ;
	public int widthOfCar=12;
	public int lengthOfCar=16;
	Color color=Color.red;
	
	private float timeConstant=0.3f;
	
	
	public MyNode getStartingNode() {
		return startingNode;
	}

	public void setStartingNode(MyNode startingNode) {
		this.startingNode = startingNode;
		this.currentNode=startingNode;
		this.positionX=currentNode.getPosition().getX();
		this.positionY=currentNode.getPosition().getY();
		this.velocity=8;
	}

	public MyNode getDestinationNode() {
		return destinationNode;
	}

	public void setDestinationNode(MyNode destinationNode) {
		this.destinationNode = destinationNode;
	}

	public ArrayList<MyNode> getPath() {
		return path;
	}

	public void setPath(ArrayList<MyNode> path) {
		this.path = path;
		targetNode=path.get(0);
		find(targetNode,currentNode.getNeighbourNode());
		//this.direction=currentNode.getNeighbourNode()
		
	}




	
	
	
	public Car() {
		
		positionX=50;
		positionY=208;
		accelerationX=0;
		accelerationY=0;
		initVelocityX=0;
		initVelocityY=0;
		driveMode=1;
		acceleration=2;
		direction=0;
		forntXY=new SurfacePoint(0, 0);
		backXY=new SurfacePoint(0, 0);
	}
	
	public void car_move(){
		switch(this.driveMode){
		case 1:
			angry();
			
			break;
		case 2:
			//fastDrive();
			System.out.println("deausjdhlas");
			break;
		default:
			System.out.println("deausjdhlas");
			break;
		
		} 
	}
	
	
	
	
	
	private void angry() {
		if(front_blocked() ){	
			if(right_blocked()){
				//turn left
			}
			else{
				//turn right
			}
		}else{
			//move forward
			move_forward();
		}
		System.out.println(getDistance(new SurfacePoint((int)this.positionX, (int)this.positionY), targetNode.getPosition()));
		if(10>getDistance(new SurfacePoint((int)this.positionX, (int)this.positionY), targetNode.getPosition())){
			
			currentNode=targetNode;
			targetNode=path.get(++pathindex);
			System.out.println("prev direc: "+targetNode.getNodeId());
			find(targetNode,currentNode.getNeighbourNode());
			
			System.out.println("new direc: "+currentNode.getNodeId());
		}
		
	}

	private void move_forward() {
		double radians = Math.toRadians(direction);
		setAccelerationX((float) (acceleration*Math.cos(radians)));
		setAccelerationY((float) (acceleration*Math.sin(radians)));
		
		
		//car.direction++;
		if(this.direction>360)
			this.direction=0;
		
		float veX=(float) (velocity*Math.cos(radians));
		float veY=(float) (velocity*Math.sin(radians));
		
		
		this.positionX+=(veX*timeConstant)+(0.5F*this.accelerationX*timeConstant*timeConstant);
		this.positionY+=(veY*timeConstant)+(0.5F*this.accelerationY*timeConstant*timeConstant);
		this.setInitVelocityX(veX);
		this.setInitVelocityY(veY);
		
		this.initVelocityX+=this.accelerationX*timeConstant;
		this.initVelocityY+=this.accelerationY*timeConstant;
		
		float ve=(float) Math.sqrt(((this.initVelocityX*this.initVelocityX)+(this.initVelocityY*this.initVelocityY)));
		this.setVelocity(ve);
		//car.myPanel.setCarXY(Math.round(car.positionX), Math.round(car.positionY));
		
		
		
		
	}

	private boolean right_blocked() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean front_blocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getPositionX() {
		return positionX;
	}




	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}




	public float getPositionY() {
		return positionY;
	}




	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}




	public float getAccelerationX() {
		return accelerationX;
	}




	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}




	public float getAccelerationY() {
		return accelerationY;
	}




	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}




	public float getInitVelocityY() {
		return initVelocityY;
	}




	public void setInitVelocityY(float initVelocityY) {
		this.initVelocityY = initVelocityY;
	}




	public float getInitVelocityX() {
		return initVelocityX;
	}




	public void setInitVelocityX(float initVelocityX) {
		this.initVelocityX = initVelocityX;
	}




	public int getDriveMode() {
		return driveMode;
	}




	public void setDriveMode(int driveMode) {
		this.driveMode = driveMode;
	}




	public float getAcceleration() {
		return acceleration;
	}




	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}




	public float getVelocity() {
		return velocity;
	}




	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}




	public float getDirection() {
		return direction;
	}


	private boolean find(MyNode n, ArrayList<AdjacentNode> ajn) {
		for(int i=0;i<ajn.size();i++){
			if(n.getNodeId()==ajn.get(i).getNode().getNodeId()){
				this.direction=(float) ajn.get(i).getDirection();
				System.out.println("setting driec "+direction);
				return true;
			}
			
			
		}
		
		
		return false;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}
	private int getDistance(SurfacePoint startPoint,SurfacePoint endPoint) {
		double d=Math.hypot(startPoint.getX()-endPoint.getX(), startPoint.getY()-endPoint.getY());
		return (int)d;
	}
	@Override
	public String toString() {
		String s="initVelocityX: "+initVelocityX+"\ninitVelocityY: "+initVelocityY+"\nacceleration: "+acceleration;
		return s;
	}

	public void updateForntBack() {
		this.backXY.setX((int) (this.positionX+((Math.cos(Math.toRadians((this.direction+180)%360))*this.lengthOfCar/2))));

		this.backXY.setY((int) (this.positionY+((Math.sin(Math.toRadians((this.direction+180)%360))*this.lengthOfCar/2))));


		this.forntXY.setX((int) (this.positionX+((Math.cos(Math.toRadians(this.direction%360))*this.lengthOfCar/2))));

		this.forntXY.setY((int) (this.positionY+((Math.sin(Math.toRadians(this.direction%360))*this.lengthOfCar/2))));
		
	}
}
