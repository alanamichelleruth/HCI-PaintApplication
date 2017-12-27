import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;;

public class ButtonPanel extends JPanel{

	private static JButton b1a, b1b, b2a, b2b, b3a, b3b, b4, b5, b6, b7, b8, b9, b9a, b11, b12a, b12b, b13, b14;
	JButton red, orange, yellow, green, cyan, blue, magenta, pink, black, gray, white, blank, customColor;
	JLabel shapeChooser, nullString, orString;
	ImageIcon icon, thick;
	Image temp, tempSized;
	public Box box0, box1, box2, box3, box4, box5, box6, box7, box8, box9, boxColor1, boxColor2, boxCustomColor;
	
	static final int BORDER_MIN = 0;
	static final int BORDER_MAX = 30;
	static final int BORDER_INIT = 15;
	
	private JFrame frame;
	DrawPanel panel;
	private InputHandler inp;

	
	public ButtonPanel(DrawPanel panel, JFrame frame){
		super();

		this.panel = panel;
		this.frame = frame;
		
		JSlider lineThickness = new JSlider(JSlider.HORIZONTAL, BORDER_MIN, BORDER_MAX, BORDER_INIT);
		lineThickness.addChangeListener(new SliderListener());
		lineThickness.setMajorTickSpacing(2);
		lineThickness.setPaintTicks(true);
		 
		inp = new InputHandler(panel, this, frame);
		
		box0 = Box.createVerticalBox();
		box1 = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();
		box4 = Box.createHorizontalBox();
		box5 = Box.createVerticalBox();
		box6 = Box.createHorizontalBox();
		box7 = Box.createHorizontalBox();
		box8 = Box.createHorizontalBox();
		box9 = Box.createHorizontalBox();
		shapeButtons();
		
		box1.add(nullString);
		
		b5 = new JButton("Move");
		box1.add(b5);
		b5.addActionListener(inp);
		
		b6 = new JButton("Resize");
		box1.add(b6);
		b6.addActionListener(inp);
		
		b4 = new JButton("Delete");
		box1.add(b4);
		b4.addActionListener(inp);

		b8 = new JButton("To Front");
		box1.add(b8);
		b8.addActionListener(inp);
		
		b9 = new JButton("To Back");
		box1.add(b9);
		b9.addActionListener(inp);
		
		b9a = new JButton("Clear Screen");
		box1.add(b9a);
		b9a.addActionListener(inp);
		
		box1.add(new JLabel("                                                                                                                                                                   "));
		
		b13 = new JButton("Change Text");
		box1.add(b13);
		b13.addActionListener(inp);
		
		b14 = new JButton("Stop Timer");
		box1.add(b14);
		b14.addActionListener(inp);
		
		b7 = new JButton("Select Border Color");
		b7.addActionListener(inp);
		
		b11 = new JButton("    Select Fill Color    ");
		b11.addActionListener(inp);
		
		box0.add(box5);
		box0.add(box2);
		box0.add(box3);
		box0.add(box4);
		box0.add(new JLabel("\n"));
		
		try{
			thick = new ImageIcon(ImageIO.read(getClass().getResource("/resources/lineThickness.png")));
			temp = thick.getImage();
			tempSized = temp.getScaledInstance(180, 10, java.awt.Image.SCALE_SMOOTH);
			thick = new ImageIcon(tempSized);
			JLabel thickness = new JLabel(thick);
			thickness.setAlignmentX(Component.CENTER_ALIGNMENT);
			box0.add(thickness);
		}catch (Exception ImageIO){
			
		}
		
		box0.add(lineThickness);
		
		box0.add(new JLabel("\n"));
		orString = new JLabel("Or:");
		orString.setAlignmentX(Component.CENTER_ALIGNMENT);
		box0.add(orString);
		
		b12a = new JButton();
		makeImageButton(b12a, "Text", "/resources/textA.png", 35, 35, box6, false);

		b12b = new JButton();
		makeImageButton(b12b, "Pic", "/resources/addPicture.png", 35, 35, box6, false);
		
		box0.add(box6);
		box0.add(new JLabel("\n"));
		box9.add(b13);
		box0.add(box9);
		for (int i = 0; i < 5; i++){
			box0.add(new JLabel("\n"));
		}
		
		box7.add(b11);
		box8.add(b7);
		
		box0.add(box7);
		box0.add(box8);
		box0.add(new JLabel("\n"));
		
		colorButtons();
	}
	
	private void shapeButtons(){
		shapeChooser = new JLabel("Choose Your SHAPE:");
		shapeChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
		nullString = new JLabel("                                                                                                  \n");
		nullString.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		box5.add(nullString);
		box5.add(shapeChooser);
			
		b1a = new JButton();
		makeImageButton(b1a, "Square", "/resources/square.png", 35, 35, box2, false);
		
		b1b = new JButton();
		makeImageButton(b1b, "Rectangle", "/resources/square.png", 55, 35, box2, false);
		
		b2a = new JButton();
		makeImageButton(b2a, "Circle", "/resources/circle.png", 35, 35, box3, false);
		
		b2b = new JButton();
		makeImageButton(b2b, "Ellipse", "/resources/circle.png", 55, 35, box3, false);
		
		b3a = new JButton();
		makeImageButton(b3a, "Line", "/resources/line.png", 35, 35, box4, false);
		
		b3b = new JButton();
		makeImageButton(b3b, "Squiggle", "/resources/squiggle2.png", 55, 35, box4, false);
	}
	
	private void colorButtons(){
		boxColor1 = Box.createHorizontalBox();
		boxColor2 = Box.createHorizontalBox();
		boxCustomColor = Box.createHorizontalBox();
		
		red = new JButton();
		makeImageButton(red, "red", "/resources/red.png", 10, 10, boxColor1, true);
		
		orange = new JButton();
		makeImageButton(orange, "orange", "/resources/orange.png", 10, 10, boxColor1, true);

		yellow = new JButton();
		makeImageButton(yellow, "yellow", "/resources/yellow.png", 10, 10, boxColor1, true);

		green = new JButton();
		makeImageButton(green, "green", "/resources/green.png", 10, 10, boxColor1, true);

		cyan = new JButton();
		makeImageButton(cyan, "cyan", "/resources/cyan.png", 10, 10, boxColor1, true);

		blue = new JButton();
		makeImageButton(blue, "blue", "/resources/blue.png", 10, 10, boxColor1, true);

		magenta = new JButton();
		makeImageButton(magenta, "magenta", "/resources/magenta.png", 10, 10, boxColor2, true);

		pink = new JButton();
		makeImageButton(pink, "pink", "/resources/pink.png", 10, 10, boxColor2, true);

		black = new JButton();
		makeImageButton(black, "black", "/resources/black.png", 10, 10, boxColor2, true);

		gray = new JButton();
		makeImageButton(gray, "gray", "/resources/gray.png", 10, 10, boxColor2, true);

		white = new JButton();
		makeImageButton(white, "white", "/resources/white.png", 10, 10, boxColor2, true);

		blank = new JButton();
		makeImageButton(blank, "blank", "/resources/blank.png", 10, 10, boxColor2, true);
		
		box0.add(boxColor1);
		box0.add(boxColor2);
		box0.add(new JLabel("\n"));
		
		customColor = new JButton("Custom Color");
		customColor.addActionListener(inp);
		boxCustomColor.add(customColor);
		box0.add(boxCustomColor);
	}
	
	private void makeImageButton(JButton button, String command, String imageFile, int height, int width, Box box, boolean borders){
		button.setActionCommand(command);
		try{
			icon = new ImageIcon(ImageIO.read(getClass().getResource(imageFile)));
			temp = icon.getImage();
			tempSized = temp.getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(tempSized);
			button.setIcon(icon);

			if (borders){
				button.setBorder(new EmptyBorder(5, 5, 5, 5));	
				button.setBorderPainted(true);
			}
			
			box.add(button);
			button.addActionListener(inp);
			
		}catch(Exception ImageIO){
			if (borders){
				button.setBorder(new EmptyBorder(20, 20, 20, 20));	
				button.setBorderPainted(true);
			}
			
			box.add(button);
			button.addActionListener(inp);
			return;
		}
	}	

	public void changeShapeButtonColor(String buttonName){
		clearShapeButtonColors();
		clearActionButtonColors();
		switch(buttonName){
		case("Square"): 	b1a.setBackground(Color.LIGHT_GRAY);
			break;
		case("Rectangle"): 	b1b.setBackground(Color.LIGHT_GRAY);
			break;
		case("Circle"): 	b2a.setBackground(Color.LIGHT_GRAY);
			break;
		case("Ellipse"): 	b2b.setBackground(Color.LIGHT_GRAY);
			break;
		case("Line"): 		b3a.setBackground(Color.LIGHT_GRAY);
			break;
		case("Squiggle"): 	b3b.setBackground(Color.LIGHT_GRAY);
			break;
		case("Pic"):        b12b.setBackground(Color.LIGHT_GRAY);
			break;
		case ("Text"):		b12a.setBackground(Color.LIGHT_GRAY);
			break;
		}
	}
		
	public void clearShapeButtonColors(){
		Color clear = new JButton().getBackground();
		b1a.setBackground(clear);
		b1b.setBackground(clear);
		b2a.setBackground(clear);
		b2b.setBackground(clear);
		b3a.setBackground(clear);
		b3b.setBackground(clear);
		b12b.setBackground(clear);
		b12a.setBackground(clear);
	}
	
	public void changeActionButtonColors(String buttonName){
		clearActionButtonColors();
		clearShapeButtonColors();
		switch(buttonName){	
		case("Delete"): 	b4.setBackground(Color.LIGHT_GRAY);
			break;
		case("Move"): 		b5.setBackground(Color.LIGHT_GRAY);
			break;
		case("Resize"): 	b6.setBackground(Color.LIGHT_GRAY);
			break;
		case("To Front"):		b8.setBackground(Color.LIGHT_GRAY);
			break;
		case("To Back"):	b9.setBackground(Color.LIGHT_GRAY);
			break;
		}
	}
	
	public void clearActionButtonColors(){
		Color clear = new JButton().getBackground();
		b4.setBackground(clear);
		b5.setBackground(clear);
		b6.setBackground(clear);
		b8.setBackground(clear);
		b9.setBackground(clear);
	}
	
	public static void changeBorderColorColor(){
		b7.setBackground(DrawPanel.currentBorderColor);	
	}
	
	public static void changeFillColorColor(){
		b11.setBackground(DrawPanel.currentFillColor);	
	}
}