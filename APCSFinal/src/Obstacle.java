import processing.core.*;

public abstract class Obstacle 
{
	protected float topX;
	protected float topY;
	protected PImage p;
	
	public Obstacle( float x, float y, String imagePath, PApplet drawer ) 
	{
		topX = x;
		topY = y;
		p = drawer.loadImage(imagePath);
	}
	
	public abstract int getDamage();
	
	public abstract double getArea();
	
	public double getWidth() 
	{
		p.getImage().getWidth(observer)
	}
	
	/*
	public boolean intersects( Obstacle other ) 
	{
		
	}*/
	
	public void draw( PApplet drawer ) 
	{
		
	}
}
