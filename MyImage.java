import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyImage implements Drawable {

	private double x1, y1, x2, y2;
//	private final double hToWRatio;
	BufferedImage image;
	DrawPanel panel;

	public MyImage(int x1, int y1, int x2, int y2, BufferedImage image, DrawPanel panel) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.image = image;
		this.panel = panel;
		
//		hToWRatio = (double)image.getHeight() / image.getWidth();
	}

	@Override
	public void draw(Graphics2D g) {
		double x = getStartX();
		double y = getStartY();
		double width = getWidth();
		double height = getHeight();
		g.drawImage(image,(int)x, (int)y, (int)width, (int)height, panel);
	}

	public double getStartX() {
		return Math.min(x1, x2);
	}

	public double getStartY() {
		return Math.min(y1, y2);
	}

	public double getWidth() {
		return Math.abs(x1 - x2);
	}

	public double getHeight() {
		return Math.abs(y1 - y2);
	}
	
	public void setStartX(double setX) {
		/*
		if (x1 < x2)
			x1 = setX;
		else
			x2 = setX;
			*/
		x1 = setX;
	}
	
	public void setStartY(double setY) {
		/*if (y1 < y2)
			y1 = setY;
		else
			y2 = setY;
			*/
		y1 = setY;
	}

	public void setWidth(double width) {
		x2 = width;
	}
	
	public void setHeight(double height) {
		y2 = height;
	}
	

	public void setBorderColor() {
	}
	
	public void setFillColor() {
	}
	
	public void setBorderThickness() {
	}

	@Override
	public void setText(String newText) {
		// TODO Auto-generated method stub
		
	}
}