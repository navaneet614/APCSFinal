import processing.core.PApplet;

public abstract class Obstacle 
{
	protected float topX;
	protected float topY;
	
	public Obstacle( float x, float y ) 
	{
		topX = x;
		topY = y;
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
