import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Stroke;

public class MyLine implements Drawable{
	
	private double x1, y1, x2, y2;
	private Color color;
	private Stroke s = new BasicStroke(SliderListener.borderThickness);
	
	public MyLine(double x1, double y1, double x2, double y2, Color color){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g){
		Line2D l = new Line2D.Double(x1, y1, x2, y2);
		g.setColor(color);
		g.setStroke(s);
		g.draw(l);
	}

	public double getStartX(){
		return Math.min(x1, x2);
	}
	
	public double getStartY(){
		return Math.min(y1, y2);
	}
	
	//Returns the width of the surrounding rectangle
	public double getWidth(){
		return Math.abs(x1 - x2);
	}
	
	//Returns the height of the surrounding rectangle
	public double getHeight(){
		return Math.abs(y1 - y2);
	}
	
	public void setStartX(double setX) {
		/*
		if (x1 < x2)
			x1 = setX;
		else
			x2 = setX;*/
		x1 = setX;
	}
	
	public void setStartY(double setY) {
		/*
		if (y1 < y2)
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
		color = DrawPanel.currentBorderColor;
	}
	
	public void setFillColor(){
		
	}
	
	public void setBorderThickness() {
		s = new BasicStroke(SliderListener.borderThickness);
	}

	@Override
	public void setText(String newText) {
		// TODO Auto-generated method stub
		
	}
}
