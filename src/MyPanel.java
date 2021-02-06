import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import graphAndAlgorithm.MyNode;
import utilityclass.MyRect;
import utilityclass.MyShapes;

public class MyPanel extends JPanel implements MouseListener{
	
	ArrayList<MyNode> md=new ArrayList<>();
	ArrayList<Car> allcars=new ArrayList<>();
	
	ArrayList<CrossPoint> allsignal;
	public static ArrayList<Point> points=new ArrayList<>();
	
	public void setAllcars(ArrayList<Car> allcars) {
		this.allcars = allcars;
	}

	public ArrayList<MyNode> getMd() {
		return md;
	}

	public void setMd(ArrayList<MyNode> md) {
		this.md = md;
	}

	ArrayList<MyRect> myRoads;
	ArrayList<MyShapes> allShapes;
	public MyPanel() {
		this.addMouseListener(this);
		myRoads=new ArrayList<>();
		allShapes=new ArrayList<>();
		this.setSize(1200, 650);
		this.setBackground(Color.decode("#fc92a3"));
	}
	
	public void setRoadRectForDrawing(ArrayList<MyRect> Roads,ArrayList<MyShapes> shapes){
		for(int i=0;i<Roads.size();i++)
			myRoads.add(Roads.get(i));
		
		for(int i=0;i<shapes.size();i++)
			allShapes.add(shapes.get(i));
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		/*for(int k=0;k<myRoads.size();k++){
			
		MyRect rect=myRoads.get(k);
		
			
				g.setColor(rect.getColor());
				g.fillRect(rect.getxCordinat(),rect.getyCordinat() , rect.getWidth(),rect.getHeight());
			
			
		}*/
		
		
		int dx=900;
		int dy=150,of=15;
		g2d.drawString("", 0, 0);
		if(info!=null){
			g2d.drawString("Car id: "+info.id, dx, dy);
			g2d.drawString("Car velocity: "+info.velocity, dx, dy=dy+of);
			g2d.drawString("Car Acceleration: "+info.acceleration, dx, dy=dy+of);
			g2d.drawString("Car direction: "+info.direction, dx, dy=dy+of);
			g2d.drawString("Current Road Id: "+info.roadid, dx, dy=dy+of);
			g2d.drawString("Current segment Id: "+info.currentSegment.sId, dx, dy=dy+of);
			//g2d.drawString("current  ID: ", dx, dy=dy+of);
			g2d.drawString("Destination  Id: "+info.destanationId, dx, dy=dy+of);
			g2d.drawString("Source  Id: "+info.sourceID, dx, dy=dy+of);
			
		}
		
		for(int i=0;i<allShapes.size();i++){
			MyShapes ms=allShapes.get(i);
			g2d.setColor(ms.getColor());
			if(ms.dashe){
				float dash1[] = {18.0f};
				BasicStroke dashed =
				        new BasicStroke(2.0f,
				                        BasicStroke.CAP_BUTT,
				                        BasicStroke.JOIN_MITER,
				                        18.0f, dash1, 0.0f);
				g2d.setStroke(dashed);
			}else
				g2d.setStroke(new BasicStroke(ms.getWidth(), BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
			g2d.draw(ms.getPolyline());
			
		}
		
		
		//g.setColor(Color.red);
		//for(int i=0;i<md.size();i++){
			
		//	int x=md.get(i).getPosition().getX();
		//	int y=md.get(i).getPosition().getY();
		//	g.fillOval(x-45,y-45 , 90, 90);
		//}
		//g.fillPolygon(xPoints, yPoints, 4);
		
		
		for(int i=0;i<allsignal.size();i++){
			CrossPoint ars=allsignal.get(i);
			for(int j=0;j<ars.allSignal.size();j++){
				RoadSignal rds=ars.allSignal.get(j);
				if(rds.rgb==0){
					g.setColor(Color.green);
				}else if(rds.rgb==1){
					g.setColor(Color.yellow);
				}else{
					g.setColor(Color.RED);
				}
				g2d.setStroke(new BasicStroke(12,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER));
				g2d.drawLine(rds.lx1, rds.ly1, rds.lx2, rds.ly2);
			}
		}
		
		for(int i=0;i<allcars.size();i++){
			
			Car c=allcars.get(i);
			g.setColor(c.color);
			g2d.setStroke(new BasicStroke(12,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
			
			g2d.drawLine(c.backXY.getX(), c.backXY.getY(), c.forntXY.getX(), c.forntXY.getY());
			
			if(this.c==null){
				if(c.id==1){
					this.c=c;
				}
			}
			//g.fillOval((int)allcars.get(i).getPositionX()-8, (int)allcars.get(i).getPositionY()-8, 16, 16);
			//g.setColor(Color.black);
			//g.drawString(allcars.get(i).id+" X: "+allcars.get(i).positionX+" Y: "+allcars.get(i).positionY, 10, 10+(10*i));
		}	
		
		
		
		
		
		
		
		
		
		
		//g.fillRect(carX, carY, 30, 15);
		
		
		
		
	/*	
		if(this.c!=null){
			
			int Ax, Ay,a1x,a1y,a2x,a2y,a3x,a3y,a4x,a4y;
			int safeDistanceBack=00,forntDistanc=80,coverWidth=16;
			Ax=(int) (c.backXY.getX()+((Math.cos(Math.toRadians((c.direction+180)%360))*safeDistanceBack)));
			Ay=(int) (c.backXY.getY()+((Math.sin(Math.toRadians((c.direction+180)%360))*safeDistanceBack)));
			
			
			a1x=(int) (Ax+((Math.cos(Math.toRadians((c.direction+90)%360))*(coverWidth/2))));
			a1y=(int) (Ay+((Math.sin(Math.toRadians((c.direction+90)%360))*(coverWidth/2))));
			
			a2x=(int) (a1x+((Math.cos(Math.toRadians((c.direction+270)%360))*coverWidth)));
			a2y=(int) (a1y+((Math.sin(Math.toRadians((c.direction+270)%360))*coverWidth)));
			
			a3x=(int) (a2x+((Math.cos(Math.toRadians((c.direction)))*forntDistanc)));
			a3y=(int) (a2y+((Math.sin(Math.toRadians((c.direction)))*forntDistanc)));
			
			a4x=(int) (a1x+((Math.cos(Math.toRadians((c.direction)))*forntDistanc)));
			a4y=(int) (a1y+((Math.sin(Math.toRadians((c.direction)))*forntDistanc)));
	
			
	
			int [] xPoints={a4x,a1x,a2x,a3x};
			int [] yPoints={a4y,a1y,a2y,a3y};
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.GREEN);
			g2d.drawPolyline(xPoints, yPoints, 4);
			
			
			
		}*/
		for(int i=0;i<points.size();i++){
			g2d.setColor(Color.cyan);
			g2d.drawOval(points.get(i).x-2,points.get(i).y-2 , 4, 4);
		}
		
	}
	Car c;

	Car info;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(info!=null){
			info.color=Color.red;
		}
		Point p=e.getPoint();
		//System.out.println(p.getX()+"    "+p.getY());
		for(int i=0;i<allcars.size();i++){
			Car cc=allcars.get(i);
			int x1,y2;
			x1=(int) cc.positionX;
			y2=(int) cc.positionY;
			
			
			
			if(setCar(p,x1,y2)){
				info=cc;
				info.color=Color.WHITE;
				break;
				//System.out.println("found");
			}else{
				info=null;
				//System.out.println("not found found");
			}
		}
		
	}

	private boolean setCar(Point p, int x1, int y1) {
		double d=Math.hypot(p.getX()-x1, p.getY()-y1);
		if(d<15){
			return true;
		}
		//System.out.println("D: "+d);
		return false;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
