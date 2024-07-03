import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;

import java.awt.*;
import java.util.ArrayList;

public class MovingWindow {

	public static final int RIGHT_EDGE_OF_SCREEN = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	public static final int LEFT_EDGE_OF_SCREEN = 0;
	public static final int TOP_EDGE_OF_SCREEN = 0;
	public static final int BOTTOM_EDGE_OF_SCREEN = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public static final Color[] COLORS = new Color[] {new Color(255, 0, 0, 255), new Color(255, 166, 0, 255), new Color(255, 242, 0, 255), new Color(55, 255, 0, 255), new Color(0, 247, 255, 255), new Color(0, 42, 255, 255), new Color(162, 0, 255, 255), new Color(255, 0, 230, 255), new Color(102, 102, 102, 255), new Color(0, 0, 0, 255)};
	public static final Color[] FADED_COLORS = new Color[] {new Color(255, 0, 0, 120), new Color(255, 166, 0, 120), new Color(255, 242, 0, 120), new Color(55, 255, 0, 120), new Color(0, 247, 255, 120), new Color(0, 42, 255, 120), new Color(162, 0, 255, 120), new Color(255, 0, 230, 120), new Color(102, 102, 102, 120), new Color(0, 0, 0, 120)};
	
	protected JFrame window;
	private int length;
	private int width;
	
	public MovingWindow(int length, int width, int speed) {
		
		JFrame window = new JFrame("DONT TOUCH");
		window.setUndecorated(true);
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.getContentPane().setBackground(COLORS[Game.gamePhase]);
		window.setSize(length, width);
		this.window = window;
		
		this.length = length;
		this.width = width;
	}
	
	public static int screenSizeChange() {
		return (int)(Math.random() * 500 + 100);
	}
	
	public void spawnWindow(int score) {
		
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
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void changeSize(int changing, int constant) {
		
	}
	
	public static int calcSpeed(int phase) {
		return (int) ((Math.random() * 10 + (5 + (phase * 8))) * .5);
	}
	
	public void setWindowColor(Color color) {
		window.setBackground(color);
	}
}
