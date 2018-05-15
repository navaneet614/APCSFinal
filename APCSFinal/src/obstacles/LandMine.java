package obstacles;

import processing.core.PApplet;
import utilities.ImageLoader;

public class LandMine extends Obstacle {
	private boolean setOff;
	public LandMine(float x, float y, double width, double height) {
		super(x,y,ImageLoader.mine,25,25);
		setOff = false;
	}

	@Override
	public int getDamage() {
		return 3;
	}
	
	public void turnOn() {
		setOff = false;
	}
	
	public void setOff() {
		setOff = true;
	}
	
	public void draw(PApplet drawer) {
		if(setOff) {
			
		}else {
			super.draw(drawer);
		}
	}
	
	
}
