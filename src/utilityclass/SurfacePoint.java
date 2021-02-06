package utilityclass;

public class SurfacePoint {
	@Override
	public String toString() {
		return "SurfacePoint [x=" + x + ", y=" + y + "]";
	}

	int x,y;
	
	public SurfacePoint(int x,int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
