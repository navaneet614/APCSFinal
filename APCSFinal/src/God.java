import java.util.ArrayList;

import processing.core.*;

public class God 
{
	private PImage character;
	private double xCoord, yCoord;
	private double width, height;
	private ArrayList<Obstacle> godstacles = new ArrayList<Obstacle>();
	
	public God( double x, double y, double w, double h ) 
	{
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		godstacles = new ArrayList<Obstacle>();
		for ( int i = 0; i < 5; i++ ) 
		{
			godstacles.add( new Block() );
			godstacles.add( new Glue() );
			godstacles.add( new LightningBolt() );
			godstacles.add( new Spike() );

		}
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
		for ( Obstacle o : godstacles ) 
		{
			if ( ob.equals(o) ) 
			{
				Obstacle t = godstacles.remove( godstacles.indexOf(ob) );
				t.setX( startX );
				t.setY( startY );
				fall( t );
				
			}
		}
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
			o.setY(o.getY() + (float)velocity);
			if(o.getY() > 550) {
				o.setY(550);
				//canFall = false;
			}
			}
			
		}
	
	public void addObstacle( Obstacle ob ) 
	{
		godstacles.add(ob);
	}
	
	
	
	
}
