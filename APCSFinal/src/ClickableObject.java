import java.awt.Color;

import processing.core.PApplet;

public class ClickableObject extends Button {
	private Obstacle obstacle;
	private float x,y,width,height;
	private String text;
	
	public ClickableObject(float x, float y, float width, float height, Obstacle obstacle, String text) {
		super(x,y,width,height,text, Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE);
		this.obstacle = obstacle;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}
	
	

}
