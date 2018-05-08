import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import processing.core.*;

public abstract class Obstacle 
{
	private float topX;
	private float topY;
	private String imagePath;
	private double width, height;
	private Rectangle boundingRectangle;
	
	public Obstacle( float x, float y, String imPath, double width, double height) 
	{
		topX = x;
		topY = y;
		imagePath = imPath;
		this.width = width;
		this.height = height;
		boundingRectangle = new Rectangle((int)topX,(int)topY,(int)width,(int)height);
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
	
	public Rectangle getBoundRect() {
		return boundingRectangle;
	}
	
	public double getX() {
		return topX;
	}
	
	public double getY() {
		return topY;
	}

}
