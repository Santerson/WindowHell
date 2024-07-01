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
		spawnWindow();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void spawnWindow(int score) {
		super.setWidth((int)(Math.random() * 500 + 200));
		int randomY = (int)(Math.random() * (MovingWindow.BOTTOM_EDGE_OF_SCREEN - 200));
		this.window.setLocation(MovingWindow.LEFT_EDGE_OF_SCREEN - super.getWidth(), randomY);
		this.window.setVisible(true);
		x = MovingWindow.LEFT_EDGE_OF_SCREEN - super.getWidth();
		y = randomY;
		super.window.setSize(super.getLength(), super.getWidth());
		speed = (int)(Math.random() * 20 + 5);
	}
	
	public void scrollWindow() {
		window.setLocation(new Point(x + speed, y));
		x = x + speed;
	}
	
	public boolean touchingEdge() {
		if (x >= RIGHT_EDGE_OF_SCREEN + 10) return true;
		return false;
	}
	
	public boolean checkForDead() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point mouse = a.getLocation(); //getting mouse position
		int mousex = (int) mouse.getX();
		int mousey = (int) mouse.getY();
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
