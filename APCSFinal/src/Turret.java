import java.util.ArrayList;

import processing.core.*;

public class Turret extends Obstacle
{
	private ArrayList<Bullet> bullets;
	public Turret(float x, float y, String imPath, double width, double height) 
	{
		super(x, y, imPath, width, height);
		bullets = new ArrayList<Bullet>();
	}

	public int getDamage() 
	{
		return 2;
	}
	
	public void shoot( double angle ) 
	{
		bullets.add(new Bullet(getX()+getWidth()/2, getY()+getHeight()/2, angle));
	}
	
	public void draw( PApplet drawer ) 
	{
		super.draw(drawer);
		for(Bullet b : bullets) {
			b.draw(drawer);
		}
		
	}

}
