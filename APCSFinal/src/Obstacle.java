import processing.core.*;

public abstract class Obstacle 
{
	private float topX;
	private float topY;
	private String imagePath;
	private double width, height;
	
	public Obstacle( float x, float y, String imPath ) 
	{
		topX = x;
		topY = y;
		imagePath = imPath;
		width = 30;
		height = 30;
	}
	
	public Obstacle( String imPath ) 
	{
		topX = 0;
		topY = 0;
		imagePath = imPath;
		width = 30;
		height = 30;
	}
	
	
	public abstract int getDamage();
	
	// public abstract void animate();
	
	//public abstract double getArea();

	
	/*
	public boolean intersects( Obstacle other ) 
	{
		
	}*/
	public void setX( float x ) 
	{
		topX = x;
	}
	
	public void setY( float y ) 
	{
		topY = y;
	}
	
	public float getX() 
	{
		return topX;
	}
	
	public float getY() 
	{
		return topY;
	}
	
	public double getWidth() 
	{
		return width;
	}
	
	public double getHeight() 
	{
		return height;
	}
	
	public void draw( PApplet drawer ) 
	{
		PShape p = drawer.loadShape(imagePath);
		drawer.shape( p, topX, topY, (float)width, (float)height );
	}
}
