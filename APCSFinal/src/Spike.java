/**
 * 
 * @author Shaunak Bhandarkar
 * 
 * The Spike class is the most common Obstacle on the
 * GameScreen, besides Block, that damages the user when
 * stepped on. To avoid them, the Player must take an 
 * alternate path, or must jump over them. 
 * 
 *
 */
public class Spike extends Obstacle
{

	public Spike(float x, float y,double width, double height) {
		super(x, y, ImageLoader.spike ,width,height);
	}
	


	public int getDamage() 
	{	
		super.hit();
		if(canDamage()) {
			return 1;
		}
		return 0;
	}

}
