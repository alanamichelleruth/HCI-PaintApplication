import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	private int squiggleNum = -1;
	public Graphics2D g2d;
	private String lastButton;
	public ArrayList<MySquiggle> squiggles = new ArrayList<MySquiggle>();
	private int x1, y1, x2, y2;
	public static ArrayList<Drawable> shapesList = new ArrayList<Drawable>();
	public static int i = 0;
	public static Color currentBorderColor = Color.BLACK, currentFillColor;
	
	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		repaint();
		
		g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2));
		for(Drawable s : shapesList)
			s.draw(g2d);
	}
	
	public void makeShapeWithDelete(int x1, int y1, int x2, int y2, String button, boolean fill){
		makeShape(x1, y1, x2, y2, button, fill);
		if (shapesList.size() >= 2){
			repaint();
			shapesList.remove(shapesList.size() - 2);
		}
	}
	
	public void makeShape(int x1, int y1, int x2, int y2, String button, boolean fill){
		lastButton = button;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

		repaint();
		switch(lastButton){
			case "Line": addLine();
				break;
			case "Rectangle": addRectangle(fill);
				break;
			case "Ellipse": addEllipse(fill);
				break;
			case "Square": addSquare(fill);
				break;
			case "Circle": addCircle(fill);
				break;
			case "Squiggle": addSquiggleLines(); addSquiggle();
				break;
			default:
				break;
		}
	}
	
	public void clickAction(String button){
		lastButton = button;
		repaint();
		switch(lastButton){
		case "Delete": 
			for(int i = shapesList.size() -  1; i >= 0; i--)
				if (shapesList.get(i) == InputHandler.currentShape){
					shapesList.remove(i);
					break;
				}
			break;
		case "To Front": moveToFront();
			break;
		case "To Back": moveToBack();
			break;
		case "Change Text": editText();
			break;
		default:
			break;
		}
	}
	
	public void dragAction(int x1, int y1, int x2, int y2, String button, boolean fill){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		repaint();
		if (button == "Move")
			moveShape();
		else if (button == "Resize")
			resizeShape();
	}
	public void addLine(){
		MyLine line = new MyLine(x1, y1, x2, y2, currentBorderColor);
		shapesList.add(line);
	}
	
	public void addLine(int x1, int y1, int x2, int y2){
		MyLine line = new MyLine(x1, y1, x2, y2, currentBorderColor);
		shapesList.add(line);
	}
	
	public void addRectangle(boolean fill){
		MyRectangle rect = new MyRectangle(x1, y1, x2, y2, currentBorderColor, currentFillColor, fill);
		shapesList.add(rect);
	}
	
	public void addEllipse(boolean fill){
		MyEllipse ell = new MyEllipse(x1, y1, x2, y2, currentBorderColor, currentFillColor, fill);
		shapesList.add(ell);
	}
	
	public void addSquare(boolean fill){
		MySquare square = new MySquare(x1, y1, x2, y2, currentBorderColor, currentFillColor, fill);
		shapesList.add(square);
	}
	
	public void addCircle(boolean fill){
		MyCircle circle = new MyCircle(x1, y1, x2, y2, currentBorderColor, currentFillColor, fill);
		shapesList.add(circle);
	}
	
	public void addSquiggle(){
		shapesList.add(squiggles.get(squiggleNum));
	}
	
	public void addSquiggleLines(){
		squiggles.get(squiggleNum).addLine(x1, y1, x2, y2, currentBorderColor);
	}
	
	public void addSquiggleToArray(){
		squiggleNum++;
		squiggles.add(new MySquiggle(this, currentBorderColor));	
	}
	
	public void addPic(){
		MyImage imageToDraw = new MyImage(30, 30, 250, 250, InputHandler.image, this);
		shapesList.add(imageToDraw);
	}
	
	public void addText(){
		MyText t = new MyText(30, 30, 500, 200, currentBorderColor, InputHandler.userText);
		shapesList.add(t);
	}
	
	public void editText(){
		if (InputHandler.shapeButton == "Text"){
			String newUserText = JOptionPane.showInputDialog(this, "Type your new text here!");
			InputHandler.currentShape.setText(newUserText);
		}
		return;
	}
	
	//if x1 and y1 are within the area of a certain shape, remove that shape from the list
	public void deleteShape(){
		for (int i = shapesList.size() - 1; i >= 0; i--){
			if (shapesList.get(i) == InputHandler.currentShape){
				shapesList.remove(i);
				break;
			}
		}
	}

	public void moveShape(){
		if (InputHandler.currentShape != null){
			InputHandler.currentShape.setStartX(x2 - InputHandler.x1Difference);
			InputHandler.currentShape.setStartY(y2 - InputHandler.y1Difference);
			InputHandler.currentShape.setWidth(x2 + InputHandler.x2Difference);
			InputHandler.currentShape.setHeight(y2 + InputHandler.y2Difference);
		}
	}
	
	public void resizeShape(){
		//find the most recent shape which is clicked and adjust x2 and y2
		//TODO: It gets weird when the mouse goes to a point before startX and startY	
		if (InputHandler.currentShape != null){
			if (InputHandler.beginning){
				InputHandler.currentShape.setStartX(InputHandler.currentShape.getStartX());
				InputHandler.currentShape.setStartY(InputHandler.currentShape.getStartY());
				//InputHandler.currentShape.setWidth(InputHandler.currentShape.getWidth());			
				//InputHandler.currentShape.setHeight(InputHandler.currentShape.getHeight());
				InputHandler.beginning = false;
			}	
			
			InputHandler.currentShape.setWidth(x2 + InputHandler.x2Difference);
			InputHandler.currentShape.setHeight(y2 + InputHandler.y2Difference);
		}
	}
	
	public void moveToFront(){
		Drawable shape;
		for(int i = shapesList.size() -  1; i >= 0; i--){
			shape = shapesList.get(i);
			if (shape == InputHandler.currentShape){
				shapesList.remove(i);
				shapesList.add(InputHandler.currentShape);
			}
		}
	}
	
	public void moveToBack(){
		Drawable shape;
		for(int i = shapesList.size() -  1; i >= 0; i--){
			shape = shapesList.get(i);
			if (shape == InputHandler.currentShape){
				shapesList.remove(i);
				shapesList.add(0, InputHandler.currentShape);
			}
		}
	}
	
	public void changeColor(String button){
		repaint();
		if (button == "Select Border Color")
			InputHandler.currentShape.setBorderColor();
		else if (button == "    Select Fill Color    ")
			InputHandler.currentShape.setFillColor();
	}
	
	public void changeBorderThickness(){
		InputHandler.currentShape.setBorderThickness();
	}
	
	public void clear(){
		for(int i = shapesList.size() -  1; i >= 0; i--)
			shapesList.remove(i);
	}
}
