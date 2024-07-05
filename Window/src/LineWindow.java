import java.awt.*;
import javax.swing.*;

public class LineWindow {

	protected JFrame window;
	private int x = 0;
	private int y;
	private int height;
	private int cooldown = -15000;
	
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
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int y) {
		this.height = y;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(int set) {
		cooldown = set;
	}
	
	public void setBackground(Color color) {
		window.setBackground(color);
	}
	
	public boolean checkForDead(int mousex, int mousey) {
		return false;
	}
}
 