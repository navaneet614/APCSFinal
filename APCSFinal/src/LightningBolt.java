
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
