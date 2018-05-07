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
	
	public void fall() {
		
	/*	if ( yCoord < 550 )
			canFall = true;
		else
			canFall = false;*/
		
		if(distanceFromGround() > 1) {
		velocity++;
		if(velocity >= maxVelocity) {
			velocity = maxVelocity;
		}
		yCoord+=velocity;
		if(yCoord > 550) {
			yCoord = 550;
			//canFall = false;
		}
		}
		/*while(distanceFromGround() > 1) {
			velocity++;
			if(velocity >= maxVelocity) {
				velocity = maxVelocity;
			}
			yCoord+=velocity;
			if(yCoord > 550) {
				yCoord = 550;
			}
		}
		velocity = 0;*/
		
	}
	
	public void jump(double y) {
		/*if(canJump) {
		yCoord -= y;
		fall();
		}
		canJump = false;*/
		if ( 600 - yCoord <= 51 ) 
		{
			yCoord -= y;
		}
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
	
	public void setFall(boolean x) {
		canFall = x;
	}
	
	public boolean intersects( Line l ) 
	{
		Line feet = new Line( xCoord, yCoord+height, xCoord+width, yCoord+height );
		if (feet.intersects(l) )
			return true;
		return false;
	}
	

	
}
