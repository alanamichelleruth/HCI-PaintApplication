import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

public class MyText implements Drawable {
	private double x1, y1, x2, y2;
	private Color borderColor;
	private String text;
	private int fontSize = (int) Math.abs(x1 - x2);
	
	public MyText(double x1, double y1, double x2, double y2, Color borderColor, String text) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.borderColor = borderColor;
		this.text = text;
	}

	@Override
	public void draw(Graphics2D g) {
		double x = getStartX();
		double y = getStartY();
		double width = getWidth();
		double height = getHeight();
		
		Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
		g.setColor(new Color(1f, 0f, 0f, .2f));
		g.setStroke(new BasicStroke(0));
		g.draw(r);

		double fontSizeDouble = Math.abs(x1 - x2) * 2.1 / text.length();
		fontSize = (int) fontSizeDouble;

		g.setColor(borderColor);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.drawString(text, (int) (x + 0.2 * fontSize), (int) y + fontSize);
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
		borderColor = DrawPanel.currentBorderColor;
	}
	
	public void setFillColor() {
	}
	
	public void setBorderThickness() {
	}
	
	public void setText(String newText){
		text = newText;
	}
}
