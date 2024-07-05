import javax.swing.*;
import java.awt.*;

public class RightScrollingWindow extends MovingWindow {
	
	//Instance data
	private int speed;
	private int x;
	private int y;
	
	public RightScrollingWindow(int scrollSpeed, int length, int width) {
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
		int randomY = (int)(Math.random() * (MovingWindow.BOTTOM_EDGE_OF_SCREEN - 200));
		this.window.setLocation(MovingWindow.LEFT_EDGE_OF_SCREEN - super.getLength(), randomY);
		this.window.setVisible(true);
		this.window.setBackground(MovingWindow.COLORS[Game.gamePhase]);
		x = MovingWindow.LEFT_EDGE_OF_SCREEN - super.getLength();
		y = randomY;
		super.window.setSize(super.getLength(), super.getWidth());
	}
	
	public void scrollWindow() {
		window.setLocation(new Point(x + speed, y));
		x = x + speed;
	}
	
	public boolean touchingEdge() {
		if (x >= RIGHT_EDGE_OF_SCREEN) return true;
		return false;
	}
	
	public boolean checkForDead(int mousex, int mousey) {
		//System.out.println("Mouse at " + mousex + ", " + mousey);

		if (mousex >= x && mousex <= x + super.getLength() && mousey >= y && mousey <= y + super.getWidth()) {
			return true;
		}
		return false;
	}
	
	public void changeSize(int width, int length) {
		super.setLength(length);
		super.setWidth(width);
	}
}
