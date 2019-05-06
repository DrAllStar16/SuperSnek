import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	TheSnek TS;
	Obstacle O;
	Food s = new Food(SuperSnek.XY + 420, SuperSnek.XY + 400, SuperSnek.XY, SuperSnek.XY, true);
	Food n = new Food(SuperSnek.XY + 20, SuperSnek.XY + 20, SuperSnek.XY, SuperSnek.XY, false);
	ArrayList<Food> Superfood = new ArrayList<Food>();
	ArrayList<Food> Normalfood = new ArrayList<Food>();
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	static final int SUPER_FOOD = 1;
	static final int NORMAL_FOOD = 2;

	public ObjectManager(TheSnek TS, Obstacle O) {
		this.TS = TS;
		this.O = O;
		Superfood.add(s);
		Normalfood.add(n);
		obstacles.add(O);
		
	}

	public void update() {
		TS.update();
	}

	public void draw(Graphics g) {
		TS.draw(g);
		O.draw(g);
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

			}

		}

		for (Food f : Normalfood) {

			if (TS.collisionBox.intersects(f.collisionBox)) {
				f.isAlive = false;

			}

		}

		for (Obstacle o : obstacles) {

			if (TS.collisionBox.intersects(o.collisionBox)) {
				TS.isAlive = false;

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
		Superfood.add(new Food(pos.get(0), pos.get(1), SuperSnek.XY, SuperSnek.XY, true));
	}

	public void makeNormalFood() {
		ArrayList<Integer> pos = getFoodPos();
		Normalfood.add(new Food(pos.get(0), pos.get(1), SuperSnek.XY, SuperSnek.XY, false));
	}

	public ArrayList<Integer> getFoodPos() {
		/*
		 * ArrayList<Integer> pos = new ArrayList<Integer>(); boolean overlap = false;
		 * int xpos; int ypos; Random r = new Random(); do {
		 * 
		 * xpos = r.nextInt(SuperSnek.w - SuperSnek.XY); ypos = r.nextInt(SuperSnek.h -
		 * SuperSnek.XY); xpos = xpos - (xpos % SuperSnek.XY); ypos = ypos - (ypos %
		 * SuperSnek.XY); Rectangle TempFood = new Rectangle(xpos, ypos, SuperSnek.XY,
		 * SuperSnek.XY); if (TempFood.intersects(TS.collisionBox)) { overlap = true; }
		 * else { overlap = false; } } while (overlap); pos.add(xpos); pos.add(ypos);
		 * return pos;
		 */
		return checkNewObjectCollision(SuperSnek.XY, SuperSnek.XY);
	}

	public void reseting() {

		TS.isAlive = true;

	}

	public ArrayList<Integer> checkNewObjectCollision(int w, int h) {

		ArrayList<Integer> pos = new ArrayList<Integer>();
		boolean overlap = false;
		int xpos;
		int ypos;
		Random r = new Random();
		do {
			// Get a random value within the Snek frame
			//xpos = r.nextInt(SuperSnek.w - w); 
			xpos = r.nextInt(150);
			ypos = r.nextInt(100+350);
			//ypos = r.nextInt(SuperSnek.h - h);
			xpos = xpos - (xpos % SuperSnek.XY);
			ypos = ypos - (ypos % SuperSnek.XY);

			// Its making sure that the random x and y values don't intersect anything else
			Rectangle TempObject = new Rectangle(xpos, ypos, SuperSnek.XY, SuperSnek.XY);

			// 1. Checking the snek collision
			if (TempObject.intersects(TS.collisionBox)) {
				overlap = true;
				break;
			}
			// 2. Checking collision of obstcales
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle o = obstacles.get(i);
				if (xpos>= O.x && xpos<= O.x+O.w) {
					
					if (ypos>= O.y && ypos<= O.y+O.h) {
						overlap = true;
						break;
					}
				}
				
			}
			
			// 3. Checking super food collision
			for (int i = 0; i < Superfood.size(); i++) {
				
				if (TempObject.intersects(Superfood.get(i).collisionBox)) {
					overlap = true;
					break;
				}
			}
			// 4. Checking normal food collision
				for (int f = 0; f < Normalfood.size(); f++) {

					if (TempObject.intersects(Normalfood.get(f).collisionBox)) {
						overlap = true;
						break;
					}
				}
			
		} while (overlap);
		pos.add(xpos);
		pos.add(ypos);
		return pos;

	}

}
