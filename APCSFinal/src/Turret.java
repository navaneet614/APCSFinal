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
	
	public void shoot() 
	{
		bullets.add(new Bullet(getX(), getY(), "left"));
	}
	
	public void draw( PApplet drawer ) 
	{
		super.draw(drawer);
		for(Bullet b : bullets) {
			b.draw(drawer);
		}
		
	}

}
