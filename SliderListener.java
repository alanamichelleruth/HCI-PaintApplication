import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class SliderListener implements ChangeListener {
	public static int borderThickness = 15;
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			borderThickness = (int)source.getValue();
		}
	}
}
