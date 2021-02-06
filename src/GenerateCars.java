import java.util.ArrayList;
import java.util.Random;

import graphAndAlgorithm.MyNode;

public class GenerateCars extends Thread{
	private int carid=1;
	ArrayList<Driver> alldriver;
	ArrayList<Car> allCarsintheRoad;
	MyNode n1;
	MyNode n2,n3;
	ArrayList<MyNode> path1,path2;
	ArrayList<MyRoad> allRoad;
	public GenerateCars(ArrayList<Car> allCarsintheRoad,ArrayList<Driver> alldriver ) {
		this.alldriver=alldriver;
		this.allCarsintheRoad=allCarsintheRoad;
	}
	
	public void setNode(MyNode n1,MyNode n2,MyNode n3) {
		this.n1=n1;	this.n2=n2;this.n3=n3;
	}
	
	public void setpath(ArrayList<MyNode> path1,ArrayList<MyNode> path2) {
		this.path1=path1;this.path2=path2;
	}
	
	public void setRoad(ArrayList<MyRoad> allRoad) {
		this.allRoad=allRoad;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void run() {
		while(true){
			Random rand = new Random();
			int posRandInt = rand.nextInt( Integer.MAX_VALUE );
			posRandInt=posRandInt%10;
			posRandInt++;
			if(posRandInt<3)
				posRandInt=5;
			
			Car c=new Car();
			c.maxSpeed=posRandInt;
			c.id=carid++;
			Driver dr= new Driver(c);
			
			//dr.setCarPosition();
			int posRandInt2 = rand.nextInt( Integer.MAX_VALUE );
			if(posRandInt2%2==0){
				dr.currentLan=1;
				
				dr.setStartingNode(n1);
				int posRandInt3 = rand.nextInt( Integer.MAX_VALUE );
				if(posRandInt3%2==0){
					dr.setPath(path2);
					dr.setDestinationNode(n3);
				}else{
					dr.setPath(path1);
					dr.setDestinationNode(n2);
				}
				
				dr.setDriveMode(1);
				dr.setAllRoads(allRoad);
				dr.setCurrentRoad(allRoad.get(0));
				dr.setCarPosition();
				
			}else{
				dr.currentLan=2;
				
				dr.setStartingNode(n1);
				int posRandInt3 = rand.nextInt( Integer.MAX_VALUE );
				if(posRandInt3%2==0){
					dr.setPath(path1);
					dr.setDestinationNode(n2);
				}else{
					dr.setPath(path2);
					dr.setDestinationNode(n3);
				}
				
				dr.setDriveMode(1);
				dr.setAllRoads(allRoad);
				dr.setCurrentRoad(allRoad.get(0));
				dr.setCarPosition();
			}
			dr.allTraficPoint=this.allTraficPoint;
			allCarsintheRoad.add(c);
			alldriver.add(dr);
			
			


			
			
			
			
			
			
			
			
			
			try {
				Thread.sleep(5500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	ArrayList<CrossPoint> allTraficPoint;
	public void setTraficPoint(ArrayList<CrossPoint> allTraficPoint) {
		this. allTraficPoint= allTraficPoint;
		
	}
}
