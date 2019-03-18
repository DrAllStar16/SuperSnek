import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	TheSnek TS;
	Food f = new Food(SuperSnek.XY + 20, SuperSnek.XY + 20, SuperSnek.XY, SuperSnek.XY);
	ArrayList<Food> food = new ArrayList<Food>();

	public ObjectManager(TheSnek TS) {
		this.TS = TS;
		food.add(f);
		makeMeFood();

	}

	public void update() {
		TS.update();

	}

	public void draw(Graphics g) {
		TS.draw(g);
		for (int i = 0; i < food.size(); i++) {
			food.get(i).draw(g);
		}

	}

	public void checkCollision() {

		for (Food f : food) {

			if (TS.collisionBox.intersects(f.collisionBox)) {
				f.isAlive = false;
				System.out.println("b");
			}

		}
	}

	public void purgeObject() {
		for (int i = 0; i < food.size(); i++) {
			if (!food.get(i).isAlive) {
				food.remove(i);
				makeMeFood();
			}
		}
	}

	public void makeMeFood() {
		boolean overlap = false;
		int fod;
		int foood;
		Random r = new Random();
		do {
			fod = r.nextInt(500);
			foood = r.nextInt(500);
			Rectangle TempFood = new Rectangle(fod, foood, SuperSnek.XY, SuperSnek.XY);
			if (TempFood.intersects(TS.collisionBox)) {
				overlap = true;
			} else {
				overlap = false;
			}
		} while (overlap);
		food.add(new Food(fod, foood, SuperSnek.XY, SuperSnek.XY));

	}

}
