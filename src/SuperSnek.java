import java.awt.Color;

import javax.swing.JFrame;

public class SuperSnek {
	JFrame f;
	GamePanel gp;
	static final int w = 500;
	static final int h = 500;
	static final int XY = 20;

	public static void main(String[] args) {
		SuperSnek ss = new SuperSnek();
	}

	public SuperSnek() {
		f = new JFrame();
		gp = new GamePanel();
		setup();
		f.setSize(w, h);
		f.setBackground(new Color(0, 100, 50));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void setup() {
		f.add(gp);
		f.addKeyListener(gp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.startGame();
	}

}
