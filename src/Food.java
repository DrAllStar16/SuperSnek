import java.awt.Color;
import java.awt.Graphics;

public class Food extends GameObject {
	boolean superFood = false;

	public Food(int x, int y, int w, int h, boolean superFood) {
		super(x, y, w, h);

		this.superFood = superFood;

	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		if (superFood == true) {
			g.drawImage(GamePanel.CupCakeImg, x, y, w, h, null);
		} else if (superFood == false) {
			g.drawImage(GamePanel.TheDonutImg, x, y, w, h, null);
		}

	}
}
