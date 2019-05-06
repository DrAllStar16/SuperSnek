import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	Obstacle O;
	int yspeed;
	int xspeed;
	public static BufferedImage SnekelImg;

	public static BufferedImage CupCakeImg;

	public static BufferedImage TheDonutImg;
	ResetListener rl;

	public GamePanel(ResetListener rl) {
		this.rl = rl;
		t = new Timer(1000 / 60, this);
		titleFont = new Font("HanziPen SC", Font.ITALIC, 36);
		GameOverFont = new Font("Futura", Font.BOLD, 36);
		TS = new TheSnek(SuperSnek.w / 2, SuperSnek.h / 2, SuperSnek.XY, SuperSnek.XY);
		O = new Obstacle(100, 400, 60, 60);
		OM = new ObjectManager(TS, O);
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();

		try {

			SnekelImg = ImageIO.read(this.getClass().getResourceAsStream("Snekel.png"));

			CupCakeImg = ImageIO.read(this.getClass().getResourceAsStream("CupCake.png"));

			TheDonutImg = ImageIO.read(this.getClass().getResourceAsStream("TheDonut.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
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
		OM.update();
		OM.checkCollision();
		OM.purgeObject();
		if (!TS.isAlive) {
			currentState = END_STATE;
			TS.isAlive = true;

		}
	}

	private void updateEndState() {
		// TODO Auto-generated method stub

	}

	public void drawMenuState(Graphics g) {
		g.setColor(new Color(0, 150, 30, 100));
		g.fillRect(0, 0, SuperSnek.w, SuperSnek.h);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("SuperSnek", 160, 50);
		g.drawString("Press ENTER to start", 80, 400);
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

			currentState++;
			if (currentState == GAME_STATE) {
				t.stop();
				rl.reset();
			}
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}

		if (KeyEvent.VK_UP == e.getKeyCode()) {
			TS.yspeed = -5;
			TS.xspeed = 0;

		} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
			TS.yspeed = 5;
			TS.xspeed = 0;
		}
		if (KeyEvent.VK_LEFT == e.getKeyCode()) {
			TS.xspeed = -5;
			TS.yspeed = 0;
		}
		if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
			TS.xspeed = 5;
			TS.yspeed = 0;
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