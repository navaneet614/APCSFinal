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
	private double velocity;
	private double maxVelocity;
	private boolean canFall;
	private boolean canJump;
	
	public Player(double x, double y,double w, double h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		alive = true;
		velocity = 0;
		maxVelocity = 15;
		canFall = true;
		canJump = true;
	}
	
	public void setup(PApplet drawer) {
		character = drawer.loadImage("character.png");
	}
	
	public void draw(PApplet drawer) {
		fall();
		drawer.image(character, (float)xCoord, (float)yCoord, (float) width, (float) height);
	}
	
	public void moveDirection(double x) {
		xCoord += x;
	}
	
	private void fall() {
		if(canFall) {
		velocity++;
		if(velocity >= maxVelocity) {
			velocity = maxVelocity;
		}
		yCoord+=velocity;
		if(yCoord >= 600) {
			yCoord = 550;
			canFall = false;
		}
		}
	}
	
	public void jump(double y) {
		if(canJump) {
		yCoord -= y;
		}
		canJump = false;
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
	
	public void setFall(boolean x) {
		canFall = x;
	}
	

	
}
