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
	TheSnek Head;
	TheSnek Tail;
	ObjectManager OM;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	Obstacle O;
	int counterInterval;
	public static BufferedImage SnekelImg;

	public static BufferedImage SnekelBodyImg;

	public static BufferedImage SnekelTailImg;

	public static BufferedImage TheEvilDonutImg;

	public static BufferedImage TheDonutImg;

	ResetListener rl;

	public GamePanel(ResetListener rl) {
		this.rl = rl;
		t = new Timer(1000 / 60, this);
		titleFont = new Font("HanziPen SC", Font.ITALIC, 100);
		GameOverFont = new Font("Futura", Font.BOLD, 40);
		Head = new TheSnek(SuperSnek.w / 2, SuperSnek.h / 2, SuperSnek.XY, SuperSnek.XY, SnekelImg);
		Tail = new TheSnek(SuperSnek.w / 2, (SuperSnek.h / 2) + 20, SuperSnek.XY, SuperSnek.XY, SnekelTailImg);
		obstacles.add(new Obstacle(100, 440, 160, 160));
		obstacles.add(new Obstacle(380, 60, 160, 20));
		obstacles.add(new Obstacle(520, 80, 20, 160));
		obstacles.add(new Obstacle(380, 220, 160, 20));
		obstacles.add(new Obstacle(100, 80, 20, 200));
		obstacles.add(new Obstacle(500, 360, 80, 240));
		OM = new ObjectManager(Head, Tail, obstacles);
		

		try {

			SnekelImg = ImageIO.read(this.getClass().getResourceAsStream("Snekel.png"));

			SnekelBodyImg = ImageIO.read(this.getClass().getResourceAsStream("SnekelBody.png"));

			SnekelTailImg = ImageIO.read(this.getClass().getResourceAsStream("SnekelTail2.png"));

			TheEvilDonutImg = ImageIO.read(this.getClass().getResourceAsStream("TheEvilDonut.png"));

			TheDonutImg = ImageIO.read(this.getClass().getResourceAsStream("TheDonut.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		counterInterval++;
		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {
			if (counterInterval % 4 == 0) {
				if (Head.xspeed != 0 || Head.yspeed != 0) {
					updateGameState();
				}
			}
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
		if (!Head.isAlive) {
			currentState = END_STATE;
			Head.isAlive = true;

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
		g.drawString("SuperSnek", 100, 80);
		g.drawString("Press ENTER", 70, 650);
		g.drawString("To Start", 160, 540);
		g.drawImage(SnekelImg, 40, 120, null);
		g.drawImage(SnekelBodyImg, 140, 120, null);
		g.drawImage(SnekelBodyImg, 240, 120, null);
		g.drawImage(SnekelBodyImg, 240, 220, null);
		g.drawImage(SnekelBodyImg, 240, 320, null);
		g.drawImage(SnekelBodyImg, 340, 320, null);
		g.drawImage(SnekelBodyImg, 440, 320, null);
		g.drawImage(SnekelTailImg, 540, 320, null);
		
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
		g.drawString("Game Over", 220, 60);
		g.drawString("You Ate "+OM.nfEaten + " Happy Donuts", 60, 200);
		g.drawString("You Ate "+OM.sfEaten + " Evil Donuts", 100, 340);
		g.drawString("Your Snek Was A Size Of " +OM.SnekBody.size(), 35, 480);
		g.drawString("Press ENTER to restart", 95, 620);
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
			if (Head.yspeed <= 0) {
				Head.yspeed = -SuperSnek.XY;
				Head.xspeed = 0;
			}

		} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
			if (Head.yspeed >= 0) {
				Head.yspeed = SuperSnek.XY;
				Head.xspeed = 0;
			}
		}
		if (KeyEvent.VK_LEFT == e.getKeyCode()) {
			if (Head.xspeed <= 0) {
				Head.xspeed = -SuperSnek.XY;
				Head.yspeed = 0;
			}
		}
		if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
			if (Head.xspeed >= 0) {
				Head.xspeed = SuperSnek.XY;
				Head.yspeed = 0;
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