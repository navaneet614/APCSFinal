import java.util.ArrayList;

import processing.core.*;

public class God 
{
	private PImage character;
	private double xCoord, yCoord;
	private double width, height;
	
	public God( double x, double y, double w, double h ) 
	{
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
	}
	
	public void setup(PApplet drawer) {
		character = drawer.loadImage("god.png");
	}
	
	public void draw(PApplet drawer) 
	{
		drawer.image(character, (float)xCoord, (float)yCoord, (float) width, (float) height);
	}
	
	public void throwObstacle( Obstacle ob, float startX, float startY ) 
	{
		
	}
	
	private void fall( Obstacle o ) {
		
		/*	if ( yCoord < 550 )
				canFall = true;
			else
				canFall = false;*/
		double velocity = 0;
			
			if( ( 600 - o.getY() - o.getHeight() ) > 1) {
			velocity+=0.25;
			if(velocity >= 15) {
				velocity = 15;
			}
			o.setY((float)o.getY() + (float)velocity);
			if(o.getY() > 550) {
				o.setY(550);
				//canFall = false;
			}
			}
			
		}
	
	
	
	
}
