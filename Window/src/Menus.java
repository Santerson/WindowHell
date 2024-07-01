import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menus {
	public static void main(String[] args) {	
		
		
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
		
		JFrame lastPlay = null;
		
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play.play();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
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
				try {
				lastPlay.setVisible(false);
				} catch (Exception e) {}
				lastPlay = Game.playGame();
				play.endPlay();
				menu.setVisible(true);
			}
			try {
				Thread.sleep(100);
			} catch(Exception e) {
				
			}
		}
	}
}
