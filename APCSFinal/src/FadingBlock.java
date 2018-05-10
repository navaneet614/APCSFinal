import processing.core.PApplet;

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
