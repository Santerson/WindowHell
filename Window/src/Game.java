import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.SwingConstants;
import java.util.ArrayList;

public class Game{
	
	public static int gamePhase;
	
	public static JFrame playGame() {
		ArrayList<MovingWindow> windows = new ArrayList<MovingWindow>(); //arraylist to store all windows on screen
		boolean ded = false; //checks whether the game is lost
		int score = 0; //score
		int phase = 0;
		gamePhase = 0;
				
		//creating windows
		windows.add(new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		
		//Creating score counter (after the windows so it is on top of them)
		JFrame scoreCounter = new JFrame("Score"); //window
		scoreCounter.setUndecorated(true);
		scoreCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreCounter.setLocationRelativeTo(null);
		scoreCounter.setLocation(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 250, 0);
		scoreCounter.setSize(500,100);
		JLabel scoreDisplay = new JLabel("Score: " + score + " | Phase: " + phase, SwingConstants.CENTER); //text
		scoreDisplay.setFont(new Font("Courier", Font.BOLD, 35));
		scoreCounter.setAlwaysOnTop(true);
		scoreCounter.setVisible(true);
		scoreCounter.getContentPane().add(scoreDisplay, BorderLayout.CENTER);
		
		
		JFrame background = new JFrame("Background");
		background.setUndecorated(true);
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background.setLocation(new Point(0, 0));
		background.setSize(new Dimension(MovingWindow.RIGHT_EDGE_OF_SCREEN, MovingWindow.BOTTOM_EDGE_OF_SCREEN));
		background.setBackground(new Color(50, 255, 184, 50));
		
		//game time loop
		while (!ded) {
			//Every window moves its speed amount of pixels, then checks if it is touching an edge, and then checks if it is touching the mouse
			for (int i = 0; i < windows.size(); i++) {
				MovingWindow current = windows.get(i);
				gamePhase = phase;
				current.scrollWindow();
				if (current.touchingEdge()) {
					if (current instanceof UpScrollingWindow) {
						windows.get(i).hideWindow();
						windows.set(i, new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (current instanceof DownScrollingWindow) {
						windows.get(i).hideWindow();
						windows.set(i, new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (current instanceof RightScrollingWindow) {
						windows.get(i).hideWindow();
						windows.set(i, new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (current instanceof LeftScrollingWindow) {
						windows.get(i).hideWindow();
						windows.set(i, new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
				}
				if (current.checkForDead()) {
					ded = true;
					break;
				}
			}
			phase = (int)(score / 1000);
			if (score % 1000 < 10 && phase != 0) {
				if (!background.isVisible())background.setVisible(true);

			}
			else if (score % 1000 < 20){
				if (background.isVisible())background.setVisible(false);
				}
			else if (score % 1000 < 30 && phase != 0) {
				if (!background.isVisible())background.setVisible(true);

			}
			else {
				if (background.isVisible())background.setVisible(false);
				}
			//Updates score and slows game speed
			try {
				if (!ded) {
					score++;
					scoreDisplay.setText("Score: " + score + " | phase: " + phase);
					scoreCounter.toFront();
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
		scoreCounter.setSize(800, 200);
		scoreDisplay.setFont(new Font("Courier", Font.BOLD, 30));
		scoreCounter.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 400, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 600));
		scoreDisplay.setText("GAME OVER!!!!!    | Final Score: " + score + " | Final Phase: " + phase);
		Menus.checkNewScore(score, phase);
		return scoreCounter;
	}
}
