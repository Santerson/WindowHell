import java.awt.Dimension;
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
		JPanel panel = new JPanel();
		JButton playButton = new JButton("Play");
		panel.add(playButton);
		menu.add(panel);
		menu.pack();
		temp play = new temp();
		menu.setVisible(true);
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
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		while (true) {
			if (play.isPlaying()) {
				Game.playGame();
				play.endPlay();
			}
			try {
				Thread.sleep(100);
			} catch(Exception e) {
				
			}
		}
	}
}
