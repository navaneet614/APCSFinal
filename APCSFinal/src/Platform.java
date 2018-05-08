import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
public class Platform extends Rectangle{

	private String imagePath;
	private PImage pic;

	
	public Platform(int x, int y, int width, int height, String imPath) {
		super(x, y, width, height);
		imagePath = imPath;
	}
	
	public void setup(PApplet drawer) {
		pic = drawer.loadImage(imagePath);
	}
	
	public void moveBy(int dx, int dy) {
		this.translate(dx, dy);
	}
	
	public void draw( PApplet drawer ) 
	{
		drawer.image(pic, (float)x, (float)y, (float) width, (float) height);
//		drawer.rect(x, y, width, height);
	}

}
