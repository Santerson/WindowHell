import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {

	public static void main(String[] args) {
		boolean ded = false;
		int score = 0;
		JFrame scoreCounter = new JFrame("Score");
		scoreCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreCounter.setLocationRelativeTo(null);
		scoreCounter.setLocation(-10, 0);
		JLabel scoreDisplay = new JLabel("Score: " + score, SwingConstants.CENTER);
		scoreDisplay.setPreferredSize(new Dimension(300, 300));
		scoreCounter.getContentPane().add(scoreDisplay, BorderLayout.CENTER);
		scoreCounter.setSize(100,100);
		scoreCounter.setVisible(true);
	
		//creating windows
		LeftScrollingWindow leftMovingWindow = new LeftScrollingWindow(10, 500, (int)(Math.random() * 500 + 200));
		RightScrollingWindow rightMovingWindow = new RightScrollingWindow(10, 500, (int)(Math.random() * 500 + 200));
		//spawning windows
		leftMovingWindow.spawnWindow();
		rightMovingWindow.spawnWindow();
		//game time loop
		while (!ded) {
			leftMovingWindow.scrollWindow();
			rightMovingWindow.scrollWindow();
			if (leftMovingWindow.touchingEdge()) {
				leftMovingWindow.spawnWindow();
			}
			if (rightMovingWindow.touchingEdge()) {
				rightMovingWindow.spawnWindow();
			}
			if (rightMovingWindow.checkForDead() || leftMovingWindow.checkForDead()) {
				ded = true;
				break;
			}
			try {
				if (!ded) {
					score++;
					scoreDisplay.setText("Score: " + score);
				}
				Thread.sleep(5);
			} catch(InterruptedException e) {
				
			}
		}
		leftMovingWindow.hideWindow();
		rightMovingWindow.hideWindow();
		scoreCounter.setSize(400, 100);
		scoreDisplay.setText("U SUCK LAMOOOOO GET GUD LUL | Final Score: " + score);
	}

}
