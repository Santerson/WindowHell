import javax.swing.*;
import java.awt.*;

public class UpScrollingWindow extends MovingWindow {
	
	//Instance data
	private int speed;
	private int x;
	private int y;
	
	public UpScrollingWindow(int scrollSpeed, int length, int width) {
		super(length, width, scrollSpeed);
		speed = scrollSpeed;
		spawnWindow(0);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void spawnWindow(int score) {
		int randomX = (int)(Math.random() * (MovingWindow.RIGHT_EDGE_OF_SCREEN - 200));
		this.window.setLocation(randomX, BOTTOM_EDGE_OF_SCREEN);
		this.window.setVisible(true);
		x = randomX;
		y = BOTTOM_EDGE_OF_SCREEN;
		super.window.setSize(super.getLength(), super.getWidth());
	}
	
	public void scrollWindow() {
		window.setLocation(new Point(x, y - speed));
		y = y - speed;
	}
	
	public boolean touchingEdge() {
		if (y <= TOP_EDGE_OF_SCREEN - super.getWidth()) return true;
		return false;
	}

	public boolean checkForDead(int mousex, int mousey) {
		//System.out.println("Mouse at " + mousex + ", " + mousey);

		if (mousex >= x && mousex <= x + super.getLength() && mousey >= y && mousey <= y + super.getWidth()) {
			return true;
		}
		return false;
	}
	
	public void changeSize(int length, int width) {
		super.setLength(length);
		super.setWidth(width);
	}
}
