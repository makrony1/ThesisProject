import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import graphAndAlgorithm.AdjacentNode;
import graphAndAlgorithm.MyNode;

import org.w3c.dom.Node;
import utilityclass.SurfacePoint;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ArrayList<RoadSegment> allSegment;
		ArrayList<MyRoad> allRoads=new ArrayList<>();
		
		try{
		File fXmlFile = new File("res/Roadinfo.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList roads = doc.getElementsByTagName("road");
		allRoads =new ArrayList<>();
		
		for(int temp=0;temp<roads.getLength();temp++){
			Node road=roads.item(temp);
			Element rElement = (Element) road;
			NodeList segments=rElement.getElementsByTagName("segment");
			MyRoad mr=new MyRoad();
			mr.setrId(Integer.valueOf(rElement.getAttribute("id")));
			mr.setMaxSpeed(Double.valueOf(rElement.getElementsByTagName("maxspeed").item(0).getTextContent()));
			mr.setRoadLength(Double.valueOf(rElement.getElementsByTagName("length").item(0).getTextContent()));
			mr.setNumOfLan(Integer.valueOf(rElement.getElementsByTagName("lan").item(0).getTextContent()));
			mr.setRoadWidth(Integer.valueOf(rElement.getElementsByTagName("width").item(0).getTextContent()));
			ArrayList<RoadSegment>allSegment=new ArrayList<>();
			
			for(int temp2=0;temp2<segments.getLength();temp2++){
				Node segment=segments.item(temp2);
				Element sElement = (Element) segment;
				RoadSegment rs=new RoadSegment();
				rs.setsId(Integer.valueOf(sElement.getAttribute("id")));
				int x,y;
				x=Integer.valueOf(sElement.getElementsByTagName("startX").item(0).getTextContent());
				y=Integer.valueOf(sElement.getElementsByTagName("startY").item(0).getTextContent());
				rs.setStart(new SurfacePoint(x, y));
				x=Integer.valueOf(sElement.getElementsByTagName("endX").item(0).getTextContent());
				y=Integer.valueOf(sElement.getElementsByTagName("endY").item(0).getTextContent());
				rs.setEnd(new SurfacePoint(x, y));
				boolean v=Boolean.valueOf(sElement.getElementsByTagName("isNode").item(0).getTextContent());
				rs.setNode(v);
				rs.setRoadWidth(mr.getRoadWidth());
				allSegment.add(rs);
				//NodeList segments=sElement.getElementsByTagName("segment");
			}
			mr.setSegment(allSegment);
			allRoads.add(mr);
			
		}
		
		
		
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		
		
		MyRoad mr=allRoads.get(0);
		SurfacePoint st=mr.getSegment().get(0).getStart();
		SurfacePoint end=mr.getSegment().get(4).getEnd();
		mr.makeRoadRact(st, end);
		
		
		MyRoad mr1=allRoads.get(1);
		SurfacePoint st1=mr1.getSegment().get(0).getStart();
		SurfacePoint end1=mr1.getSegment().get(1).getEnd();
		mr1.makeRoadRact(st1, end1);
		
		
		MyFrame frame=new MyFrame();
		MyPanel  myPanel =new MyPanel();
		for(int noR=0;noR<allRoads.size();noR++){
			myPanel.setRoadRectForDrawing(allRoads.get(noR).getRoadRect(),allRoads.get(noR).getRoadPath());
			
		}
		
		MyNode n1=new MyNode();
		n1.setName("Dhaka");
		n1.setNodeId(1);
		n1.setPosition(new SurfacePoint(200,300));
		n1.x1=195;
		n1.y1=350;
		n1.x2=195;
		n1.y2=250;
		n1.x3=205;
		n1.y3=250;
		n1.x4=205;
		n1.y4=350;
		
		MyNode n5=new MyNode();
		n5.setName("Dhaka");
		n5.setNodeId(5);
		n5.setPosition(new SurfacePoint(400,300));
		
		MyNode n6=new MyNode();
		n6.setName("Dhaka");
		n6.setNodeId(6);
		n6.setPosition(new SurfacePoint(300,300));
		
		MyNode n2=new MyNode();
		n2.setName("Dhaka");
		n2.setNodeId(2);
		n2.setPosition(new SurfacePoint(745,50));
		
		n2.x1=695;
		n2.y1=45;
		n2.x2=795;
		n2.y2=45;
		n2.x3=795;
		n2.y3=55;
		n2.x4=695;
		n2.y4=55;
		
		MyNode n3=new MyNode();
		n3.setName("Dhaka");
		n3.setNodeId(3);
		n3.setPosition(new SurfacePoint(745,550));
		n3.x1=695;
		n3.y1=555;
		n3.x2=795;
		n3.y2=555;
		n3.x3=795;
		n3.y3=545;
		n3.x4=695;
		n3.y4=545;
		
		MyNode n4=new MyNode();
		n4.setName("Dhaka");
		n4.setNodeId(4);
		n4.setPosition(new SurfacePoint(745,300));
		
		n4.x1=695;
		n4.y1=350;
		n4.x2=695;
		n4.y2=250;
		n4.x3=795;
		n4.y3=250;
		n4.x4=795;
		n4.y4=350;
		
		ArrayList<MyNode> md=new ArrayList<>();
		md.add(n1);
		md.add(n2);
		md.add(n3);
		md.add(n4);
		md.add(n5);
		md.add(n6);
		myPanel.setMd(md);
		
		AdjacentNode aj14=new AdjacentNode(500,500,0,n4,2);
		n1.addAdjacentNode(aj14);
		
		AdjacentNode aj64=new AdjacentNode(500,500,0,n4,2);
		n1.addAdjacentNode(aj64);
		
		AdjacentNode aj54=new AdjacentNode(500,500,0,n4,2);
		n5.addAdjacentNode(aj54);
		
		AdjacentNode aj24=new AdjacentNode(500,500,90,n4,2);
		n2.addAdjacentNode(aj24);
		
		AdjacentNode aj41=new AdjacentNode(500,500,180,n1,1);
		AdjacentNode aj42=new AdjacentNode(500,500,270,n2,2);
		AdjacentNode aj43=new AdjacentNode(500,500,90,n3,2);
		n4.addAdjacentNode(aj41);
		n4.addAdjacentNode(aj42);
		n4.addAdjacentNode(aj43);
		
		AdjacentNode aj34=new AdjacentNode(500,500,0,n4,2);
		n3.addAdjacentNode(aj34);
		
		
		Car car=new Car();
		car.id=1;
		car.maxSpeed=8;
		ArrayList<MyNode> path=new ArrayList<>();
		path.add(n4);
		path.add(n2);
		ArrayList<MyNode> path3=new ArrayList<>();
		path3.add(n4);
		path3.add(n3);
		ArrayList<MyNode> path2=new ArrayList<>();
		path2.add(n4);
		path2.add(n1);
		
		Car car2=new Car();
		car2.maxSpeed=4.5;
		car2.id=2;
		
		Car car6=new Car();
		car6.maxSpeed=4.5;
		car6.id=6;
		
		Car car3=new Car();
		car3.maxSpeed=7.5;
		car3.id=3;
		
		Car car4=new Car();
		car4.maxSpeed=3.5;
		car4.id=4;
		
		Car car5=new Car();
		car5.maxSpeed=6.0;
		car5.id=5;
		
		Driver dr=new Driver(car);
		Driver dr2=new Driver(car2);
		Driver dr3=new Driver(car3);
		Driver dr4=new Driver(car4);
		Driver dr5=new Driver(car5);
		Driver dr6=new Driver(car6);
		dr3.currentLan=2;
		
		dr.currentLan=1;
		dr.setDestinationNode(n3);
		dr.setStartingNode(n1);
		dr.setPath(path3);
		dr.setDriveMode(1);
		dr.setAllRoads(allRoads);
		dr.setCurrentRoad(allRoads.get(0));
		dr.setCarPosition();
		
		dr2.setDestinationNode(n2);
		dr2.setStartingNode(n5);
		dr2.setPath(path);
		dr2.setDriveMode(1);
		dr2.setAllRoads(allRoads);
		dr2.setCurrentRoad(allRoads.get(0));
		dr2.setCarPosition();
		dr2.currentLan=2;
		
		dr3.setDestinationNode(n2);
		dr3.setStartingNode(n5);
		dr3.setPath(path);
		dr3.setDriveMode(1);
		dr3.setAllRoads(allRoads);
		dr3.setCurrentRoad(allRoads.get(0));
		dr3.setCarPosition();
		
		dr4.setDestinationNode(n2);
		dr4.setStartingNode(n6);
		dr4.setPath(path);
		dr4.setDriveMode(1);
		dr4.setAllRoads(allRoads);
		dr4.setCurrentRoad(allRoads.get(0));
		dr4.currentLan=1;
		dr4.setCarPosition();
		
		
		dr5.setDestinationNode(n2);
		dr5.setStartingNode(n6);
		dr5.setPath(path);
		dr5.setDriveMode(1);
		dr5.setAllRoads(allRoads);
		dr5.setCurrentRoad(allRoads.get(0));
		dr5.currentLan=2;
		dr5.setCarPosition();
		
		dr6.currentLan=1;
		dr6.setDestinationNode(n1);
		dr6.setStartingNode(n2);
		dr6.setPath(path2);
		dr6.setDriveMode(1);
		dr6.setAllRoads(allRoads);
		dr6.setCurrentRoad(allRoads.get(1));
		dr6.setCarPosition();
		
		
		ArrayList<Driver> alldriver=new ArrayList<>();
		ArrayList<Car> allCarsintheRoad=new ArrayList<>();/*
		alldriver.add(dr);
		alldriver.add(dr2);
		alldriver.add(dr3);
		alldriver.add(dr4);
		alldriver.add(dr5);
		//alldriver.add(dr6);
		
		
		allCarsintheRoad.add(car);
		allCarsintheRoad.add(car2);
		allCarsintheRoad.add(car3);
		allCarsintheRoad.add(car4);
		allCarsintheRoad.add(car5);
		allCarsintheRoad.add(car6);*/
		TraficSignal ts=new TraficSignal();
		GenerateCars gs=new GenerateCars(allCarsintheRoad, alldriver);
		gs.setpath(path, path3);
		gs.setNode(n1, n2, n3);
		gs.setRoad(allRoads);
		gs.setTraficPoint(ts.allTraficPoint);
		gs.start();
		
		frame.add(myPanel);
		
		frame.setVisible(true);
		DrawingThread drt=new DrawingThread(myPanel);
		drt.start();
		myPanel.setAllcars(allCarsintheRoad);
		
		myPanel.allsignal=ts.allTraficPoint;
		for(int i=0;i<alldriver.size();i++){
			//alldriver.get(i).allTraficPoint=ts.allTraficPoint;
		}
		ts.start();
		while(true){
			for(int nc=0;nc<alldriver.size();nc++){
				Driver c=alldriver.get(nc);
				if(c.flag){
					c.run();
					
				}
				
				else{
					alldriver.remove(c);
					
					allCarsintheRoad.remove(c.car);
				}
			}
			if(alldriver.size()==0)
				break;
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/*MyFrame frame=new MyFrame();
		Road r1=new Road(new SurfacePoint(0,80), new SurfacePoint(1200, 80));
		Road r2=new Road(new SurfacePoint(600,315), new SurfacePoint(1200, 315));
		Road r3=new Road(new SurfacePoint(0,550), new SurfacePoint(1200, 550));
		Road r4=new Road(new SurfacePoint(80,0), new SurfacePoint(80, 750),1);
		Road r5=new Road(new SurfacePoint(600,0), new SurfacePoint(600, 750),1);
		Road r6=new Road(new SurfacePoint(1120,0), new SurfacePoint(1120, 750),1);
		ArrayList<Road> myRoads=new ArrayList<>();
		myRoads.add(r1);
		myRoads.add(r2);
		myRoads.add(r3);
		myRoads.add(r4);
		myRoads.add(r5);
		myRoads.add(r6);
		MyPanel  myPanel =new MyPanel(myRoads);
		
		
		//making node and others things///
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		Node n6=new Node();
		Node n7=new Node();
		Node n8=new Node();
		
		n1.setName("Dhaka");
		n1.setNodeId(1);
		n1.setPosition(new SurfacePoint(80, 80));
		
		n2.setName("Syhlet");
		n2.setNodeId(2);
		n2.setPosition(new SurfacePoint(600, 80));
		
		n3.setName("Faridpur");
		n3.setNodeId(3);
		n3.setPosition(new SurfacePoint(1120, 80));
		
		n4.setName("Commilla");
		n4.setNodeId(4);
		n4.setPosition(new SurfacePoint(600, 315));
		
		n5.setName("Narayangonj");
		n5.setNodeId(5);
		n5.setPosition(new SurfacePoint(1120, 315));
		
		n6.setName("Barisal");
		n6.setNodeId(6);
		n6.setPosition(new SurfacePoint(80, 550));
		
		n7.setName("Kishoureganj");
		n7.setNodeId(7);
		n7.setPosition(new SurfacePoint(600, 550));
		
		n8.setName("Khulna");
		n8.setNodeId(8);
		n8.setPosition(new SurfacePoint(1120, 550));
		
		//Adjacent matrix
		
		AdjacentNode aj12=new AdjacentNode(520, 520, 0, n2);
		AdjacentNode aj16=new AdjacentNode(470, 470, 90, n6);
		n1.addAdjacentNode(aj12);
		n1.addAdjacentNode(aj16);
		
		AdjacentNode aj21=new AdjacentNode(520, 520, 180, n1);
		AdjacentNode aj23=new AdjacentNode(520, 520, 0, n3);
		AdjacentNode aj24=new AdjacentNode(235, 235, 90, n4);
		n2.addAdjacentNode(aj21);
		n2.addAdjacentNode(aj23);
		n2.addAdjacentNode(aj24);
		
		AdjacentNode aj32=new AdjacentNode(520, 520, 180, n2);
		AdjacentNode aj35=new AdjacentNode(235,235, 90, n5);
		n3.addAdjacentNode(aj32);
		n3.addAdjacentNode(aj35);
		
		AdjacentNode aj42=new AdjacentNode(235, 235, 270, n2);
		AdjacentNode aj47=new AdjacentNode(235, 235, 90, n7);
		AdjacentNode aj45=new AdjacentNode(520, 520, 0, n5);
		n4.addAdjacentNode(aj42);
		n4.addAdjacentNode(aj47);
		n4.addAdjacentNode(aj45);

		AdjacentNode aj54=new AdjacentNode(520, 520, 180, n4);
		AdjacentNode aj53=new AdjacentNode(235, 235, 270, n3);
		AdjacentNode aj58=new AdjacentNode(235, 235, 90, n8);
		n5.addAdjacentNode(aj54);
		n5.addAdjacentNode(aj53);
		n5.addAdjacentNode(aj58);
		
		AdjacentNode aj61=new AdjacentNode(470, 470, 270, n1);
		AdjacentNode aj67=new AdjacentNode(520, 520, 0, n7);
		n6.addAdjacentNode(aj61);
		n6.addAdjacentNode(aj67);
		
		AdjacentNode aj74=new AdjacentNode(235, 235, 270, n4);
		AdjacentNode aj76=new AdjacentNode(520, 520, 180, n6);
		AdjacentNode aj78=new AdjacentNode(520, 520, 0, n8);
		n7.addAdjacentNode(aj74);
		n7.addAdjacentNode(aj76);
		n7.addAdjacentNode(aj78);
		
		AdjacentNode aj85=new AdjacentNode(235, 235, 270, n5);
		AdjacentNode aj87=new AdjacentNode(520, 520, 180, n7);
		n8.addAdjacentNode(aj85);
		n8.addAdjacentNode(aj87);
		
		
		
		
		
		Car car=new Car(myPanel);
		car.setVelocity(10F);
		Driver  driver=new Driver(car);
		driver.setStartingNode(n1);
		driver.setDestinationNode(n8);
		ArrayList<Node> path=new ArrayList<>();
		path.add(n2);
		path.add(n4);
		path.add(n5);
		path.add(n8);
		//path.add(n3);
		//path.add(n2);
		//path.add(n1);
		driver.setPath(path);
		
		driver.setStartingPoint(new SurfacePoint(0,180));
		driver.setDetinationPoint(new SurfacePoint(550, 750));
		frame.add(myPanel);
		frame.setVisible(true);
		
		driver.start();
		
		*/
		

	}

}
