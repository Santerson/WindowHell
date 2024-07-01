import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.util.ArrayList;

public class Game{
	
	public static JFrame playGame() {
		ArrayList<MovingWindow> windows = new ArrayList<MovingWindow>(); //arraylist to store all windows on screen
		boolean ded = false; //checks whether the game is lost
		int score = 0; //score
		
		//Creating the score counter
				JFrame scoreCounter = new JFrame("Score"); //window
				scoreCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				scoreCounter.setLocationRelativeTo(null);
				scoreCounter.setLocation(-10, 0);
				JLabel scoreDisplay = new JLabel("Score: " + score, SwingConstants.CENTER); //text
				scoreDisplay.setPreferredSize(new Dimension(300, 300));
				scoreCounter.getContentPane().add(scoreDisplay, BorderLayout.CENTER);
				scoreCounter.setSize(100,100);
				scoreCounter.setVisible(true);
				
				//creating windows
				windows.add(new LeftScrollingWindow(10, 300, MovingWindow.screenSizeChange()));
				windows.add(new RightScrollingWindow(10, 300, MovingWindow.screenSizeChange()));
				windows.add(new DownScrollingWindow(10, MovingWindow.screenSizeChange(), 200));
				windows.add(new UpScrollingWindow(10, MovingWindow.screenSizeChange(), 200));
				windows.add(new LeftScrollingWindow(10, 300, MovingWindow.screenSizeChange()));
				windows.add(new RightScrollingWindow(10, 300, MovingWindow.screenSizeChange()));
				windows.add(new DownScrollingWindow(10, MovingWindow.screenSizeChange(), 200));
				windows.add(new UpScrollingWindow(10, MovingWindow.screenSizeChange(), 200));
				
				
				//game time loop
				while (!ded) {
					//Every window moves its speed amount of pixels, then checks if it is touching an edge, and then checks if it is touching the mouse
					for (MovingWindow current : windows) {
						current.scrollWindow();
						if (current.touchingEdge()) {
							current.changeSize(MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange());
							current.spawnWindow();
						}
						if (current.checkForDead()) {
							ded = true;
							break;
						}
					}
					//Updates score and slows game speed
					try {
						if (!ded) {
							score++;
							scoreDisplay.setText("Score: " + score);
						}
						Thread.sleep(5);
					} catch(InterruptedException e) {
						
					}
				}
				//hides all windows apon losing the game
				for (MovingWindow current : windows) {
					current.hideWindow();
				}
				//Final score counter update
				scoreCounter.setSize(400, 100);
				scoreDisplay.setText("U SUCK LAMOOOOO GET GUD SCRUB | Final Score: " + score);
				return scoreCounter;
	}

}
