import javax.swing.*;
import java.awt.*;

public class MovingWindow {

	public static final int RIGHT_EDGE_OF_SCREEN = 2560;
	public static final int LEFT_EDGE_OF_SCREEN = 0;
	public static final int TOP_EDGE_OF_SCREEN = 0;
	public static final int BOTTOM_EDGE_OF_SCREEN = 1440;
	
	protected JFrame window;
	private int length;
	private int width;
	private int speed;
	
	public MovingWindow(int length, int width, int speed) {
		
		JFrame window = new JFrame("NEWM");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		window.setSize(length, width);
		JLabel text = new JLabel("DONT TOUCH", SwingConstants.CENTER);
		text.setFont(new Font("Courier", Font.BOLD, 60));
		window.getContentPane().add(text, BorderLayout.CENTER);
		this.window = window;
		
		this.length = length;
		this.width = width;
		this.speed = speed;
	}
	
	public void spawnWindow() {
		
	}
	
	public void scrollWindow() {
		
	}
	
	public boolean touchingEdge() {
		return false;
	}
	
	public boolean checkForDead() {
		return false;
	}
	
	public void hideWindow() {
		window.setVisible(false);
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}
