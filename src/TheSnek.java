import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class TheSnek extends GameObject {
	int speed;
	int yspeed = 0;
	int xspeed = 0;
	BufferedImage img;

	public TheSnek(int x, int y, int w, int h, BufferedImage img) {
		super(x, y, w, h);
		speed = 20;
		this.img = img;
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
		g.drawImage(img, x, y, w, h, null);
		g.setColor(Color.GREEN);

	}
}