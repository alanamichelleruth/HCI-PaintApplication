import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame{
	
	public Window(){
		//super calls a function inherited from the parent class (JFrame)
		super();

		setTitle("Paint Application");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Make sure the new window appears in the middle of your screen
		setLocationRelativeTo(null);
		//Determines what should happen when the frame is closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		DrawPanel dp = new DrawPanel();
		dp.setBackground(Color.WHITE);
		dp.setBorder(BorderFactory.createLineBorder(Color.BLACK));			
		
		ButtonPanel bp = new ButtonPanel(dp, this);		
		
		//Choses a certain layout type for the elements in this frame
		getContentPane().setLayout(new BorderLayout());

		//Places the DrawPanel in the center of the frame
		getContentPane().add(dp, BorderLayout.CENTER);
		//Places the ButtonPanel in the frame
		getContentPane().add(bp.box1, BorderLayout.NORTH);
		getContentPane().add(bp.box0, BorderLayout.WEST);
		//Set the window to visible! Yup... this is necessary
		setVisible(true);
	}
	
	public void setCursors(){
		//if (InputHandler.lastOption == "ShapeButton")
		    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
	}
}
