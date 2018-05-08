import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PImage;

public class Player {

	private final double GRAVITY = 1.05, JUMP_HEIGHT = 20;

	private double xCoord;
	private double yCoord;
	private int health;
	private double width;
	private double height;
	private PImage character;
	private boolean alive, jumping, slow;
	private double vy;
	private Rectangle boundingRectangle;

	public Player(double x, double y, double w, double h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		alive = true;
		vy = 0;
		boundingRectangle = new Rectangle((int) x, (int) y, (int) w, (int) h);
		slow = false;
	}

	public void setup(PApplet drawer) {
		character = drawer.loadImage("character.png");
	}

	public void draw(PApplet drawer) {

		drawer.image(character, (float) xCoord, (float) yCoord, (float) width, (float) height);

		// drawer.rect(boundingRectangle.x, boundingRectangle.y,
		// boundingRectangle.width, boundingRectangle.height);

	}

	public void moveDirection(double x) {
		xCoord += x * 5;
	}

	public void jump() {
		if (!jumping)
			jumping = true;
	}
	
	public void cancelJump()
	{
		if(jumping) {
			jumping = false;
		}
	}
	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			alive = false;
		}
	}

	public boolean getStatus() {
		return alive;
	}

	public double getX() {
		return xCoord;
	}

	public double getY() {
		return yCoord;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setX(double x) {
		xCoord = x;
	}
	
	public void setY(double y) {
		yCoord = y;
	}
	
	public void setVY(double newVY) {
		vy = newVY;
	}

	public void update(HashSet<Integer> keys, GameScreen gameScreen) {
		for (int key : keys) {
			if (key == PApplet.UP) {
				jump();
			} else if (key == PApplet.DOWN) {
				// god.throwObstacle(new LightningBolt(), 0, (float) Math.random() * 601);
			} else if (key == PApplet.LEFT && getX() > 0) {
				if (xCoord > gameScreen.ORIGINAL_WIDTH * 1 / 4) {
					if (slow) {
						moveDirection(-.5);
					} else
						moveDirection(-1);
				} else {
					gameScreen.translate(-10);
				}
			} else if (key == PApplet.RIGHT) {
				if (xCoord < gameScreen.ORIGINAL_WIDTH * 3 / 4) {
					if (slow) {
						moveDirection(.5);
					} else
						moveDirection(1);
				} else {
					gameScreen.translate(10);
				}

			}
		}
		act(gameScreen);
	}

	private void act(GameScreen gameScreen) {
		if (jumping) {
			yCoord -= JUMP_HEIGHT;
		}
		boundingRectangle.setLocation((int) xCoord, (int) yCoord);

		 
		vy += GRAVITY;
		yCoord += vy;
		boundingRectangle.setLocation((int) xCoord, (int) yCoord);
	}

	public Rectangle getBoundingRect() {
		return boundingRectangle;
	}

	public void setSlow(boolean x) {
		slow = x;
	}

}
