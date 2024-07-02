import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.*;

public class Menus {
	
	private static int score = 0;
	private static int phase = 0;
	public static int highestScore;
	public static int highestPhase;
	final static File highScoreFile = new File("C:\\Users\\santi\\git\\windowHell\\Window\\AllTimeHighScore");
	final static File highPhaseFile = new File("C:\\Users\\santi\\git\\windowHell\\Window\\AllTimeHighPhase");
	
	public static void main(String[] args) {	
		
		JankyInteger startingPhase = new JankyInteger(0);
		PlayBoolean settingsOpen = new PlayBoolean();
		
		fetchHighScores();

		//Play Menu
		JFrame playMenu = new JFrame();
		playMenu.setUndecorated(true);
		playMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playMenu.setPreferredSize(new Dimension(1000, 800));
		playMenu.setLocationRelativeTo(null);
		playMenu.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 500, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 400));
		JButton playButton = new JButton("START");
		playButton.setFont(new Font("Courier", Font.BOLD, 220));
		playButton.setForeground(new Color(71, 12, 1, 255));
		playMenu.add(playButton);
		playMenu.pack();
		playMenu.setAlwaysOnTop(true);
		PlayBoolean play = new PlayBoolean();
		playMenu.setVisible(true);
		
		//Settings Menu
		JFrame settingsMenu = new JFrame("Settings Button");
		settingsMenu.setUndecorated(true);
		settingsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsMenu.setPreferredSize(new Dimension(400, 300));
		settingsMenu.setLocationRelativeTo(null);
		settingsMenu.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 950, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 325));
		JButton settingsButton = new JButton("Settings");
		settingsButton.setFont(new Font("Courier", Font.BOLD, 60));
		settingsMenu.add(settingsButton);
		settingsMenu.pack();
		settingsMenu.setAlwaysOnTop(true);
		settingsMenu.setVisible(true);
		
		//Instructions Menu
		JFrame instructionsMenu = new JFrame("How to Play Button");
		instructionsMenu.setUndecorated(true);
		instructionsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructionsMenu.setPreferredSize(new Dimension(400, 300));
		instructionsMenu.setLocationRelativeTo(null);
		instructionsMenu.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 950, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 + 25));
		JButton instructionsButton = new JButton("How to Play");
		instructionsButton.setFont(new Font("Courier", Font.BOLD, 50));
		instructionsMenu.add(instructionsButton);
		instructionsMenu.pack();
		instructionsMenu.setAlwaysOnTop(true);
		instructionsMenu.setVisible(true);
		
		//Scores
		JFrame highScores = new JFrame("High Scores");
		highScores.setUndecorated(true);
		highScores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highScores.setSize(new Dimension(400, 600));
		highScores.setLocationRelativeTo(null);
		highScores.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 + 550, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 400));
		highScores.setVisible(true);
		JLabel currHighScores = new JLabel("<html>Session High Score: 0pts | phase 0<br> All Time High Score: " + highestScore + "pts | phase " + highestPhase + "<html>", SwingConstants.CENTER);
		currHighScores.setFont(new Font("Courier", Font.BOLD, 20));
		highScores.setAlwaysOnTop(true);
		highScores.add(currHighScores);
		
		//Quit Button
		JFrame quitMenu = new JFrame();
		quitMenu.setUndecorated(true);
		quitMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quitMenu.setPreferredSize(new Dimension(200, 100));
		quitMenu.setLocationRelativeTo(null);
		quitMenu.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 + 750, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 + 300));
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Courier", Font.BOLD, 60));
		quitMenu.add(quitButton);
		quitMenu.pack();
		quitMenu.setAlwaysOnTop(true);
		quitMenu.setVisible(true);
		
		//Settings
		
		//Starting Phase: pannel
		JFrame startingPhaseOverlay = new JFrame();
		startingPhaseOverlay.setUndecorated(true);
		startingPhaseOverlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startingPhaseOverlay.setSize(new Dimension(300, 150));
		startingPhaseOverlay.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 500, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 400));
		JLabel startingPhaseText2 = new JLabel("Raise the starting phase");
		JLabel startingPhaseText = new JLabel("<html>WARNING: You cannot get high scores with this option enabled!<html>");
		JPanel startingPhasePanel = new JPanel();
		startingPhaseText.setFont(new Font("Courier", Font.BOLD, 25));
		startingPhaseText2.setFont(new Font("Courier", Font.BOLD, 20));
		startingPhasePanel.setLayout(new BoxLayout(startingPhasePanel, BoxLayout.Y_AXIS));
		startingPhaseText.setVerticalAlignment(SwingConstants.TOP);
		startingPhasePanel.add(startingPhaseText2);
		startingPhasePanel.add(startingPhaseText);
		startingPhaseOverlay.add(startingPhasePanel);
		startingPhaseOverlay.setVisible(false);
		
		JFrame startingPhaseButtonBack = new JFrame();
		setUpJFrame(startingPhaseButtonBack, new Dimension(300, 300), new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 500, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 200));
		JButton startingPhaseButton = new JButton("Click to raise");
		setUpJButton(startingPhaseButton, 40);
		startingPhaseButtonBack.add(startingPhaseButton);
		startingPhaseButtonBack.setVisible(false);
		
		JFrame startingPhaseIncrementedBack = new JFrame();
		setUpJFrame(startingPhaseIncrementedBack, new Dimension(200, 100), new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 450, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 + 100));
		JLabel startingPhaseIncremented = new JLabel("Starting phase: " + startingPhase);
		startingPhaseIncremented.setFont(createFont(20));
		startingPhaseIncrementedBack.add(startingPhaseIncremented);
		startingPhaseIncrementedBack.setVisible(false);
		

		
		JFrame lastPlay = null;
		
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				play.play();
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e){
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setFont(new Font("Courier", Font.BOLD, 240));
				playButton.setForeground(new Color(133, 22, 0, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setFont(new Font("Courier", Font.BOLD, 220));
				playButton.setForeground(new Color(71, 12, 1, 255));
				playButton.setBackground(null);
			}
		});
		
		settingsButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (!settingsOpen.isPlaying()) {
					playMenu.setVisible(false);
					highScores.setVisible(false);
					quitMenu.setVisible(false);
					startingPhaseOverlay.setVisible(true);
					startingPhaseButtonBack.setVisible(true);
					settingsOpen.play();
				}
				else {
					settingsOpen.endPlay();
					playMenu.setVisible(true);
					highScores.setVisible(true);
					quitMenu.setVisible(true);
					startingPhaseOverlay.setVisible(false);
					startingPhaseButtonBack.setVisible(false);
					startingPhaseIncrementedBack.setVisible(false);
				}
				
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e){
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				settingsButton.setFont(new Font("Courier", Font.BOLD, 70));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingsButton.setFont(new Font("Courier", Font.BOLD, 60));
			}
		});
		
		instructionsButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//play.play();
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e){
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				instructionsButton.setFont(new Font("Courier", Font.BOLD, 60));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				instructionsButton.setFont(new Font("Courier", Font.BOLD, 50));
			}
		});
		
		quitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				quitMenu.dispatchEvent(new WindowEvent(quitMenu, WindowEvent.WINDOW_CLOSING));
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e){
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setFont(new Font("Courier", Font.BOLD, 70));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setFont(new Font("Courier", Font.BOLD, 60));
			}
		});
		
		startingPhaseButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!startingPhaseIncrementedBack.isVisible()) startingPhaseIncrementedBack.setVisible(true);
				if (startingPhase.getInt() <= 3) startingPhase.setInt(startingPhase.getInt()+1);
				else startingPhase.setInt(0);
				if (startingPhase.getInt() > 0) startingPhaseIncremented.setText("Starting Phase: " + startingPhase);
				else {
					startingPhaseIncrementedBack.setVisible(false);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		while (true) {
			if (play.isPlaying()) {
				playMenu.setVisible(false);
				settingsMenu.setVisible(false);
				highScores.setVisible(false);
				instructionsMenu.setVisible(false);
				quitMenu.setVisible(false);
				try {
					lastPlay.setVisible(false);
				} catch (Exception e) {}
				lastPlay = Game.playGame(startingPhase.getInt());
				play.endPlay();
				playMenu.setVisible(true);
				settingsMenu.setVisible(true);
				highScores.setVisible(true);
				instructionsMenu.setVisible(true);
				quitMenu.setVisible(true);
				currHighScores.setText("<html>Session High Score: " + score + "pts | phase "+ phase + "<br> All Time High Score: " + highestScore + "pts | phase " + highestPhase + "<html>");
			}
			try {
				Thread.sleep(100);
			} catch(Exception e) {
				
			}
		}
	}
	
	public static void checkNewScore(int newScore, int newPhase, int startingPhase) {
		if (score <= newScore && startingPhase == 0) {
			score = newScore;
			phase = newPhase;
			if (newScore > highestScore) {
				try (final DataOutputStream dos = new DataOutputStream(new FileOutputStream(highScoreFile))){
					dos.writeInt(newScore);
				}catch (Exception e) {}
				try (final DataOutputStream dos = new DataOutputStream(new FileOutputStream(highPhaseFile))){
					dos.writeInt(newPhase);
				}catch (Exception e) {}
				highestScore = newScore;
				highestPhase = newPhase;
			}
		}
	}
	
	public static void fetchHighScores() {
		try (final DataInputStream dis = new DataInputStream(new FileInputStream(highScoreFile))){
			highestScore = dis.readInt();
		} catch(Exception e) {}
		try (final DataInputStream dis = new DataInputStream(new FileInputStream(highPhaseFile))){
			highestPhase = dis.readInt();
		} catch(Exception e) {}
	}
	/**
	 * sets up a JFrame's size and location
	 * @param frame the JFrame
	 * @param size the size of the JFrame, x and y
	 * @param location where the JFrame is on the screen
	 * @return a JPanel object to go with the JFrame
	 */
	public static JPanel setUpJFrame(JFrame frame, Dimension size, Point location) {
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size);
		frame.setLocation(location);
		return new JPanel();
	}
	
	public static JPanel setUpJFrame(JFrame frame) {
		return setUpJFrame(frame, new Dimension(100, 100),  new Point(0, 0));
	}
	
	public static void setUpJButton(JButton button, int size) {
		button.setFont(new Font("Courier", Font.BOLD, 40));
	}
	
	public static void setUpJButton(JButton button) {
		setUpJButton(button, 20);
	}
	
	public static Font createFont(int size) {
		return new Font("Courier", Font.BOLD, size);
	}
}
