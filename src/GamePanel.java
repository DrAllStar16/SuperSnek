import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer t;
	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	public static int currentState = MENU_STATE;
	Font titleFont;
	Font GameOverFont;
	TheSnek TS;
	ObjectManager OM;

	public GamePanel() {
		t = new Timer(1000 / 60, this);
		titleFont = new Font("HanziPen SC", Font.ITALIC, 36);
		GameOverFont = new Font("Futura", Font.BOLD, 36);
		TS = new TheSnek(SuperSnek.w / 2, SuperSnek.h / 2, 500, 500);
		OM = new ObjectManager(TS);
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
		g.setColor(new Color(0, 150, 30, 100));
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("SuperSnek", 180, 50);
		g.drawString("Press ENTER to start", 90, 400);
	}

	public void drawGameState(Graphics g) {
		g.setColor(new Color(100, 85, 51, 178));
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		OM.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(new Color(153, 0, 0, 255));
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		g.setFont(GameOverFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 140, 40);
		g.drawString("Press ENTER to restart", 40, 450);
	}

	void startGame() {
		t.start();
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

		if (KeyEvent.VK_UP == e.getKeyCode()) {
			TS.y -= 20;
			if (TS.y < 0) {
				TS.y = 500;
			}

		} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
			TS.y += 20;
			if (TS.y > 500) {
				TS.y = 0;
			}
		}
		if (KeyEvent.VK_LEFT == e.getKeyCode()) {
			TS.x -= 20;
			if (TS.x < 0) {
				TS.x = 500;
			}
		}
		if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
			TS.x += 20;

			if (TS.x > 500) {
				TS.x = 0;
			}
		}
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}