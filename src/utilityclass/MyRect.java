package utilityclass;

import java.awt.Color;

public class MyRect {
	int xCordinat;
	int yCordinat;
	int width;
	int height;
	public int getxCordinat() {
		return xCordinat;
	}

	public void setxCordinat(int xCordinat) {
		this.xCordinat = xCordinat;
	}

	public int getyCordinat() {
		return yCordinat;
	}

	public void setyCordinat(int yCordinat) {
		this.yCordinat = yCordinat;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	Color color;
	
	public MyRect(int x,int y,int w,int h) {
		this.xCordinat=x;
		this.yCordinat=y;
		this.width=w;
		this.height=h;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
