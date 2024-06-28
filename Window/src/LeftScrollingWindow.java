import javax.swing.*;
import java.awt.*;

public class LeftScrollingWindow extends MovingWindow {
	
	//Instance data
	private int speed;
	private int x;
	private int y;
	
	public LeftScrollingWindow(int scrollSpeed, int length, int width) {
		super(length, width, scrollSpeed);
		speed = scrollSpeed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	//
	public void spawnWindow() {
		super.setWidth((int)(Math.random() * 500 + 200));
		int randomY = (int)(Math.random() * MovingWindow.BOTTOM_EDGE_OF_SCREEN);
		this.window.setLocation(MovingWindow.RIGHT_EDGE_OF_SCREEN, randomY);
		this.window.setVisible(true);
		x = MovingWindow.RIGHT_EDGE_OF_SCREEN;
		y = randomY;
		super.window.setSize(super.getLength(), super.getWidth());
		speed = (int)(Math.random() * 20 + 5);
	}
	
	public void scrollWindow() {
		window.setLocation(new Point(x - speed, y));
		x = x - speed;
	}
	
	public boolean touchingEdge() {
		if (x <= LEFT_EDGE_OF_SCREEN - super.getWidth()) return true;
		return false;
	}

	public boolean checkForDead() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point mouse = a.getLocation(); //getting mouse position
		int mousex = (int) mouse.getX();
		int mousey = (int) mouse.getY();
		System.out.println("Mouse at " + mousex + ", " + mousey);

		if (mousex >= x && mousex <= x + super.getLength() && mousey >= y && mousey <= y + super.getWidth()) {
			return true;
		}
		return false;
	}
}
