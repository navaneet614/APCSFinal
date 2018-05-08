import java.awt.Rectangle;
import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PImage;


public class Player{
	
	private final double GRAVITY = 1.05, JUMP_HEIGHT = 15;
	
	private double xCoord;
	private double yCoord;
	private int health;
	private double width;
	private double height;
	private PImage character;
	private boolean alive, jumping;
	private double vy;
	private Rectangle boundingRectangle;
	
	public Player(double x, double y,double w, double h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		alive = true;
		vy = 0;
		boundingRectangle = new Rectangle((int)x, (int)y, (int)w, (int)h);
	}
	
	public void setup(PApplet drawer) {
		character = drawer.loadImage("character.png");
	}
	
	public void draw(PApplet drawer) {

		drawer.image(character, (float)xCoord, (float)yCoord, (float) width, (float) height);
		
	}
	
	public void moveDirection(double x) {
		xCoord += x*5;
	}
	
	public void jump() {
		if(!jumping)
			jumping = true;
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
				jump();
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
	
	private void act(GameScreen gameScreen) {
		if(jumping) {
			yCoord-=JUMP_HEIGHT;
		}
		boundingRectangle.setLocation((int)xCoord, (int)yCoord);

		gameScreen.line(0, gameScreen.ORIGNAL_HEIGHT, gameScreen.ORIGINAL_WIDTH, gameScreen.ORIGNAL_HEIGHT);
		if(boundingRectangle.intersectsLine(0, gameScreen.height, gameScreen.width, gameScreen.height)) {
			vy=0;
			if(jumping){
				jumping = false;
			}
		} else {
			vy+=GRAVITY;
		}
		
		yCoord+=vy;
		boundingRectangle.setLocation((int)xCoord, (int)yCoord);
	}

	
}
