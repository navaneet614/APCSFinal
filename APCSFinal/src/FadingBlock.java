import processing.core.PApplet;

/**
 *
 *  @author Shaunak Bhandarkar
 * 
 * The FadingBlock is a special type of Block that gradually fades away
 * as soon as the Player steps on it. This Obstacle does not affect the
 * Player's health, but causes him/her to fall. 
 *
 */
public class FadingBlock extends Block 
{

	public FadingBlock(float x, float y, double width, double height) 
	{
		super(x, y, width, height);
	}
	
	
	public boolean steppedOn( Player p ) 
	{
		return ( p.getBoundingRect().intersects( this.getBoundRect() ) );
	}
	
	/*public void fade( Player p ) 
	{
		if ( steppedOn(p) ) 
		{
			
		}
	}*/

}
