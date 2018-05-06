
public class Spike extends Obstacle
{

	public Spike(float x, float y) {
		super(x, y, "spike.png" );
	}

	public int getDamage() {
		return 1;
	}

	public double getArea() {
		return 0;
	}

}
