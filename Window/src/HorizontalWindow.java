import java.awt.Color;
import java.awt.Dimension;

public class HorizontalWindow extends LineWindow {

	
	public HorizontalWindow() {
		super(0, (int)(Math.random() * MovingWindow.BOTTOM_EDGE_OF_SCREEN));
	}
	
	public void spawnWindow() {
		int height = (int)(Math.random() * 100 + 100);
		int y = (int)(Math.random() * MovingWindow.BOTTOM_EDGE_OF_SCREEN);
		super.setCooldown(100);
		super.setBackground(MovingWindow.FADED_COLORS[Game.gamePhase]);
		super.window.setSize(new Dimension(MovingWindow.RIGHT_EDGE_OF_SCREEN, height));
		super.setHeight(height);
		super.setY(y);
		super.window.setLocation(MovingWindow.LEFT_EDGE_OF_SCREEN, y);
		super.window.setVisible(true);
	}
	
	public boolean checkForDead(int mousex, int mousey) {
		if (mousey >= super.getY() && mousey <= super.getY() + super.getHeight()) {
			return true;
		}
		return false;
	}
	
}
