import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MySquiggle implements Drawable{

 	private Color color;
 	ArrayList<MyLine> lines;
 	DrawPanel panel;
	private Stroke s = new BasicStroke(SliderListener.borderThickness);
	
	public MySquiggle(DrawPanel panel, Color color){
		lines = new ArrayList<MyLine>();
		this.panel = panel;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < lines.size(); i++){
			g.setColor(color);
			g.setStroke(s);
			lines.get(i).draw(g);
		}
	}

	public void addLine(int x1, int y1, int x2, int y2, Color color){
		lines.add(new MyLine(x1, y1, x2, y2, color));
	}

	@Override
	public double getStartX() {
		// TODO Auto-generated method stub
		double min = 10000;
		for(int i = 0; i < lines.size(); i++){
			if (lines.get(i).getStartX() < min)
				min = lines.get(i).getStartX();
		}
		return min;
	}

	@Override
	public double getStartY() {
		// TODO Auto-generated method stub
		double min = 10000;
		for(int i = 0; i < lines.size(); i++){
			if (lines.get(i).getStartY() < min)
				min = lines.get(i).getStartY();
		}
		return min;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		double max = 0;
		for(int i = 0; i < lines.size(); i++){
			if ((lines.get(i).getWidth() + lines.get(i).getStartX()) > max)
				max = lines.get(i).getWidth() + lines.get(i).getStartX();
		}
		return max;	
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		double max = 0;
		for(int i = 0; i < lines.size(); i++){
			if ((lines.get(i).getHeight() + lines.get(i).getStartY()) > max)
				max = lines.get(i).getHeight() + lines.get(i).getStartY();
		}
		return max;
	}

	@Override
	public void setStartX(double X) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStartY(double Y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(double X) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(double Y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBorderColor() {
		// TODO Auto-generated method stub
		color = DrawPanel.currentBorderColor;
	}

	@Override
	public void setFillColor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBorderThickness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setText(String newText) {
		// TODO Auto-generated method stub
		
	}
}