import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SuperSnek {
	JFrame f;
	JPanel p;
	int w = 500;
	int h = 500;

	public static void main(String[] args) {
		SuperSnek ss = new SuperSnek();
	}

	public SuperSnek() {
		f = new JFrame();
		p = new GamePanel();
		f.add(p);
		f.setSize(w, h);
		f.setBackground(new Color(0, 100, 50));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
