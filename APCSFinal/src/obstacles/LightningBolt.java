/**
 * 
 * @author Shaunak Bhandarkar
 * 
 * The LightningBolt is a special type of Obstacle
 * that is capable of taking all of the Player's health
 * away in just one hit.
 *
 */
public class LightningBolt extends Obstacle
{

	public LightningBolt(float x, float y ) 
	{
		super(x, y, ImageLoader.lightningbolt, 70, 80);
	}
	
	public int getDamage() 
	{
		return 3;
	}
	
}
