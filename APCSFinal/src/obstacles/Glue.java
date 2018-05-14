package obstacles;
import utilities.ImageLoader;

/**
 * 
 * @author Shaunak Bhandarkar
 *
 * The Glue class is a type of Obstacle whose specific
 * effect is not to damage the user, but rather slow
 * down the user's speed.
 *
 */
public class Glue extends Obstacle
{

	public Glue(float x, float y, double width, double height ) 
	{
		super(x, y, ImageLoader.glue, width, height);
	}
	

	public int getDamage() 
	{
		return 0;
	}
	
}
