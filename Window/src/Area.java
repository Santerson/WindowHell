import java.awt.*;
import javax.swing.*;

public class Area {
	
	private static void createWindow(int x, int y) {
		
		//create the window
		JFrame frame = new JFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the text on the window
		JLabel textLabel = new JLabel("Ur mom", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(300, 300));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		
		//Displaying the window
		frame.setLocationRelativeTo(null);
		frame.setLocation(new Point(100, 1000));
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		int length = 0;
		int width = 0;
		while (true) {
			createWindow(length, width);
			length++;
			width++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
		}
	}
	
}
