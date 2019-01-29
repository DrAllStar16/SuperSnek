import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	Timer t;
	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	public static int currentState = MENU_STATE;

	public GamePanel() {
		t = new Timer(1000 / 60, this);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 150, 70));
		g.fillRect(0, 0, 100, 100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {

			updateGameState();

		} else if (currentState == END_STATE) {

			updateEndState();

		}
		repaint();

	}

	private void updateMenuState() {
		// TODO Auto-generated method stub

	}

	private void updateGameState() {
		// TODO Auto-generated method stub

	}

	private void updateEndState() {
		// TODO Auto-generated method stub

	}
}
