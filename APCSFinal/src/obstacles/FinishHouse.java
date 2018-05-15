package obstacles;

import processing.core.PImage;

public class FinishHouse extends Obstacle {

	public FinishHouse(float x, float y, PImage p, double width, double height) {
		super(x, y, p, width, height);
	}

	@Override
	public int getDamage() {
		return 0;
	}

}
