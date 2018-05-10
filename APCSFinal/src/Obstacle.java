import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import processing.core.*;

/**
 * 
 * @author William Hu
 * 
 * The abstract superclass Obstacle provides 
 * the framework for all Obstacles in the game.
 * In particular, this includes quantifying the 
 * health damage done to the Player as well as 
 * identifying the boundaries of a given Obstacle.
 *
 */
public abstract class Obstacle 
{
	private float x;
	private float y;
	private double width, height;
	private Rectangle boundingRectangle;
	private PImage pic;
	
	public Obstacle( float x, float y, PImage p, double width, double height) 
	{
		this.x = x;
		this.y = y;
		pic = p;
		this.width = width;
		this.height = height;
		boundingRectangle = new Rectangle((int)x,(int)y,(int)width,(int)height);
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
		this.x = x;
	}
	
	public void setY( float y ) 
	{
		this.y = y;
	}
	
	
	public double getWidth() 
	{
		return width;
	}
	
	public double getHeight() 
	{
		return height;
	}
	
	public void setup(PApplet drawer) {

	}
	
	public void draw( PApplet drawer ) 
	{
		boundingRectangle.setLocation((int)x, (int)y);
		drawer.image(pic , x, y, (float)width, (float)height );
//		drawer.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
	}
	
	public Rectangle getBoundRect() {
		return boundingRectangle;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
