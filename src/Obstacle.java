import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameObject {

	public Obstacle(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
		g.drawRect(x, y, w, h);
		
		

	}
}
