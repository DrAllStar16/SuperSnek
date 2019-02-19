import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int w;
	int h;
	boolean isAlive;
	Rectangle collisionBox;

	public GameObject(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.isAlive = true;
		collisionBox = new Rectangle(x, y, w, h);
	}

	public void update() {

	}

	public void draw(Graphics g) {

	}
}
