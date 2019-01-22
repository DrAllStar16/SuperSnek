import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public GamePanel() {

	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 150, 70));
		g.fillRect(0, 0, 100, 100);
	}
}
