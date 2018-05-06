import processing.core.*;

public abstract class Obstacle 
{
	private float topX;
	private float topY;
	private PImage p;
	
	public Obstacle( float x, float y, String imagePath, PApplet drawer ) 
	{
		topX = x;
		topY = y;
		p = drawer.loadImage(imagePath);
		
	}
	
	public abstract int getDamage();
	
	public abstract double getArea();

	
	/*
	public boolean intersects( Obstacle other ) 
	{
		
	}*/
	
	public void draw( PApplet drawer ) 
	{
		
	}
}
