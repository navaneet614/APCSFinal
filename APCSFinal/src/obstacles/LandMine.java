package obstacles;

import utilities.ImageLoader;

public class LandMine extends Obstacle {
	public LandMine(float x, float y, double width, double height) {
		super(x,y,ImageLoader.mine,25,25);
	}

	@Override
	public int getDamage() {
		return 3;
	}
	
	
}
