import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

		//Menu
		JFrame menu = new JFrame("Select an option");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setPreferredSize(new Dimension(600, 300));
		menu.setLocationRelativeTo(null);
		menu.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 300, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 - 150));
		JPanel panel = new JPanel();
		JButton playButton = new JButton("Play");
		playButton.setFont(new Font("Courier", Font.BOLD, 12));
		panel.add(playButton);
		menu.add(panel);
		menu.pack();
		PlayBoolean play = new PlayBoolean();
		menu.setVisible(true);
		
		//Scores
		JFrame highScores = new JFrame("High Scores");
		highScores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highScores.setSize(new Dimension(400, 200));
		highScores.setLocationRelativeTo(null);
		highScores.setLocation(new Point(MovingWindow.RIGHT_EDGE_OF_SCREEN / 2 - 200, MovingWindow.BOTTOM_EDGE_OF_SCREEN / 2 + 250));
		highScores.setVisible(true);
		JLabel currHighScores = new JLabel("<html>High Score: 0pts | phase 0<br> All Time High Score: " + highestScore + "pts | phase " + highestPhase + "<html>", SwingConstants.CENTER);
		currHighScores.setFont(new Font("Courier", Font.BOLD, 20));
		highScores.add(currHighScores);
		
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
				playButton.setFont(new Font("Courier", Font.BOLD, 15));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setFont(new Font("Courier", Font.BOLD, 12));
			}
		});
		while (true) {
			if (play.isPlaying()) {
				menu.setVisible(false);
				highScores.setVisible(false);
				try {
				lastPlay.setVisible(false);
				} catch (Exception e) {}
				lastPlay = Game.playGame();
				play.endPlay();
				menu.setVisible(true);
				highScores.setVisible(true);
				currHighScores.setText("<html>High Score: " + score + "pts | phase "+ phase + "<br> All Time High Score: " + highestScore + "pts | phase " + highestPhase + "<html>");
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
