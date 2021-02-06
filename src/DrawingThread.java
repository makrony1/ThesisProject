
public class DrawingThread extends Thread{

	MyPanel myPanel;
	boolean flag=false;
	public DrawingThread(MyPanel p) {
		// TODO Auto-generated constructor stub
		this.myPanel=p;
	}
	
	@Override
	public void run() {
		flag=true;
		while(flag){
			try{
				Thread.sleep(17);
				
				myPanel.updateUI();
			}catch(Exception e){
				
			}
		}
		
	}
}
