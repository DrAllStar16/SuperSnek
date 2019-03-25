import java.awt.Color;
import java.awt.Graphics;

public class TheSnek extends GameObject {
	int speed;
	int yspeed = 0;
	int xspeed = 0;

	public TheSnek(int x, int y, int w, int h) {
		super(x, y, w, h);
		speed = 20;
	}

	public void update() {
		super.update();
		y = y + yspeed;
		x = x + xspeed;
		if (y < 0) {
			y = 500;
		}
		if (y > 500) {
			y = 0;
		}
		if (x < 0) {
			x = 500;
		}
		if (x > 500) {
			x = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.SnekelImg, x, y, w, h, null);
		g.setColor(Color.GREEN);

	}
}