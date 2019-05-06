import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameObject {

	public Obstacle(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public void draw(Graphics g) {
		g.drawRect(x, y, w, h);
		g.setColor(Color.GRAY);

	}
}
