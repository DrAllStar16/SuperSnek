import java.awt.Color;
import java.awt.Graphics;

public class TheSnek extends GameObject {
	int speed;

	public TheSnek(int x, int y, int w, int h) {
		super(x, y, w, h);
		speed = 5;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.fillRect(x - 20, y - 20, 20, 20);
		g.setColor(Color.GREEN);

	}
}