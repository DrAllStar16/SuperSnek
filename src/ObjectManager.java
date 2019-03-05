import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	TheSnek TS;
	Random r;
	Food f = new Food(20 + 20, 20 + 20, 20, 20);
	ArrayList<Food> food = new ArrayList<Food>();

	public ObjectManager(TheSnek TS) {
		this.TS = TS;
		food.add(f);

	}

	public void update() {
		TS.update();

	}

	public void draw(Graphics g) {
		TS.draw(g);
		f.draw(g);

	}

	public void checkCollision() {
		for (Food f : food) {

			if (TS.collisionBox.intersects(f.collisionBox)) {
				f.isAlive = false;
				TS.isAlive = false;
				System.out.println("b");
			}

		}
	}

	public void purgeObject() {
		for (int i = 0; i < food.size(); i++) {
			if (!food.get(i).isAlive) {
				food.remove(i);
			}
		}
	}
}
