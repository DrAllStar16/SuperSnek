import java.util.Random;

public class Food extends GameObject {
	int foodx = 0;
	int foody = 0;
	int f20 = 0;
	int r = new Random().nextInt(500);

	public Food(int x, int y, int w, int h) {
		super(x, y, w, h);
		while (foodx == 0) {
			if (f20 - r < 10) {
				foodx = f20;
			}
			f20 += 20;
		}
	}

}
