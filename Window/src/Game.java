import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.SwingConstants;
import java.util.ArrayList;

public class Game{
	
	public static int gamePhase;
	
	public static JFrame playGame(){
		return playGame(0);
	}
	
	public static JFrame playGame(int startingPhase) {
		ArrayList<MovingWindow> windows = new ArrayList<MovingWindow>(); //arraylist to store all windows on screen
		ArrayList<LineWindow> lineWindows = new ArrayList<LineWindow>();
		boolean ded = false; //checks whether the game is lost
		int score = startingPhase * 1000; //score
		int phase = startingPhase;
		gamePhase = startingPhase;
		
		//creating windows
		windows.add(new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		windows.add(new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
		
		//creating line windows
		lineWindows.add(new HorizontalWindow());
		
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
			//getting mouse cursor location
			PointerInfo a = MouseInfo.getPointerInfo();
			Point mouse = a.getLocation(); 
			int mousex = (int) mouse.getX();
			int mousey = (int) mouse.getY();
			
			//Every window moves its speed amount of pixels, then checks if it is touching an edge, and then checks if it is touching the mouse
			for (int i = 0; i < windows.size(); i++) {
				MovingWindow current = windows.get(i);
				gamePhase = phase;
				current.scrollWindow();
				if (current.touchingEdge()) {
					int randomWindow = (int)(Math.random() * 4);
					if (randomWindow <= 0) {
						windows.get(i).hideWindow();
						windows.set(i, new UpScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (randomWindow <= 1) {
						windows.get(i).hideWindow();
						windows.set(i, new DownScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (randomWindow <= 2) {
						windows.get(i).hideWindow();
						windows.set(i, new RightScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
					else if (randomWindow <= 3) {
						windows.get(i).hideWindow();
						windows.set(i, new LeftScrollingWindow(MovingWindow.calcSpeed(phase), MovingWindow.screenSizeChange(), MovingWindow.screenSizeChange()));
					}
				}
				if (current.checkForDead(mousex, mousey)) {
					ded = true;
					break;
				}
			}
			
			if (phase >= 2) {
				for (int i = 0; i < lineWindows.size(); i++) {
					LineWindow current = lineWindows.get(i);
					int cooldown = current.getCooldown();
					if (cooldown <= -700) {
						current.spawnWindow();
						System.out.println("spawned");
					}
					else if (cooldown <= -500) {
						current.window.setVisible(false);
					}
					else if (cooldown <= 0) {
						if (current.window.getBackground() != MovingWindow.COLORS[phase]) current.window.setBackground(MovingWindow.COLORS[phase]);
					}
					current.setCooldown(current.getCooldown() - 1);
					System.out.println(cooldown);
					
					if (current.checkForDead(mousex, mousey) && current.getCooldown() > -500 && current.getCooldown() <= 0) {
						ded = true;
						break;
					}
				}
			}
			//Determining phase and score calculations
			phase = (int)(score / 1000);
			
			//Does the background flashing on phase increase
			if (score % 1000 < 10 && phase != startingPhase) {
				if (!background.isVisible())background.setVisible(true);

			}
			else if (score % 1000 < 20){
				if (background.isVisible())background.setVisible(false);
				}
			else if (score % 1000 < 30 && phase != startingPhase) {
				if (!background.isVisible())background.setVisible(true);

			}
			else {
				if (background.isVisible())background.setVisible(false);
				}
			
			//Updates score and slows game speed
			try {
				if (!ded) {
					score++;
					if (startingPhase == 0) scoreDisplay.setText("Score: " + score + " | phase: " + phase);
					else scoreDisplay.setText("<html>-Not submitted-<br> Score: " + score + " | phase: " + phase + "<html>");
					scoreCounter.toFront();
				}
				Thread.sleep(5);
			} catch(InterruptedException e) {}
		}
		//hides all windows apon losing the game
		for (MovingWindow current : windows) {
			current.hideWindow();
		}
		for (LineWindow current : lineWindows) {
			current.window.setVisible(false);
		}
		background.setVisible(false);
		
		//Final score counter update
		scoreCounter.setSize(800, 200);
		scoreDisplay.setFont(new Font("Courier", Font.BOLD, 30));
		scoreCounter.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 400, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 600));
		if (startingPhase == 0) scoreDisplay.setText("GAME OVER!!!!!    | Final Score: " + score + " | Final Phase: " + phase);
		else scoreDisplay.setText("<html> -Score not submitted- <br>GAME OVER!!!!!    | Final Score: " + score + " | Final Phase: " + phase +"<html>");
		Menus.checkNewScore(score, phase, startingPhase);
		//Returns for leaderboards and stuff
		return scoreCounter;
	}
}
