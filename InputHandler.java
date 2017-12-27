import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputHandler implements ActionListener, MouseListener, MouseMotionListener{
	
	private DrawPanel panel;
	private JFrame thisFrame;
	private final JFileChooser directory = new JFileChooser();
	public static String shapeButton, actionButton, colorModeButton, colorButton, lastOption, lastColorOption;
	public int x1, y1, x2, y2, i;
	public static double x1Difference, y1Difference, x2Difference, y2Difference, startTime;
	public static Drawable currentShape;
	private ButtonPanel button;
	public static boolean fill = false;
	public static boolean beginning = true, justDragged = false;
	public static BufferedImage image;
	private static Color customColor;
	public static String userText;
	
	public InputHandler(DrawPanel panel, ButtonPanel button, JFrame frame){
		this.panel = panel;
		this.button = button;
		thisFrame = frame;
		
		shapeButton = "Nothing Selected";		
		actionButton = "Nothing Selected";
		colorModeButton = "Select Border Color";
		lastOption = "Nothing Selected";
		colorButton = "Nothing Selected";
		i = 0;
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		//TODO: add code here that will
		//be run when the button is clicked
		
		if (lastOption == "Nothing Selected")
			startTime = System.currentTimeMillis();
		
		if (e.getActionCommand() == "Line" || e.getActionCommand() == "Square" || e.getActionCommand() == "Rectangle" || e.getActionCommand() == "Circle" || e.getActionCommand() == "Ellipse" || e.getActionCommand() == "Squiggle" || e.getActionCommand() == "Pic" || e.getActionCommand() == "Text"){
			shapeButton = e.getActionCommand();
			lastOption = "ShapeButton";
			button.changeShapeButtonColor(shapeButton);
			lastColorOption = "";
			if (e.getActionCommand() == "Text"){
				userText = JOptionPane.showInputDialog(panel, "Type your text here!");
				panel.addText();
				actionButton = "Move";
				lastOption = "ActionButton";
				button.changeActionButtonColors(actionButton);
			}
			else if (e.getActionCommand() == "Pic"){
				directory.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
				int returnVal = directory.showDialog(panel, "Select Image");
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selected = directory.getSelectedFile();	            
					try {
						image = ImageIO.read(selected);
			   			panel.addPic();
			   			shapeButton = e.getActionCommand();
			   			lastOption = "ShapeButton";
			    			
			            } catch (IOException ex) {
			            	ex.printStackTrace();
			            }
				}
				actionButton = "Move";
				lastOption = "ActionButton";
				button.changeActionButtonColors(actionButton);
			}
		}
		else if (e.getActionCommand() == "Move" || e.getActionCommand() == "Resize" || e.getActionCommand() == "Delete" || e.getActionCommand() == "To Front" || e.getActionCommand() == "To Back" || e.getActionCommand() == "Clear Screen" || e.getActionCommand() == "Stop Timer" || e.getActionCommand() == "Change Text"){
			actionButton = e.getActionCommand();
			lastOption = "ActionButton";
			button.changeActionButtonColors(actionButton);
			lastColorOption = "";
			if (actionButton == "Clear Screen"){
				panel.clear();
				actionButton = "Nothing Selected";
				if (shapeButton != "Pic" && shapeButton != "Text"){
					lastOption = "ShapeButton";
					button.changeShapeButtonColor(shapeButton);
				}
			}
			if (actionButton == "Stop Timer"){
				double tttc = (System.currentTimeMillis() - startTime)/1000.0;
				System.out.println("Time to Total Task Completion: " + tttc + " seconds.");
				return;
			}
		}
		else if (e.getActionCommand() == "Select Border Color" || e.getActionCommand() == "    Select Fill Color    "){
			colorModeButton = e.getActionCommand();
			lastColorOption = "ColorModeButton";
		}
		else if (e.getActionCommand() == "red" || e.getActionCommand() == "orange" || e.getActionCommand() == "yellow" || e.getActionCommand() == "green" || e.getActionCommand() == "cyan" || e.getActionCommand() == "blue" || e.getActionCommand() == "magenta" || e.getActionCommand() == "pink" ||e.getActionCommand() == "black" ||e.getActionCommand() == "gray" ||e.getActionCommand() == "white" ||e.getActionCommand() == "blank"){
			colorButton = e.getActionCommand();
			lastColorOption = "ColorButton";
		}
		else if (e.getActionCommand() == "Custom Color"){
			customColor = JColorChooser.showDialog(null, "Choose Custom Color", Color.BLACK);
			
			if (colorModeButton == "    Select Fill Color    "){
				fill = true;
				panel.currentFillColor = customColor;
				colorButton = "Nothing Selected";
				ButtonPanel.changeFillColorColor();
			}
			else if (colorModeButton == "Select Border Color"){
				panel.currentBorderColor = customColor;
				colorButton = "Nothing Selected";
				ButtonPanel.changeBorderColorColor();
			}
		}
		
		setCursors(lastOption);
		
		/*
		System.out.println("Shape: " + shapeButton);
		System.out.println("Action: " + actionButton);
		System.out.println("Color Mode: " + colorModeButton);
		System.out.println("Color: " + colorButton);
		System.out.println("Last Color Option: " + lastColorOption);
		System.out.println("Last Option: " + lastOption + '\n');
		
		
		if (shapeButton == "Line" || shapeButton == "Squiggle")
			colorModeButton = "Select Border Color";
		*/
		
		if (colorButton != "Nothing Selected"){
			if (colorModeButton == "    Select Fill Color    "){
				fill = true;
				panel.currentFillColor = stringToColor(colorButton);
				if (lastColorOption == "ColorButton")
					button.changeFillColorColor();
				if (colorButton == "blank"){
					fill = false;
					panel.currentFillColor = new Color(1f,0f,0f,0f);
				}
			}
			if (colorModeButton == "Select Border Color"){
				panel.currentBorderColor = stringToColor(colorButton);
				if (lastColorOption == "ColorButton")
					button.changeBorderColorColor();
				if (colorButton == "blank")
					panel.currentBorderColor = panel.currentFillColor;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentShape != null){
			if (lastOption == "ActionButton")
				panel.clickAction(actionButton);
			if (lastColorOption != "")
				panel.changeColor(colorModeButton);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		beginning = true;
	
		Drawable shape;
		double shapeX1, shapeY1, shapeX2, shapeY2;
		//find the most recent shape which is clicked and adjust x2 and y2
		for(int i = DrawPanel.shapesList.size() - 1; i >= 0; i--){
			shape = DrawPanel.shapesList.get(i);
			
			shapeX1 = shape.getStartX();
			shapeY1 = shape.getStartY();
			shapeX2 = shapeX1 + shape.getWidth();
			shapeY2 = shapeY1 + shape.getHeight();

			if (x1 >= shapeX1 && y1 >= shapeY1 && x1 <= shapeX2 && y1 <= shapeY2){
				currentShape = shape;
				x1Difference = x1 - shapeX1;
				y1Difference = y1 - shapeY1;
				x2Difference = shapeX2 - x1;
				y2Difference = shapeY2 - y1;
				break;
			}
			else
				currentShape = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (lastOption == "ShapeButton" && shapeButton == "Square" && justDragged){
			DrawPanel.shapesList.remove(DrawPanel.shapesList.size()-1);
			double rectangleLength = Math.max(x2 - x1, y2 - y1);
			MyRectangle rect = new MyRectangle(x1, y1, x1 + rectangleLength, y1 + rectangleLength, DrawPanel.currentBorderColor, DrawPanel.currentFillColor, fill);
			DrawPanel.shapesList.add(rect);
		}
		else if (lastOption == "ShapeButton" && shapeButton == "Circle" && justDragged){
			DrawPanel.shapesList.remove(DrawPanel.shapesList.size()-1);
			double ellipseLength = Math.max(x2 - x1, y2 - y1);
			MyEllipse rect = new MyEllipse(x1, y1, x1 + ellipseLength, y1 + ellipseLength, DrawPanel.currentBorderColor, DrawPanel.currentFillColor, fill);
			DrawPanel.shapesList.add(rect);
		}
		beginning = true;
		i = 0;
		justDragged = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (lastOption == "ShapeButton"){
			if (shapeButton != "Squiggle"){
				x2 = arg0.getX();
				y2 = arg0.getY();
				if (i > 0)
					panel.makeShapeWithDelete(x1, y1, x2, y2, shapeButton, fill);
				else if (i <= 0)
					panel.makeShape(x1, y1, x2, y2, shapeButton, fill);
				i++;
			}
			else{
				x1 = x2;
				y1 = y2;
				x2 = arg0.getX();
				y2 = arg0.getY();
				
				if (beginning){
					panel.addSquiggleToArray();
					x1 = x2;
					y1 = y2;
					beginning = false;
				}
				
				if (i > 0){
					panel.makeShapeWithDelete(x1, y1, x2, y2, shapeButton, fill);
					i++;
				}
				else if (i <= 0){
					panel.makeShape(x1, y1, x2, y2, shapeButton, fill);
					i++;
				}
			}
		}
		
		else if (lastOption == "ActionButton"){
			x2 = arg0.getX();
			y2 = arg0.getY();
			panel.dragAction(x1, y1, x2, y2, actionButton, fill);
		}
		
		justDragged = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	/*(C) Copyright 2003-2005, by Object Refinery Limited.
	 *
	 * Original Author:  David Gilbert (for Object Refinery Limited);
	 * Contributor(s):   -;
	 *
	 * $Id: PaintUtilities.java,v 1.10 2007/11/02 17:50:37 taqua Exp $
	 *
	 * Changes
	 * -------
	 * 13-Nov-2003 : Version 1 (DG);
	 * 04-Oct-2004 : Renamed PaintUtils --> PaintUtilities (DG);
	 * 23-Feb-2005 : Rewrote equal() method with less indenting required (DG);
	 *
	 */
	private Color stringToColor(String value){
		if (value == null)
			return null;
		try{
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			try {
				final Field f = Color.class.getField(value);
				return (Color) f.get(null);
		} catch (Exception ce){
			return null;
		}
		}
	}
	
	private void setCursors(String option){
		if (option == "ShapeButton"){
			if (shapeButton != "Squiggle" && shapeButton != "Pic")
				thisFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else if (shapeButton == "Squiggle"){
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursorImage = toolkit.getImage(getClass().getResource("/resources/pencil.png"));
				try{
					ImageIO.read(getClass().getResource("/resources/pencil.png"));
					Cursor c = toolkit.createCustomCursor(cursorImage , new Point(0, 0), "customCursor");
					thisFrame.setCursor (c);
				}catch(IOException e){
					System.out.println("Cursor not valid.");
				}
			}
			else if (shapeButton == "Pic")
				thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if(option == "ActionButton"){
			switch(actionButton){
			case("Move"): 	thisFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
				break;
			case("Resize"):			thisFrame.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
				break;
			case("To Front"):
			case("To Back"):
			case("Change Text"):
			case("Stop Timer"):
			case ("Nothing Selected"):		thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				break;
			case ("Delete"):    Toolkit toolkit = Toolkit.getDefaultToolkit();
								Image cursorImage = toolkit.getImage(getClass().getResource("/resources/garbage.png"));
								try{
									ImageIO.read(getClass().getResource("/resources/garbage.png"));
									Cursor c = toolkit.createCustomCursor(cursorImage , new Point(0, 0), "customCursor");
									thisFrame.setCursor(c);
								}catch(IOException e){
									System.out.println("Cursor not valid.");
								}

			}
		}
	}
}