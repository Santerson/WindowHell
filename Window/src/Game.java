import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
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
		int phase = 0;
		
		//Creating the score counter
				JFrame scoreCounter = new JFrame("Score"); //window
				scoreCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				scoreCounter.setLocationRelativeTo(null);
				scoreCounter.setLocation(-10, 0);
				scoreCounter.setSize(150,100);
				JLabel scoreDisplay = new JLabel("Score: " + score + " | Phase: " + phase, SwingConstants.CENTER); //text
				scoreDisplay.setPreferredSize(new Dimension(300, 300));
				scoreCounter.getContentPane().add(scoreDisplay, BorderLayout.CENTER);
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
							current.spawnWindow(phase);
						}
						if (current.checkForDead()) {
							ded = true;
							break;
						}
						phase = (int)(score / 1000);
					}
					//Updates score and slows game speed
					try {
						if (!ded) {
							score++;
							scoreDisplay.setText("Score: " + score + " | phase: " + phase);
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
				scoreCounter.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 200, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 450));
				scoreDisplay.setText("GAME OVER!!!!!    | Final Score: " + score + " | Final Phase: " + phase);
				Menus.checkNewScore(score, phase);
				return scoreCounter;
	}

}
