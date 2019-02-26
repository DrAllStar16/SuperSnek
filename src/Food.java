import java.util.Random;

public class Food {
	int foodx = 0;
	int foody = 0;
	int f20 = 0;
	Random r = new Random();

	public Food() {
		while (foodx == 0) {
			if (f20 - r.nextInt() < 10) {
				foodx = f20;
			}
			f20 += 20;
		}
	}

}
