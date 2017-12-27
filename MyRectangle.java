import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class MyRectangle implements Drawable {

	private double x1, y1, x2, y2;
	private Color borderColor, fillColor;
	private boolean fill;
	private Stroke s = new BasicStroke(SliderListener.borderThickness);

	public MyRectangle(double x1, double y1, double x2, double y2, Color borderColor, Color fillColor, boolean fill) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
		this.fill = fill;
	}

	@Override
	public void draw(Graphics2D g) {
		double x = getStartX();
		double y = getStartY();
		double width = getWidth();
		double height = getHeight();
		Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
		g.setColor(borderColor);
		g.setStroke(s);
		g.draw(r);
		if (fill){
			g.setColor(fillColor);
			g.fill(r);
		}
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
			x2 = setX;*/
		x1 = setX;
	}
	
	public void setStartY(double setY) {
		/*
		if (y1 < y2)
			y1 = setY;
		else
			y2 = setY;*/
		y1 = setY;
	}

	public void setWidth(double width) {
		x2 = width;
	}
	
	public void setHeight(double height) {
		y2 = height;
	}
	

	public void setBorderColor() {
		borderColor = DrawPanel.currentBorderColor;
	}
	
	public void setFillColor() {
		fill = true;
		fillColor = DrawPanel.currentFillColor;
	}
	
	public void setBorderThickness() {
		s = new BasicStroke(SliderListener.borderThickness);
	}

	@Override
	public void setText(String newText) {
		// TODO Auto-generated method stub
		
	}
}