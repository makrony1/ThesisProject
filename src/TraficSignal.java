import java.util.ArrayList;

public class TraficSignal extends Thread{
	public ArrayList<CrossPoint> allTraficPoint=new ArrayList<>();
	public TraficSignal() {
		CrossPoint a=new CrossPoint();
		RoadSignal a1=new RoadSignal();
		RoadSignal a2=new RoadSignal();
		RoadSignal a3=new RoadSignal();
		a1.x1=650;
		a1.y1=300;
		
		a1.x2=650;
		a1.y2=255;
		
		a1.x3=700;
		a1.y3=255;
		
		a1.x4=700;
		a1.y4=300;
		
		
		a2.x1=745;
		a2.y1=210;
		
		a2.x2=790;
		a2.y2=210;
		
		a2.x3=790;
		a2.y3=260;
		
		a2.x4=745;
		a2.y4=260;
		
		
		a3.x1=745;
		a3.y1=395;
		
		a3.x2=700;
		a3.y2=395;
		
		a3.x3=700;
		a3.y3=345;
		
		a3.x4=745;
		a3.y4=345;
		a1.updateAll();
		a2.updateAll();
		a3.updateAll();
		a.allSignal.add(a1);
		a.allSignal.add(a2);
		a.allSignal.add(a3);
		a.allSignal.get(0).rgb=0;
		a.allSignal.get(0).startime=System.currentTimeMillis();
		this.allTraficPoint.add(a);
		//this.start();
		
	}
	
	@Override
	public void run() {
		while(true){
		for(int i=0;i<allTraficPoint.size();i++){
			ArrayList<RoadSignal> xp=allTraficPoint.get(i).allSignal;
			
			for(int j=0;j<xp.size();j++){
				RoadSignal sig=xp.get(j);
				if(sig.rgb!=2){
					if(sig.rgb==1){
						//yellow
						long l=System.currentTimeMillis();
						if(l-sig.startime>2000){
							sig.rgb=(sig.rgb+1)%3;
							sig.startime=System.currentTimeMillis();
							RoadSignal sig2=xp.get((j+1)%xp.size());
							sig2.rgb=0;
							sig2.startime=System.currentTimeMillis();
						}
					}else{
						//green
						long l=System.currentTimeMillis();
						if(l-sig.startime>5000){
							sig.rgb=(sig.rgb+1)%3;
							sig.startime=System.currentTimeMillis();
						}
					}
				}
				
			}
		}
		//System.out.println("traffik workinghjgjjjjjjjjjjjjj");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
