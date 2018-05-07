import processing.core.PImage;

import java.util.HashSet;

import processing.core.PApplet;


public class Player {
	
	private final double GRAVITY = 1.05;
	
	private double xCoord;
	private double yCoord;
	private int health;
	private double width;
	private double height;
	private PImage character;
	private boolean alive;
	private double vy;
	
	public Player(double x, double y,double w, double h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		alive = true;
		vy = 0;
	}
	
	public void setup(PApplet drawer) {
		character = drawer.loadImage("character.png");
	}
	
	public void draw(PApplet drawer) {

		
		
		drawer.image(character, (float)xCoord, (float)yCoord, (float) width, (float) height);
		
	}
	
	public void moveDirection(double x) {
		xCoord += x;
	}
	
	public void jump(double y) {
		vy = -10;
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		if(health <= 0) {
			alive = false;
		}
	}
	
	public double distanceFromGround() 
	{
		return ( 600 - yCoord - height );
	}
	
	public boolean getStatus() {
		return alive;
	}
	

	
	public boolean intersects( Line l ) 
	{
		Line feet = new Line( xCoord, yCoord+height, xCoord+width, yCoord+height );
		if (feet.intersects(l) )
			return true;
		return false;
	}
	
	public double getX() {
		return xCoord;
	}
	
	public double getY() {
		return yCoord;
	}
	
	public void update(HashSet<Integer> keys, GameScreen gameScreen) {
		for(int key:keys) {
			if (key == PApplet.UP) {
				jump(100);
				;
			} else if (key == PApplet.DOWN) {
//				god.throwObstacle(new LightningBolt(), 0, (float) Math.random() * 601);
			} else if (key == PApplet.LEFT && getX() > 0) {
				moveDirection(-1);
			} else if (key == PApplet.RIGHT) {
				moveDirection(1);
				;
			}
		}
		act(gameScreen);
	}
	
	private void act( GameScreen gameScreen) {
		
	}

	
}
