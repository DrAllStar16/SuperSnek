import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food extends GameObject {

	int r = new Random().nextInt(w);

	public Food(int x, int y, int w, int h) {
		super(x, y, w, h);
		r = r - (r % 20);

	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(20, 20, 20, 20);

	}

}
