import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class OLDScrollingWindow {
	
	//Constants
	public static final int edgeOfScreen = 2560;
	
	//Main
	public static void main(String[] args) {
		boolean ded = false;
		int score = 0;
		int speed = 10;
		
		//setting up the score counter
		JFrame scoreCounter = new JFrame("Score");
		scoreCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreCounter.setLocationRelativeTo(null);
		scoreCounter.setLocation(-10, 0);
		JLabel scoreDisplay = new JLabel("Score: " + score, SwingConstants.CENTER);
		scoreDisplay.setPreferredSize(new Dimension(300, 300));
		scoreCounter.getContentPane().add(scoreDisplay, BorderLayout.CENTER);
		scoreCounter.setSize(100,100);
		scoreCounter.setVisible(true);
		
		while (!ded) {
			int windowCurrentSize = (int)(Math.random() * 500 + 200);
			
			//creating the window object
			JFrame window = new JFrame("NEWM");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setLocationRelativeTo(null);
			
			//Window details
			window.setSize(500, windowCurrentSize);
			JLabel text = new JLabel("DONT TOUCH", SwingConstants.CENTER);
			text.setFont(new Font("Courier", Font.BOLD, 60));
			window.getContentPane().add(text, BorderLayout.CENTER);
			
			int y = spawnWindow(window);
			int x = edgeOfScreen;
			while (x > -10 && !ded) {
				//scrolls the screen
				x = scrollWindowLeft(window, x, y);
				
				//Checks if mouse is touching the window
				PointerInfo a = MouseInfo.getPointerInfo();
				Point mouse = a.getLocation(); //getting mouse position
				int mousex = (int) mouse.getX();
				int mousey = (int) mouse.getY();
				System.out.println("Mouse at " + mousex + ", " + mousey);
				if (mousex >= x && mousex <= x + 500 && mousey >= y && mousey <= y + windowCurrentSize) {
					ded = true;
				}
				
				//Pauses the scrolling
				try {
				Thread.sleep(speed);
				} catch (InterruptedException e){}
			}
			speed = 1;
			window.setVisible(false);
			if (!ded) {
				score++;
				scoreDisplay.setText("Score: " + score);
			}
			
		}
		scoreCounter.setSize(310, 100);
		scoreDisplay.setText("U SUCK LAMOOOOO GET GUD LUL | Final Score: " + score);
	}
	
	/**
	 * Spawns a window at the right edge of the screen at a random height.
	 * @param window the window to be spawned
	 * @return the Y value of the window
	 */
	private static int spawnWindow(JFrame window) {
		int randomY = (int)(Math.random() * 1000);
		window.setLocation(edgeOfScreen - 150, randomY);
		window.setVisible(true);
		return randomY;
	}
	
	/**
	 * Scrolls the window left 10 pixels
	 * @param window the window
	 * @param currentX the window's current x
	 * @param currentY the window's current y
	 * @return the window's new x
	 */
	public static int scrollWindowLeft(JFrame window, int currentX, int currentY) {
		window.setLocation(new Point(currentX - 10, currentY));
		return currentX - 10;
	}
	
}
