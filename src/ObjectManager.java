import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

public class ObjectManager {
	TheSnek Head;
	TheSnek Tail;
	int nfEaten = 0;
	int sfEaten = 0;
	Food s = new Food(SuperSnek.XY + 420, SuperSnek.XY + 400, SuperSnek.XY, SuperSnek.XY, true);
	Food n = new Food(SuperSnek.XY + 20, SuperSnek.XY + 20, SuperSnek.XY, SuperSnek.XY, false);
	ArrayList<Food> Superfood = new ArrayList<Food>();
	ArrayList<Food> Normalfood = new ArrayList<Food>();
	ArrayList<Obstacle> obstacles ;
	ArrayList<TheSnek> SnekBody = new ArrayList<TheSnek>();
	static final int SUPER_FOOD = 1;
	static final int NORMAL_FOOD = 2;

	public ObjectManager(TheSnek Head, TheSnek Tail, ArrayList<Obstacle> O) {
		this.Head = Head;
		this.Tail = Tail;
		this.obstacles = O;
		Superfood.add(s);
		Normalfood.add(n);
		

	}

	public void update() {

		if (SnekBody.isEmpty()) {
			Tail.setX(Head.getX());
			Tail.setY(Head.getY());

		} else {
			Tail.setX(SnekBody.get(SnekBody.size() - 1).getX());
			Tail.setY(SnekBody.get(SnekBody.size() - 1).getY());
			for (int i = SnekBody.size() - 1; i > 0; i--) {
				SnekBody.get(i).setX(SnekBody.get(i - 1).getX());
				SnekBody.get(i).setY(SnekBody.get(i - 1).getY());
			}
			SnekBody.get(0).setX(Head.getX());
			SnekBody.get(0).setY(Head.getY());
		}

		Head.update();
	}

	public void draw(Graphics g) {
		Head.draw(g);
		Tail.draw(g);
		for (int i = 0; i < SnekBody.size(); i++) {
			SnekBody.get(i).draw(g);
		}
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g);
		}
		
		
		for (int i = 0; i < Superfood.size(); i++) {
			Superfood.get(i).draw(g);
			
		}
		for (int i = 0; i < Normalfood.size(); i++) {
			Normalfood.get(i).draw(g);
		}

	}

	public void checkCollision() {

		for (Food sf : Superfood) {

			if (Head.collisionBox.intersects(sf.collisionBox)) {
				sf.isAlive = false;
				sfEaten ++;
			}

		}

		for (Food nf : Normalfood) {

			if (Head.collisionBox.intersects(nf.collisionBox)) {
				nfEaten ++;
				nf.isAlive = false;

			}

		}

		for (Obstacle o : obstacles) {

			if (Head.collisionBox.intersects(o.collisionBox)) {
				Head.isAlive = false;

			}

		}

	if (Head.getX() == Tail.getX() && Head.getY() == Tail.getY()) {
			Head.isAlive = false;
		}

		for (int i = 0; i < SnekBody.size(); i++) {

			if (Head.getX() == (SnekBody.get(i).getX()) && Head.getY() == (SnekBody.get(i).getY())) {
				Head.isAlive = false;
			}
		}

	}

	public void purgeObject() {
		for (int i = 0; i < Superfood.size(); i++) {
			if (!Superfood.get(i).isAlive) {
				Superfood.remove(i);
				if (SnekBody.size()>0) {
					SnekBody.remove(i);
				}
				
				makeMeFood(SUPER_FOOD);
			}
		}

		for (int i = 0; i < Normalfood.size(); i++) {
			if (!Normalfood.get(i).isAlive) {
				Normalfood.remove(i);
				SnekBody.add(new TheSnek(Head.x, Head.y, 20, 20, GamePanel.SnekelBodyImg));
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

		Head.isAlive = true;

	}

	public ArrayList<Integer> checkNewObjectCollision(int w, int h) {

		ArrayList<Integer> pos = new ArrayList<Integer>();
		boolean overlap = false;
		int xpos;
		int ypos;
		Random r = new Random();
		do {
			// Get a random value within the Snek frame
			// xpos = r.nextInt(SuperSnek.w - w);
			xpos = r.nextInt(150);
			ypos = r.nextInt(100 + 350);
			// ypos = r.nextInt(SuperSnek.h - h);
			xpos = xpos - (xpos % SuperSnek.XY);
			ypos = ypos - (ypos % SuperSnek.XY);

			// Its making sure that the random x and y values don't intersect anything else
			Rectangle TempObject = new Rectangle(xpos, ypos, SuperSnek.XY, SuperSnek.XY);

			// 1. Checking the snek collision
			if (TempObject.intersects(Head.collisionBox)) {
				overlap = true;
				break;
			}
			// 2. Checking collision of obstcales
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle o = obstacles.get(i);
				if (xpos >= o.x && xpos <= o.x + o.w) {

					if (ypos >= o.y && ypos <= o.y + o.h) {
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
