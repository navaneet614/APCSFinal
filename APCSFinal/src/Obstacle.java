import processing.core.*;

public abstract class Obstacle 
{
	private float topX;
	private float topY;
	private String imagePath;
	
	public Obstacle( float x, float y, String imPath ) 
	{
		topX = x;
		topY = y;
		imagePath = imPath;
	}
	
	public abstract int getDamage();
	
	//public abstract double getArea();

	
	/*
	public boolean intersects( Obstacle other ) 
	{
		
	}*/
	
	public void draw( PApplet drawer ) 
	{
		
	}
}
