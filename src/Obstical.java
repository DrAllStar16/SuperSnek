import java.awt.Color;
import java.awt.Graphics;

public class Obstical extends GameObject {

	public Obstical(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public void draw(Graphics g) {
		g.drawRect(x, y, 60, 60);
		g.setColor(Color.GRAY);

	}
}
