import java.awt.Graphics2D;

public interface Drawable {

	public void draw(Graphics2D g);
	public double getStartX();
	public double getStartY();
	public double getWidth();
	public double getHeight();
	public void setStartX(double X);
	public void setStartY(double Y);
	public void setWidth(double X);
	public void setHeight(double Y);
	public void setBorderColor();
	public void setFillColor();
	public void setBorderThickness();
	public void setText(String newText);
}