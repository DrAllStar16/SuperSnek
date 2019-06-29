import java.awt.Color;

import javax.swing.JFrame;

public class SuperSnek {
	JFrame f;
	GamePanel gp;
	static final int w = 700;
	static final int h = 700;
	static final int XY = 20;

	ResetListener rl = new ResetListener() {
		@Override
		public void reset() {
			gp.removeAll();
			f.removeAll();
			SuperSnek s = new SuperSnek();
			s.setup();

		}
	};

	public static void main(String[] args) {
		SuperSnek s = new SuperSnek();
		s.setup();
	}

	public SuperSnek() {
		f = new JFrame();
		gp = new GamePanel(rl);

	}

	void setup() {
		f.add(gp);
		f.addKeyListener(gp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(w, h);
		f.setBackground(new Color(0, 100, 50));
		f.setVisible(true);
		gp.startGame();
	}

}
