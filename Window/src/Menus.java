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
				//play.play();
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
				lastPlay = Game.playGame();
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
	
	public static void checkNewScore(int newScore, int newPhase) {
		if (score <= newScore) {
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
}
