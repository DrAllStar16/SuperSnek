import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	TheSnek TS;
	Food f = new Food(SuperSnek.XY + 20, SuperSnek.XY + 20, SuperSnek.XY, SuperSnek.XY);
	ArrayList<Food> Superfood = new ArrayList<Food>();
	ArrayList<Food> Normalfood = new ArrayList<Food>();
	static final int SUPER_FOOD = 1;
	static final int NORMAL_FOOD = 2;

	public ObjectManager(TheSnek TS) {
		this.TS = TS;
		Superfood.add(f);
		Normalfood.add(f);
	}

	public void update() {
		TS.update();

	}

	public void draw(Graphics g) {
		TS.draw(g);
		for (int i = 0; i < Superfood.size(); i++) {
			Superfood.get(i).draw(g);
		}
		for (int i = 0; i < Normalfood.size(); i++) {
			Normalfood.get(i).draw(g);
		}

	}

	public void checkCollision() {

		for (Food f : Superfood) {

			if (TS.collisionBox.intersects(f.collisionBox)) {
				f.isAlive = false;
				System.out.println("b");
			}

		}

		for (Food f : Normalfood) {

			if (TS.collisionBox.intersects(f.collisionBox)) {
				f.isAlive = false;
				System.out.println("b");
			}

		}
	}

	public void purgeObject() {
		for (int i = 0; i < Superfood.size(); i++) {
			if (!Superfood.get(i).isAlive) {
				Superfood.remove(i);
				makeMeFood(SUPER_FOOD);
			}
		}

		for (int i = 0; i < Normalfood.size(); i++) {
			if (!Normalfood.get(i).isAlive) {
				Normalfood.remove(i);
				makeMeFood(NORMAL_FOOD);
			}
		}
	}

	public void makeMeFood(int foodType) {
		if (foodType == SUPER_FOOD) {
			makeSuperFood();
		} else if (foodType == NORMAL_FOOD) {
			makeNormalFood();
		}

	}

	public void makeSuperFood() {
		ArrayList<Integer> pos = getFoodPos();
		Superfood.add(new Food(pos.get(0), pos.get(1), SuperSnek.XY, SuperSnek.XY));
	}

	public void makeNormalFood() {
		ArrayList<Integer> pos = getFoodPos();
		Superfood.add(new Food(pos.get(0), pos.get(1), SuperSnek.XY, SuperSnek.XY));
	}

	public ArrayList<Integer> getFoodPos() {
		ArrayList<Integer> pos = new ArrayList<Integer>();
		boolean overlap = false;
		int xpos;
		int ypos;
		Random r = new Random();
		do {
			xpos = r.nextInt(SuperSnek.w);
			ypos = r.nextInt(SuperSnek.h);
			Rectangle TempFood = new Rectangle(xpos, ypos, SuperSnek.XY, SuperSnek.XY);
			if (TempFood.intersects(TS.collisionBox)) {
				overlap = true;
			} else {
				overlap = false;
			}
		} while (overlap);
		pos.add(xpos);
		pos.add(ypos);
		return pos;

	}
}
