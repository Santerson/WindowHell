
public class HorizontalWindow extends LineWindow {

	
	public HorizontalWindow() {
		super(0, (int)(Math.random() * MovingWindow.BOTTOM_EDGE_OF_SCREEN));
	}
	
	public void spawnWindow() {
		super.setCooldown(1000);
		super.setY(MovingWindow.BOTTOM_EDGE_OF_SCREEN);
		super.window.setBackground(MovingWindow.FADED_COLORS[Game.gamePhase]);
		super.window.setVisible(true);
	}
	
}
