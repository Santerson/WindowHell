import java.awt.*;
import javax.swing.*;

public class LineWindow {

	protected JFrame window;
	private int x;
	private int y;
	private int cooldown;
	
	public LineWindow(int x, int y) {
		JFrame window = new JFrame();
		Menus.setUpJFrame(window, new Dimension(MovingWindow.RIGHT_EDGE_OF_SCREEN, 100), new Point(x, y));
		this.window = window;
	}
	
	public void spawnWindow() {
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int cooldown() {
		return cooldown;
	}
	
	public void setCooldown(int set) {
		cooldown = set;
	}
}
 