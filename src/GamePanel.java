import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	Timer t;
	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	public static int currentState = MENU_STATE;
	Font titleFont;
	Font GameOverFont;
	SuperSnek SS;

	public GamePanel() {
		t = new Timer(1000 / 60, this);
		titleFont = new Font("HanziPen SC", Font.ITALIC, 36);
		GameOverFont = new Font("Futura", Font.BOLD, 36);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 150, 70));
		g.fillRect(0, 0, 500, 500);
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

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("SuperSnek", 130, 40);
		g.drawString("Press ENTER to start", 90, 400);
		g.drawString("Press SPACE for intructions", 50, 700);
	}

	public void drawGameState(Graphics g) {

	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		g.setFont(GameOverFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 140, 40);
		g.drawString("Press ENTER to restart", 40, 700);
	}

	public void keyPressed(KeyEvent e) {
		if (KeyEvent.VK_ENTER == e.getKeyCode()) {

			if (currentState == END_STATE) {

			}
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}

		// if (KeyEvent.VK_UP == e.getKeyCode()) {
		// SS.-= 20;
		// } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
		// RS.y += 20;
		// }
		// if (KeyEvent.VK_LEFT == e.getKeyCode()) {
		// RS.x -= 20;
		// }
		// if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
		// RS.x += 20;

		// }
	}
}