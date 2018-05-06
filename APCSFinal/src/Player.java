import processing.core.PImage;
import processing.core.PApplet;


public class Player {
	private double xCoord;
	private double yCoord;
	private int health;
	private double width;
	private double height;
	private PImage character;
	private boolean alive;
	
	public Player(double x, double y,double w, double h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		alive = true;
	}
	
	public void setup(PApplet drawer) {
		character = drawer.loadImage("character.png");
	}
	
	public void draw(PApplet drawer) {
		drawer.image(character, (float)xCoord, (float)yCoord, (float) width, (float) height);
	}
	
	public void moveDirection(double x, double y) {
		xCoord += x;
		yCoord += y;
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		if(health <= 0) {
			alive = false;
		}
	}
	
	public boolean getStatus() {
		return alive;
	}
	

	
}
