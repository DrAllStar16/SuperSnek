import java.awt.Graphics;
import java.util.Random;

public class ObjectManager {
	TheSnek TS;
	Random r;
	Food f;

	public ObjectManager(TheSnek TS) {
		this.TS = TS;
		// while(random x!= TS.x && random y ! = TS.y) {

		// }
	}

	public void update() {
		TS.update();

	}

	public void draw(Graphics g) {
		TS.draw(g);

	}
}
