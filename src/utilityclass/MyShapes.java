package utilityclass;

import java.awt.Color;
import java.awt.geom.GeneralPath;

public class MyShapes {
	Color color;
	int width;
	public boolean dashe=false;
	GeneralPath polyline;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public GeneralPath getPolyline() {
		return polyline;
	}
	public void setPolyline(GeneralPath polyline) {
		this.polyline = polyline;
	}
	
}
